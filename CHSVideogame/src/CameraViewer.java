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
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class CameraViewer extends JPanel implements MouseMotionListener, MouseListener {
	
	private Game game;
	
	int cameraX = 0;
	int cameraY = 0;
	double heading = 0;
	double iterator = 0;
	
	int cameraWidth;
	int cameraHeight;
	
	Path path;
	Timer timer;
	
	Image distBar;
	Image healthBar;
	Image strengthBar;
	Image emptyBar;
	
	CameraViewer(Game g) {
		game = g;
		cameraWidth = game.getCamera().getDimX();
		cameraHeight = game.getCamera().getDimY();
		
		addMouseMotionListener(this);
		addMouseListener(this);

		
		try {
			int barDimX = (int) (game.getCamera().getDimX()*0.3);
			int barDimY = (int) (game.getCamera().getDimX()*.3*7./48.);
		    emptyBar = ImageIO.read(new File("assets/empty_bar.png")).getScaledInstance(barDimX, barDimY, 0);
		    healthBar = ImageIO.read(new File("assets/health_bar.png")).getScaledInstance(barDimX, barDimY, 0);
		    strengthBar = ImageIO.read(new File("assets/strength_bar.png")).getScaledInstance(barDimX, barDimY, 0);
		    distBar = ImageIO.read(new File("assets/distance_bar.png")).getScaledInstance(barDimX, barDimY, 0);
		    
		} catch (IOException e) {
			System.out.println("bake sale sprites not found.");
		}
		
	}
	
	public Timer getTimer() {
		return timer;
	}
	
	public void renderFrame() {
		repaint();
	}
	
	public int getDimX() {
		return cameraWidth;
	}
	
	public int getDimY() {
		return cameraHeight;
	}
	
	
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D g = (Graphics2D) graphics;

		AffineTransform camTransform = game.getCamera().getTransform();
		AffineTransform originalTransform = g.getTransform();
		g.transform(camTransform);
		
		
		Image mapImg = game.getMap().getMapImage();
		g.drawImage(mapImg, AffineTransform.getScaleInstance(game.getMap().getScale(), game.getMap().getScale()), this);
		
		
		
		for(int i = 0; i < game.displayObjectAmt()+game.oncomingStudentsAmt(); i++) {
			DisplayObject obj;
			if(i < game.displayObjectAmt()) {
				obj = game.getDisplayObject(i);
			} else {
				obj = game.getOncomingStudents(i-game.displayObjectAmt());
			}
			
			Image sprite = obj.getSprite();
			Point objPos = new Point(obj.getX(), obj.getY());

			AffineTransform objTransform = game.getCamera().getObjectTransform(
					objPos, obj.getHeading(), obj.getDimensionX(), obj.getDimensionY());
//			System.out.println(objTransform+" "+objPos+" "+obj.getHeading()+" "+obj.getDimensionX()+" "+obj.getDimensionY());
			g.drawImage(obj.getSprite(),  objTransform, this);
//			g.drawImage(sprite, obj.getX()-obj.getDimensionX()/2,
//					obj.getY()-obj.getDimensionY()/2, this);
			obj.getHitbox().render(g);
			

		}
		
		for(int i = 0; i < game.oncomingStudentsAmt(); i++) {
			OncomingStudent student = game.getOncomingStudents(i);
			Image sprite = student.getSprite();
			Point studentPos = new Point(student.getX(), student.getY());
			
//			AffineTransform studentTransform = game.getCamera().getObjectTransform(
//					studentPos, student.getHeading(), student.getDimensionX(), student.getD
//			);
		}
		
		Player player = game.getPlayer();

		Point playerPos = new Point(player.getPositionX(), player.getPositionY());
		AffineTransform playerTransform = game.getCamera().getObjectTransform(
				playerPos, player.getHeading(),player.getDimensionX(), player.getDimensionY());
//		System.out.println(playerTransform+" "+player.getHeading());
		g.drawImage(player.getSprite(), playerTransform, this);
		
		game.getPlayer().getHitbox().render(g);
		
		g.setTransform(originalTransform);
		
		int gap = (int) (game.getCamera().getDimX()*.01);
		int barWidth = (int) (game.getCamera().getDimX()*.32);
		
		g.drawImage(healthBar, gap, gap, null);
		g.drawImage(strengthBar, gap*2+barWidth, gap, null);
		g.drawImage(distBar, gap*3+barWidth*2, gap, null);

	}
	
	public static void startWindow(CameraViewer cam) {
		System.out.println(cam.getDimX()+" "+cam.getDimY());
		JFrame.setDefaultLookAndFeelDecorated(true);
		cam.setSize(new Dimension(cam.getDimX(), cam.getDimY()));
		cam.setPreferredSize(new Dimension(cam.getDimX(), cam.getDimY()));
		JFrame graphFrame = new JFrame("CHSVideogame");
		graphFrame.setResizable(false);
		graphFrame.setSize(new Dimension(cam.getDimX()+11, cam.getDimY()+11));
		graphFrame.setPreferredSize(new Dimension(cam.getDimX()+11, cam.getDimY()+11));
		graphFrame.setContentPane(cam);
		graphFrame.pack();
		graphFrame.setVisible(true);
		
	}

	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);
	}

	public void mouseMoved(MouseEvent e) {
		Point mousePos = e.getPoint();
		AffineTransform reverseCam;
		try {
			reverseCam = game.getCamera().getTransform().createInverse();
		} catch (NoninvertibleTransformException e1) {
			e1.printStackTrace();
		}
		
		int offset = game.getCamera().getDimX()/2-(int)mousePos.getX();
		game.getPlayer().setOffset(offset);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		game.setStart(true);
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
