import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;
import java.awt.geom.NoninvertibleTransformException;
import java.util.Arrays;

public class Hitbox {
	
	private Point.Double[] corners;
	private Line2D.Double[] lines;
	private Point.Double center;
	
	private double width;
	private double height;
	private double heading;
	
	Hitbox(Point center, int width, int height, double heading) {
		this.center = new Point.Double((double) center.getX(), (double) center.getY());
		this.width = width;
		this.height = height;
		this.heading = heading;
		populateLines();
	}
	
	private void populateLines() {
		
		populateCorners();
		
		int lineAmt = corners.length;
		lines = new Line2D.Double[lineAmt];
		for(int i = 0; i < lineAmt; i++) {
			lines[i] = new Line2D.Double(corners[i], corners[(i+1)%lineAmt]);
		}
	}
	
	private void populateCorners() {
	    Point.Double[] corners = new Point.Double[4];
	    double halfWidth = width / 2;
	    double halfHeight = height / 2;
	    double sinHeading = Math.sin(heading);
	    double cosHeading = Math.cos(heading);

	    corners[0] = new Point.Double(
	        center.x - halfWidth * cosHeading - halfHeight * sinHeading,
	        center.y + halfWidth * sinHeading - halfHeight * cosHeading
	    );
	    corners[1] = new Point.Double(
	        center.x + halfWidth * cosHeading - halfHeight * sinHeading,
	        center.y - halfWidth * sinHeading - halfHeight * cosHeading
	    );
	    corners[2] = new Point.Double(
	        center.x + halfWidth * cosHeading + halfHeight * sinHeading,
	        center.y - halfWidth * sinHeading + halfHeight * cosHeading
	    );
	    corners[3] = new Point.Double(
	        center.x - halfWidth * cosHeading + halfHeight * sinHeading,
	        center.y + halfWidth * sinHeading + halfHeight * cosHeading
	    );

	    this.corners = corners;
	}
	
	public int linesAmt() {
		return lines.length;
	}
	
	public int cornersAmt() {
		return corners.length;
	}
	
	public Line2D.Double getLine(int i) {
		return lines[i];
	}
	
	public Point.Double getCorner(int i) {
		return new Point.Double(Math.round(corners[i].getX()*1000)/1000, Math.round(corners[i].getY()*1000)/1000);
	}
	
	public void render(Graphics2D g) {
		AffineTransform ogTransform = g.getTransform();
		try {
			g.transform(ogTransform.createInverse());
		} catch (NoninvertibleTransformException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < linesAmt(); i++) {
			Line2D.Double l = getLine(i);
			g.setColor(Color.RED);
			g.drawLine((int)l.getX1(), (int)l.getY1(), (int)l.getX2(), (int)l.getY2());
		}
		
		g.setTransform(ogTransform);
		
	}
	
	public boolean isColliding(Hitbox other) {
		for(int i = 0; i < lines.length; i++) {
			for(int j = 0; j < other.linesAmt(); j++) {
				if(lines[i].intersectsLine(other.getLine(j))) {
					return true;
				}
			}
		}
		return false;
	}
}
