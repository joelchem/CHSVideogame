import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Camera {
	private int positionX;
	private int positionY;
	private double heading;
	private Game game;
	Camera(Game gameObj) {
		game = gameObj;
		heading = 0;
		position = new Point(10,12);
	}
	
//	public Point screenCoords(Point absoluteCoords) {
//		
//	}
	
	public Point getPos() {
		return position;
	}
	
	public static void main(String[] args) {
		int cameraX = -8;
		int cameraY = 5;
		double heading = -Math.PI/4;
		
		int pointX = -6;
		int pointY = 3;
		
		AffineTransform t = new AffineTransform();
		t.translate(-cameraX, -cameraY);
		t.rotate(heading,cameraX,cameraY);
		System.out.println(t.transform(new Point(pointX, pointY), new Point.Double()));
		

	}
	
}
