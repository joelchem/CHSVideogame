import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CameraViewer extends JPanel{
	
	private Game game;
	
	int cameraX = 0;
	int cameraY = 0;
	double heading = 0;
	double iterator = 0;
	
	int pointX = 0;
	int pointY = 0;
	int dimX = 2000;
	int dimY = 2000;
	
	int cameraWidth = 500;
	int cameraHeight = 500;
	
	Path path;
	
	CameraViewer() {
//		game = g;
		Path combinePath = new Path();
		combinePath.addPath(new StraightPath(new Point(0, 0), new Point(0, 500)));
		combinePath.addPath(new CurvedPath(new Point(0, 500), new Point(100, 500), -Math.PI/2));
		combinePath.addPath(new StraightPath(new Point(100, 600), new Point(300, 600)));
		combinePath.addPath(new CurvedPath(new Point(300, 600), new Point(300,700), Math.PI/2));
		combinePath.addPath(new StraightPath(new Point(400, 700), new Point(400, 1000)));
		combinePath.addPath(new CurvedPath(new Point(400, 1000), new Point(300, 1000), Math.PI/2));
		combinePath.addPath(new StraightPath(new Point(300, 1100), new Point(-150, 1100)));
		combinePath.addPath(new CurvedPath(new Point(-150, 1100), new Point(-150, 1000), Math.PI/2));
		combinePath.addPath(new StraightPath(new Point(-250, 1000), new Point(-250, 100)));
		combinePath.addPath(new CurvedPath(new Point(-250, 100), new Point(-350, 100), -Math.PI/2));
		combinePath.addPath(new StraightPath(new Point(-350, 0), new Point(-800, 0)));
		combinePath.addPath(new CurvedPath(new Point(-800, 0), new Point(-800, 100), -Math.PI/2));
		combinePath.addPath(new StraightPath(new Point(-900, 100), new Point(-900, 800)));
		path = combinePath;
	}
	
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D g = (Graphics2D) graphics;
//
//		AffineTransform camTransform = game.getCamera().getTransform();
//		g.transform(camTransform);
//		Image mapImg = game.getMap().getMapImage();
//		g.drawImage(mapImg, 0, 0, this);
//		
//		
//		
//		for(int i = 0; i < game.displayObjectAmt(); i++) {
//			DisplayObject obj = game.getDisplayObject(i);
//			Image sprite = obj.getSprite();
//			g.drawImage(sprite, obj.getX()-obj.getDimensionX()/2, 
//					obj.getY()-obj.getDimensionY()/2, this);
//		}
//		
//		for(int i = 0; i < game.oncomingStudentsAmt(); i++) {
//			OncomingStudent student = game.getOncomingStudents(i);
//			Image sprite = student.getSprite();
//			g.drawImage(sprite, student.getX()-student.getDimensionX()/2,
//					student.getY()-student.getDimensionY()/2, this);
//		}
//		
//		Player player = game.getPlayer();
//		g.drawImage(player.getSprite(), player.getPositionX()-player.getDimensionX()/2,
//				player.getPositionY()-player.getDimensionY()/2, this);
		
		Point pos = path.getPos(iterator);
		heading = path.heading(iterator);
		cameraX = (int)pos.getX();
		cameraY = (int)pos.getY();
		
		
		Image img = null;
		try {
		    img = ImageIO.read(new File("assets/map_test.png")).getScaledInstance(dimX, dimY, 0);
		} catch (IOException e) {
		}
		AffineTransform camTransform = new AffineTransform();
		camTransform.translate(-cameraX+cameraWidth/2,  -cameraY+cameraHeight/2);
		camTransform.rotate(heading,cameraX,cameraY);
		g.transform(camTransform);
		g.drawImage(img, pointX-dimX/2, pointY-dimY/2, this);
		
		iterator = (iterator + 2)%path.length();
		repaint();
	}
	
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		CameraViewer cam = new CameraViewer();
		cam.setSize(new Dimension(500, 500));
		cam.setPreferredSize(new Dimension(500, 500));
		JFrame graphFrame = new JFrame("CHSVideogame");
		graphFrame.setResizable(false);
		graphFrame.setSize(new Dimension(500+11, 500+11));
		graphFrame.setPreferredSize(new Dimension(500+11, 500+11));
		graphFrame.setContentPane(cam);
		graphFrame.pack();
		graphFrame.setVisible(true);
		cam.repaint();
	}
	
}
