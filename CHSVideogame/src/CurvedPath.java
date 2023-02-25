import java.awt.Point;

public class CurvedPath extends PathSegment{
	
	private Point center;
	private Point start;
	private double curveAngle;
	private double startAngle;
	
	CurvedPath(Point curveStart, Point curveCenter, double angle) {
		center = curveCenter;
		start = curveStart;
		curveAngle = angle;
		startAngle = Math.atan((start.getY()-center.getY())/(start.getX()-center.getX()));
		
	}
	
	private double radius() {
		return center.distance(start);
	}
	
	public double length() {
		return Math.abs(radius()*curveAngle);
	}
	
	public Point getPos(double distOnSegment) {
		if(distOnSegment < 0) {
			return getPos(0);
		} else if(distOnSegment > length()) {
			return getPos(length()-.01);
		}
		double newAngle = curveAngle*distOnSegment/length() + startAngle;
		
		return new Point((int)(radius()*Math.cos(newAngle)+center.getX()),
				(int)(radius()*Math.sin(newAngle)+center.getY()));
	}
	@Override
	public Point getPos(double distOnSegment, double strafeOffset) {
		// TODO: do this
		
		return getPos(distOnSegment);
	}
	
}
