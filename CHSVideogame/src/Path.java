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
	
	public static void main(String[] args) {
		StraightPath path = new StraightPath(new Point(0, 0), new Point(0, 500));
		System.out.println(path.length());
		System.out.println(path.getPos(5));
		
		CurvedPath curve = new CurvedPath(new Point(0, 500), new Point(100, 500), -Math.PI/2);
		System.out.println(curve.length());
		for(double i = 0; i < curve.length(); i+=1) {
			System.out.print("("+curve.getPos(i).getX()+",");
			System.out.print(curve.getPos(i).getY()+"),");
		}
		System.out.println("\n");
		
		Path combinePath = new Path();
		combinePath.addPath(new StraightPath(new Point(0, 0), new Point(0, 500)));
		combinePath.addPath(new CurvedPath(new Point(0, 500), new Point(100, 500), -Math.PI/2));
		combinePath.addPath(new StraightPath(new Point(100, 600), new Point(300, 600)));
		combinePath.addPath(new CurvedPath(new Point(300, 600), new Point(300,700), Math.PI/2));
		combinePath.addPath(new StraightPath(new Point(400, 700), new Point(400, 1000)));
		combinePath.addPath(new CurvedPath(new Point(400, 1000), new Point(300, 1000), Math.PI/2));
		combinePath.addPath(new StraightPath(new Point(300, 1100), new Point(-150, 1100)));
		combinePath.addPath(new CurvedPath(new Point(-150, 1100), new Point(-150, 1000), Math.PI/2));
		combinePath.addPath(new StraightPath(new Point(-250, 1000), new Point(-250, 100)));
		combinePath.addPath(new CurvedPath(new Point(-250, 100), new Point(-350, 100), -Math.PI/2));
		combinePath.addPath(new StraightPath(new Point(-350, 0), new Point(-800, 0)));
		combinePath.addPath(new CurvedPath(new Point(-800, 0), new Point(-800, 100), -Math.PI/2));
		combinePath.addPath(new StraightPath(new Point(-900, 100), new Point(-900, 800)));
		
		System.out.println(combinePath.length());
//		System.out.println(combinePath.getPos(200));
		
		for(double i = 0; i < combinePath.length(); i+=50) {
			System.out.print(",("+combinePath.getPos(i).getX()+",");
			System.out.print(combinePath.getPos(i).getY()+")");
//			if(combinePath.getPos(i)==null)
//				System.out.println(i);
		}
		System.out.println();
		
	}

	
	
	
}
