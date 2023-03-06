import java.awt.Point;

public class CurvedPath extends PathSegment{
	
	private Point center;
	private Point start;
	private double curveAngle;
	private double startAngle;
	
	private boolean reverse;
	
	CurvedPath(Point curveStart, Point curveCenter, double angle) {
		center = curveCenter;
		start = curveStart;
		curveAngle = angle;
		startAngle = Math.atan((start.getY()-center.getY())/(start.getX()-center.getX()));
		if((start.getX()-center.getX())<0) {
			startAngle += Math.PI;
		}
		
		reverse = false;
		
//		System.out.print(this+" ");
//		System.out.println(heading(0));
		
	}
	
	CurvedPath(Point curveStart, Point curveCenter, double angle, boolean reverseFix) {
		this(curveStart, curveCenter, angle);
		reverse = true;
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
			return getPos(0, strafeOffset);
		} else if(distOnSegment > length()) {
			return getPos(length()-.01, strafeOffset);
		}
		double newAngle = curveAngle*distOnSegment/length() + startAngle;
		
		double radius = radius()+Math.signum(curveAngle)*strafeOffset;
		
		
		return new Point((int)(radius*Math.cos(newAngle)+center.getX()),
				(int)(radius*Math.sin(newAngle)+center.getY()));
	}
	
	private Point.Double accurateGetPos(double distOnSegment) {
		double newAngle = curveAngle*distOnSegment/length() + startAngle;
		
		double radius = radius()+Math.signum(curveAngle);
		
		
		return new Point.Double((radius*Math.cos(newAngle)+center.getX()),
				(radius*Math.sin(newAngle)+center.getY()));
	}
	
	private double straightHeading(Point.Double startPoint, Point.Double endPoint) {
		double distOnSegment = 0;
		if((endPoint.getY()-startPoint.getY())==0.) {
			if(startPoint.getX()<endPoint.getX()) {
				return -Math.PI/2;
			}
			return Math.PI/2;
		} else if((endPoint.getX()-startPoint.getX())==0.) {
			if(endPoint.getY()<startPoint.getY()) {
				return 0;
			}
			return Math.PI;
		} else {
			double angle = Math.atan((endPoint.getY()-startPoint.getY())/(endPoint.getX()-startPoint.getX()));
			if((endPoint.getX()-startPoint.getX())<0) {
				angle += Math.PI;
			}
			return angle;
		}
		
	}
	
	

	public double heading(double distOnSegment) {
	
//		System.out.println(startAngle+" "+curveAngle*distOnSegment/length()+" "+Math.signum(curveAngle)*(Math.PI/2)+" "+(startAngle - curveAngle*distOnSegment/length() + 0* Math.PI));
//		return (startAngle + curveAngle*distOnSegment/length() + Math.signum(curveAngle)*(-Math.PI/2));
		
//	    >>>>>>>>>>>>>> working
		
		if(distOnSegment < 0) {
			return heading(0);
		} else if(distOnSegment > length()) {
			return heading(length()-.01);
		}
		
		double extra = 0;
		if(reverse) {
			extra = Math.PI;
		}
		
		return (startAngle - curveAngle*distOnSegment/length() + extra/* + Math.signum(curveAngle)*(-Math.PI/2)*/);
//		return (startAngle + curveAngle*distOnSegment/length() + Math.signum(curveAngle)*Math.PI/2);
//		>>>>>>>>>>>>>>>>>>>>>>>>>>>
		// it works in 3rd and 6th turn when u add Math.PI oaisdhjfioafsdjoji
//		double closeFrontDist = distOnSegment + 0.000001;
//		if(closeFrontDist > length()) {
//			closeFrontDist = distOnSegment;
//			distOnSegment -= 0.000001;
//		}
//		
//		StraightPath smolPath = new StraightPath(getPos(distOnSegment), getPos(closeFrontDist));
//		return -straightHeading(accurateGetPos(distOnSegment), accurateGetPos(closeFrontDist))-Math.PI;
		
	}
	
}
