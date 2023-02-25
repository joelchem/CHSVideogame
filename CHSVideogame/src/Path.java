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
	

	public Point getPos(double distOnSegment) {
		if(distOnSegment < 0) {
			return getPos(0);
		} else if(distOnSegment > length()) {
			return getPos(length()-.01);
		}
		double sum = 0;
		for(PathSegment segment : segmentList) {
			if(distOnSegment >= sum && distOnSegment <= sum + segment.length()) {
				return segment.getPos(distOnSegment-sum);
			}
			sum += segment.length();
		}
		return null;
	}


	public Point getPos(double distOnSegment, double strafeOffset) {
		if(distOnSegment < 0) {
			return getPos(0);
		} else if(distOnSegment > length()) {
			return getPos(length()-.01);
		}
		double sum = 0;
		for(PathSegment segment : segmentList) {
			if(distOnSegment > sum && distOnSegment <= sum + segment.length()) {
				return segment.getPos(distOnSegment-sum, strafeOffset);
			}
			sum += segment.length();
		}
		return null;
	}
	
	public static void main(String[] args) {
		StraightPath path = new StraightPath(new Point(0, 0), new Point(10, 10));
		System.out.println(path.length());
		System.out.println(path.getPos(5));
		
		CurvedPath curve = new CurvedPath(new Point(0, 50), new Point(10, 50), -Math.PI/2);
		System.out.println(curve.length());
		for(double i = 0; i < curve.length(); i+=1) {
			System.out.print("("+curve.getPos(i).getX()+",");
			System.out.print(curve.getPos(i).getY()+"),");
		}
		System.out.println("\n");
		
		Path combinePath = new Path();
		combinePath.addPath(new StraightPath(new Point(0, 0), new Point(0, 50)));
		combinePath.addPath(curve);
		combinePath.addPath(new StraightPath(new Point(10, 60), new Point(70, 60)));
		
		System.out.println(combinePath.length());
//		System.out.println(combinePath.getPos(200));
		
		for(double i = 0; i < combinePath.length(); i+=3) {
			System.out.print(",("+combinePath.getPos(i).getX()+",");
			System.out.print(combinePath.getPos(i).getY()+")");
//			if(combinePath.getPos(i)==null)
//				System.out.println(i);
		}
		System.out.println();
		
	}
	
	
}
