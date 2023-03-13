import java.awt.Point;

public class Test {
	
	public static boolean isPointInsideQuadrilateral(double x, double y, double[] quadX, double[] quadY) {
	    // Check if the point is inside the bounds of the quadrilateral
	    double minX = Double.POSITIVE_INFINITY;
	    double maxX = Double.NEGATIVE_INFINITY;
	    double minY = Double.POSITIVE_INFINITY;
	    double maxY = Double.NEGATIVE_INFINITY;

	    for (int i = 0; i < 4; i++) {
	        if (quadX[i] < minX) {
	            minX = quadX[i];
	        }
	        if (quadX[i] > maxX) {
	            maxX = quadX[i];
	        }
	        if (quadY[i] < minY) {
	            minY = quadY[i];
	        }
	        if (quadY[i] > maxY) {
	            maxY = quadY[i];
	        }
	    }

	    if (x < minX || x > maxX || y < minY || y > maxY) {
	        return false;
	    }

	    // Calculate the cross products of each pair of adjacent edges
	    double[] crossProducts = new double[4];
	    for (int i = 0; i < 4; i++) {
	        double dx1 = quadX[(i + 1) % 4] - quadX[i];
	        double dy1 = quadY[(i + 1) % 4] - quadY[i];
	        double dx2 = x - quadX[i];
	        double dy2 = y - quadY[i];
	        crossProducts[i] = dx1 * dy2 - dx2 * dy1;
	    }

	    // If the signs of the cross products are all the same, the point is inside the quadrilateral
	    return Math.abs(Math.signum(crossProducts[0])+
	    		Math.signum(crossProducts[1])+Math.signum(crossProducts[2])+Math.signum(crossProducts[3]))>3.9;
//	    for (int i = 0; i < 4; i++) {
//	        if (crossProducts[i] < 0) {
//	            sameSign = false;
//	            break;
//	        }
//	    }
	    if (sameSign) {
	        return true;
	    }

	    // If the signs of the cross products are not all the same, the point is outside the quadrilateral
	    return false;
	}
	
	public static Point.Double[] findRotatedRectangleCorners(Point.Double center, double heading, double width, double height) {
	    Point.Double[] corners = new Point.Double[4];
	    double halfWidth = width / 2;
	    double halfHeight = height / 2;
	    double sinHeading = Math.sin(heading);
	    double cosHeading = Math.cos(heading);

	    // Calculate the four corners of the rectangle
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

	    return corners;
	}
	
	public static void main(String[] args) {
		
//		double[] quadX = {1.0, 3.0, 2.0, 0.0};
//		double[] quadY = {1.0, 2.0, 4.0, 3.0};
//		double x = .2;
//		double y = 2.6;
//		boolean inside = isPointInsideQuadrilateral(x, y, quadX, quadY);
//		System.out.println(inside); // prints "true"
//		
//		Point.Double[] points = findRotatedRectangleCorners(
//				new Point.Double(2, 2),
//				-Math.PI/5,
//				10,
//				10
//		);
//		for(Point.Double a : points) {
//			System.out.print(",("+a.getX()+","+a.getY()+")");
//		}
		
		Hitbox h1 = new Hitbox(new Point(-25,0), 40, 40, Math.PI/4);
		Hitbox h2 = new Hitbox(new Point(25,0), 40, 40, Math.PI/4);
		
		for(int i = 0; i < h1.cornersAmt(); i++) {
			Point.Double c1 = h1.getCorner(i);
			Point.Double c2 = h2.getCorner(i);
			System.out.print(",("+c1.getX()+","+c1.getY()+")");
			System.out.print(",("+c2.getX()+","+c2.getY()+")");
		}
		
		System.out.println("\n"+h1.isColliding(h2)+" "+h2.isColliding(h1));
	}
}
