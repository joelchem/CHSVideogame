import java.awt.Point;

abstract public class PathSegment {

	abstract public double length();

	abstract public Point getPos(double distOnSegment);

	abstract public Point getPos(double distOnSegment, double strafeOffset);

	abstract public double heading(double distOnSegment);

}
