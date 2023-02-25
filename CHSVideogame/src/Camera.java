import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Camera {
	
	private int posX;
	private int posY;
	private int cameraWidth;
	private int cameraHeight;
	private double heading;
	private Game game;
	
	Camera(Game gameObj, int setCameraWidth, int setCameraHeight) {
		game = gameObj;
		cameraWidth = setCameraWidth;
		cameraHeight = setCameraHeight;
		heading = 0;
		posX = 0;
		posY = 0;
	}
	
	public int getX() {
		return posX;
	}
	
	public int getY() {
		return posY;
	}
	
	public double getHeading() {
		return heading;
	}
	
	public void setX(int newX) {
		posX = newX;
	}
	
	public void setY(int newY) {
		posY = newY;
	}
	
	public void setHeading(double newHeading) {
		heading = newHeading;
	}
	
	public AffineTransform getTransform() {
		AffineTransform t = new AffineTransform();
		t.translate(-posX+cameraWidth/2,  -posY+cameraHeight/2);
		t.rotate(heading,posX,posY);
		return t;
	}
	
}
