import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
	private GameRunner gameRunner;
	
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
	
	CameraViewer(Game g, GameRunner gr) {
		game = g;
		gameRunner = gr;
		cameraWidth = game.getCamera().getDimX();
		cameraHeight = game.getCamera().getDimY();
		
		addMouseMotionListener(this);
		addMouseListener(this);

		
		try {
			int barDimX = (int) (game.getCamera().getDimX()*0.3);
			int barDimY = (int) (game.getCamera().getDimX()*.3*7./48.);
		    emptyBar = ImageIO.read(getClass().getClassLoader().getResource("empty_bar.png")).getScaledInstance(barDimX, barDimY, 0);
		    healthBar = ImageIO.read(getClass().getClassLoader().getResource("health_bar.png")).getScaledInstance(barDimX, barDimY, 0);
		    strengthBar = ImageIO.read(getClass().getClassLoader().getResource("strength_bar.png")).getScaledInstance(barDimX, barDimY, 0);
		    distBar = ImageIO.read(getClass().getClassLoader().getResource("distance_bar.png")).getScaledInstance(barDimX, barDimY, 0);
		    
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
//			obj.getHitbox().render(g);
			

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
		
//		game.getPlayer().getHitbox().render(g);
		
		g.setTransform(originalTransform);
		
		int gap = (int) (game.getCamera().getDimX()*.01);
		int barWidth = (int) (game.getCamera().getDimX()*.32);
		
		int height = (int) (game.getCamera().getDimX()*.3*7./48.);
		
		Player p = game.getPlayer();
		
		Image healthBarActual = cropBarImage(healthBar, (double)p.getHealth()/(double)p.getMaxHealth());
		Image strengthBarActual = cropBarImage(strengthBar, (double)p.getStrength()/(double)p.getMaxStrength());
		Image distBarActual = cropBarImage(distBar, p.getDistOnPath()/game.getMap().getPath().length());
		
		g.drawImage(emptyBar, gap, gap, null);
		g.drawImage(emptyBar, gap*2+barWidth, gap, null);
		g.drawImage(emptyBar, gap*3+barWidth*2, gap, null);
		
		g.drawImage(healthBarActual, gap, gap, null);
		g.drawImage(strengthBarActual, gap*2+barWidth, gap, null);
		g.drawImage(distBarActual, gap*3+barWidth*2, gap, null);
		
		g.setColor(Color.white);
		g.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		g.drawString("Health", gap*3, gap+2*height/3);
		g.drawString("Strength", gap*4+barWidth, gap+2*height/3);
		g.drawString("Distance", gap*5+barWidth*2, gap+2*height/3);
		
		

	}
	
	private Image cropBarImage(Image barImage, double ratio) {
		if((int)(barImage.getWidth(null)*ratio) <= 0)
			return null;
		if(ratio > 1)
			ratio = 1;
		BufferedImage bimage = new BufferedImage(barImage.getWidth(null), barImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(barImage, 0, 0, null);
	    bGr.dispose();
	    return bimage.getSubimage(0, 0, (int)(barImage.getWidth(null)*ratio), barImage.getHeight(null));
	}
	
	public static void startWindow(CameraViewer cam) {
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
		graphFrame.setDefaultCloseOperation(graphFrame.EXIT_ON_CLOSE);
		
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
		
		if(!gameRunner.getStarted()) {
			gameRunner.startGameloop();
		}
		
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
