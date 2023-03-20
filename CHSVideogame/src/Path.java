import java.awt.Point;
import java.util.ArrayList;

public class Path extends PathSegment{
	private ArrayList<PathSegment> segmentList;
	
	Path() {
		segmentList = new ArrayList<PathSegment>();
	}
	
	public void addPath(PathSegment newSegment) {
		segmentList.add(newSegment);
	}
	
	public double length() {
		double sum = 0;
		for(PathSegment segment : segmentList) {
			sum += segment.length();
		}
		return sum;
	}
	
	private PathSegment segmentAtDist(double distOnSegment) {
		if(distOnSegment < 0) {
			return segmentAtDist(0);
		} else if(distOnSegment > length()) {
			return segmentAtDist(length()-.01);
		}
		double sum = 0;
		for(PathSegment segment : segmentList) {
			if(distOnSegment >= sum && distOnSegment <= sum + segment.length()) {
				return segment;
			}
			sum += segment.length();
		}
		return null;
	}
	
	private double sumBeforeSegment(PathSegment seg) {
		double sum = 0;
		for(PathSegment segment : segmentList) {
			if(segment==seg) {
				return sum;
			}
			sum += segment.length();
		}
		return 0.;
	}

	public Point getPos(double distOnSegment) {
		PathSegment targetSeg = segmentAtDist(distOnSegment);
		return targetSeg.getPos(distOnSegment-sumBeforeSegment(targetSeg));
	}


	public Point getPos(double distOnSegment, double strafeOffset) {
		PathSegment targetSeg = segmentAtDist(distOnSegment);
		return targetSeg.getPos(distOnSegment-sumBeforeSegment(targetSeg), strafeOffset);
	}

	public double heading(double distOnSegment) {
		PathSegment targetSeg = segmentAtDist(distOnSegment);
		return targetSeg.heading(distOnSegment-sumBeforeSegment(targetSeg));
		
	}
	
	public boolean isCurve(double distOnSegment) {
		PathSegment targetSeg = segmentAtDist(distOnSegment);
		return targetSeg instanceof CurvedPath;
	}
	
}
