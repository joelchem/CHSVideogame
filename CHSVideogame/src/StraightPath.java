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
		Point linePoint = getPos(distOnSegment);
		if((endPoint.getY()-startPoint.getY())==0.) {
			if(startPoint.getX()<endPoint.getX()) {
				strafeOffset *= -1;
			}
			return new Point((int)linePoint.getX(), (int)(linePoint.getY()+strafeOffset));
		} else if((endPoint.getX()-startPoint.getX())==0.) {
			if(endPoint.getY()<startPoint.getY()) {
				strafeOffset *= -1;
			}
			return new Point((int)(linePoint.getX()+strafeOffset), (int)linePoint.getY());
		} else {
			double perpSlope = -(endPoint.getX()-startPoint.getX())/(endPoint.getY()-startPoint.getY());
			double mult = strafeOffset/Math.sqrt(1+Math.pow(perpSlope, 2));
			return new Point((int)(linePoint.getX()+mult),(int)(linePoint.getY()+mult*perpSlope));
		}
	}

	public double heading(double distOnSegment) {
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
			double angle = Math.atan((endPoint.getY()-startPoint.getY())/(endPoint.getX()
					-startPoint.getX()));
			if((endPoint.getX()-startPoint.getX())<0) {
				angle += Math.PI;
			}
			return angle;
		}
	}
}
