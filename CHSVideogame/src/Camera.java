import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Camera {
	private int posX;
	private int posY;
	private final int cameraWidth = 1000;
	private final int cameraHeight = 1000;
	private double heading;
	private Game game;
	
	Camera(Game gameObj) {
		game = gameObj;

	}
	private Point position;
	private Camera() {     
		heading = 0;
		posX = 0;
		posY = 0;
	}
	
	public Point getPos() {
		return position;
	}
	
	public int getX() {
		return posX;
	}
	
	public int getY() {
		return posY;
	}
	
	public int getDimX() {
		return cameraWidth;
	}
	
	public int getDimY() {
		return cameraHeight;
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
	
	public AffineTransform getObjectTransform(
			Point pos, double heading, int dimX, int dimY) {
		double diag = Math.hypot((double)dimX, (double)dimY)/2;
		double innerAngle = Math.atan((double)dimY/(double)dimX);
		
		double offsetX = diag*Math.cos(innerAngle-heading);
		double offsetY = diag*Math.sin(innerAngle-heading);
		
		int pointX = -6;
		int pointY = 3;

		AffineTransform t = new AffineTransform();
		t.translate(pos.getX()-offsetX, pos.getY()-offsetY);
		t.rotate(-heading);
		return t;
	}
	
}
