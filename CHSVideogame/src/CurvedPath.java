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
		if((start.getX()-center.getX())<0) {
			startAngle += Math.PI;
		}
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
		if(distOnSegment < 0) {
			return getPos(0);
		} else if(distOnSegment > length()) {
			return getPos(length()-.01);
		}
		double newAngle = curveAngle*distOnSegment/length() + startAngle;
		
		double radius = radius()+Math.signum(curveAngle)*strafeOffset;
		
		
		return new Point((int)(radius*Math.cos(newAngle)+center.getX()),
				(int)(radius*Math.sin(newAngle)+center.getY()));
	}

	public double heading(double distOnSegment) {
		System.out.println(startAngle+" "+curveAngle*distOnSegment/length()+" "+Math.signum(curveAngle)*(Math.PI/2)+" "+(startAngle - curveAngle*distOnSegment/length() + 0* Math.PI));
//		return (startAngle + curveAngle*distOnSegment/length() + Math.signum(curveAngle)*(-Math.PI/2));

		return (startAngle - curveAngle*distOnSegment/length() + 0*Math.PI/* + Math.signum(curveAngle)*(-Math.PI/2)*/);
		
		// it works in 3rd and 6th turn when u add Math.PI oaisdhjfioafsdjoji
		
	}
	
}
