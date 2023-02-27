import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class CameraViewer extends JPanel implements MouseMotionListener {
	
	private Game game;
	
	int cameraX = 0;
	int cameraY = 0;
	double heading = 0;
	double iterator = 0;
	
	int pointX = 0;
	int pointY = 0;
	int dimX = 2000;
	int dimY = 2000;
	
	int cameraWidth;
	int cameraHeight;
	
	Path path;
	Timer timer;
	
	CameraViewer(Game g) {
		game = g;
		cameraWidth = game.getCamera().getDimX();
		cameraHeight = game.getCamera().getDimY();
		
		

		
		Path combinePath = new Path();
//		combinePath.addPath(new StraightPath(new Point(0, 0), new Point(0, 500)));
//		combinePath.addPath(new CurvedPath(new Point(0, 500), new Point(100, 500), -Math.PI/2));
//		combinePath.addPath(new StraightPath(new Point(100, 600), new Point(300, 600)));
//		combinePath.addPath(new CurvedPath(new Point(300, 600), new Point(300,700), Math.PI/2));
//		combinePath.addPath(new StraightPath(new Point(400, 700), new Point(400, 1000)));
//		combinePath.addPath(new CurvedPath(new Point(400, 1000), new Point(300, 1000), Math.PI/2, true));
//		combinePath.addPath(new StraightPath(new Point(300, 1100), new Point(-150, 1100)));
//		combinePath.addPath(new CurvedPath(new Point(-150, 1100), new Point(-150, 1000), Math.PI/2));
//		combinePath.addPath(new StraightPath(new Point(-250, 1000), new Point(-250, 100)));
//		combinePath.addPath(new CurvedPath(new Point(-250, 100), new Point(-350, 100), -Math.PI/2));
//		combinePath.addPath(new StraightPath(new Point(-350, 0), new Point(-800, 0)));
//		combinePath.addPath(new CurvedPath(new Point(-800, 0), new Point(-800, 100), -Math.PI/2, true));
//		combinePath.addPath(new StraightPath(new Point(-900, 100), new Point(-900, 800)));

		
		path = combinePath;
	}
	
	public Timer getTimer() {
		return timer;
	}
	
	public void renderFrame() {
		repaint();
	}
	
	public int getDimX() {
		return dimX;
	}
	
	public int getDimY() {
		return dimY;
	}
	
	
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D g = (Graphics2D) graphics;

		AffineTransform camTransform = game.getCamera().getTransform();
		g.transform(camTransform);
		Image mapImg = game.getMap().getMapImage();
		g.drawImage(mapImg, 0, 0, this);
		
		
		
		for(int i = 0; i < game.displayObjectAmt(); i++) {
			DisplayObject obj = game.getDisplayObject(i);
			Image sprite = obj.getSprite();
//			g.drawImage(sprite, obj.getX()-obj.getDimensionX()/2, 
//					obj.getY()-obj.getDimensionY()/2, this);

		}
		
		for(int i = 0; i < game.oncomingStudentsAmt(); i++) {
			OncomingStudent student = game.getOncomingStudents(i);
			Image sprite = student.getSprite();
			g.drawImage(sprite, student.getX()-student.getDimensionX()/2,
					student.getY()-student.getDimensionY()/2, this);
		}
		
		Player player = game.getPlayer();
//		g.drawImage(player.getSprite(), player.getPositionX()-player.getDimensionX()/2,
//				player.getPositionY()-player.getDimensionY()/2, this);
		
//		double diag = Math.hypot((double)player.getDimensionX(), (double)player.getDimensionY())/2;
//		double innerAngle = Math.acos((double)player.getDimensionX()/(double)player.getDimensionY());
//		
//		double offsetX = diag*Math.cos(innerAngle-player.getHeading());
//		double offsetY = diag*Math.sin(innerAngle-player.getHeading());
//		
//		AffineTransform t = new AffineTransform();
//		t.translate(player.getPositionX()-offsetX,  player.getPositionY()-offsetY);
//		t.rotate(-player.getHeading());
		Point playerPos = new Point(player.getPositionX(), player.getPositionY());
		AffineTransform playerTransform = game.getCamera().getObjectTransform(
				playerPos, player.getHeading(),player.getDimensionX(), player.getDimensionY());
		g.drawImage(player.getSprite(), playerTransform, this);
		
		
		
	    Shape theCircle = new Ellipse2D.Double(player.getPositionX() - 2, player.getPositionY() - 2, 2.0 * 2, 2.0 * 2);
		g.draw(theCircle);

//		Point pos = path.getPos(iterator);
//		heading = path.heading(iterator);
//		cameraX = (int)pos.getX();
//		cameraY = (int)pos.getY();
//		
//		
//		Image img = null;
//		try {
//		    img = ImageIO.read(new File("assets/map_test.png")).getScaledInstance(dimX, dimY, 0);
//		} catch (IOException e) {
//		}
//		AffineTransform camTransform = new AffineTransform();
//		camTransform.translate(-cameraX+cameraWidth/2,  -cameraY+cameraHeight/2);
//		camTransform.rotate(heading,cameraX,cameraY);
//		g.transform(camTransform);
//		g.drawImage(img, pointX-dimX/2, pointY-dimY/2, this);
//		
//		iterator = (iterator + 2)%path.length();
//		repaint();
	}
	
	public static void startWindow(CameraViewer cam) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		cam.setSize(new Dimension(500, 500));
		cam.setPreferredSize(new Dimension(500, 500));
		JFrame graphFrame = new JFrame("CHSVideogame");
		graphFrame.setResizable(false);
		graphFrame.setSize(new Dimension(500+11, 500+11));
		graphFrame.setPreferredSize(new Dimension(500+11, 500+11));
		graphFrame.setContentPane(cam);
		graphFrame.pack();
		graphFrame.setVisible(true);
		
	}

	public void mouseDragged(MouseEvent e) {}

	public void mouseMoved(MouseEvent e) {
		
	}
	
	
}
