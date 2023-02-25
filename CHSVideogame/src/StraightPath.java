import java.awt.Point;

public class StraightPath extends PathSegment{
	
	private Point startPoint;
	private Point endPoint;
	
	StraightPath(Point start, Point end) {
		startPoint = start;
		endPoint = end;
	}

	public double length() {
		return startPoint.distance(endPoint);
	}

	public Point getPos(double distOnSegment) {
		if(distOnSegment < 0) {
			return getPos(0);
		} else if(distOnSegment > length()) {
			return getPos(length()-.01);
		}
		double segmentFraction = distOnSegment/length();
		Point outputPos = new Point(
				(int)((endPoint.getX()-startPoint.getX())*segmentFraction+startPoint.getX()),
				(int)((endPoint.getY()-startPoint.getY())*segmentFraction+startPoint.getY())
		);
		return outputPos;
	}

	public Point getPos(double distOnSegment, double strafeOffset) {
		// TODO: fix this
		return getPos(distOnSegment);
	}
	
	
	
}
