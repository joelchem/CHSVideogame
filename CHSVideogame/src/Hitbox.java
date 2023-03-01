import java.awt.Point;
import java.util.Arrays;

public class Hitbox {
	
	private Point.Double[] corners;
	private Point.Double center;
	
	private double width;
	private double height;
	private double heading;
	
	Hitbox(Point center, int width, int height, double heading) {
		this.center = new Point.Double((double) center.getX(), (double) center.getY());
		this.width = width;
		this.height = height;
		this.heading = heading;
		populateCorners();
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
	
	public int cornersAmt() {
		return corners.length;
	}
	
	public Point.Double getCorner(int i) {
		return corners[i];
	}
	
	public boolean isColliding(Hitbox other) {
		for(int i = 0; i < cornersAmt(); i++) {
			if(pointWithin(other.getCorner(i))) {
				System.out.println(other.getCorner(i));
				return true;
			} else if(other.pointWithin(getCorner(i))) {
				System.out.println(getCorner(i));
				return true;
			}
		}
		return false;
	}
	
	public boolean pointWithin(Point.Double point) {
	    double minX = Double.POSITIVE_INFINITY;
	    double maxX = Double.NEGATIVE_INFINITY;
	    double minY = Double.POSITIVE_INFINITY;
	    double maxY = Double.NEGATIVE_INFINITY;

	    for (int i = 0; i < cornersAmt(); i++) {
	        if (getCorner(i).getX() < minX) {
	            minX = getCorner(i).getX();
	        }
	        if (getCorner(i).getX() > maxX) {
	            maxX = getCorner(i).getX();
	        }
	        if (getCorner(i).getY() < minY) {
	            minY = getCorner(i).getY();
	        }
	        if (getCorner(i).getY() > maxY) {
	            maxY = getCorner(i).getY();
	        }
	    }

	    if (point.getX() < minX || point.getX() > maxX || point.getY() < minY || point.getY() > maxY) {
	        return false;
	    }

	    double[] crossProducts = new double[4];
	    for (int i = 0; i < cornersAmt(); i++) {
	        double dx1 = getCorner((i + 1) % 4).getX() - getCorner(i).getX();
	        double dy1 = getCorner((i + 1) % 4).getY() - getCorner(i).getY();
	        double dx2 = point.getX() - getCorner(i).getX();
	        double dy2 = point.getY() - getCorner(i).getY();
	        crossProducts[i] = dx1 * dy2 - dx2 * dy1;
	    }
	    
	    return Math.abs(Math.signum(crossProducts[0])+
	    		Math.signum(crossProducts[1])+Math.signum(crossProducts[2])+Math.signum(crossProducts[3]))>3.9;
	    
//	    boolean sameSign = true;
//	    for (int i = 0; i < crossProducts.length; i++) {
//	        if (crossProducts[i] < 0) {
//	            sameSign = false;
//	            break;
//	        }
//	    }
//	    return sameSign;
	}
	
	
}
