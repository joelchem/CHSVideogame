import java.awt.Point;
import java.awt.geom.Line2D;

public class MotionHitbox extends Hitbox {
	private Line2D.Double trail;
	
	MotionHitbox(Point center, int width, int height, double heading, Point prev) {
		super(center, width, height, heading);
		trail = new Line2D.Double(center, prev);
	}
	
	public int linesAmt() {
		return super.linesAmt()+1;
	}
	
	public Line2D.Double getLine(int i) {
		if(i==super.linesAmt()) {
			return trail;
		}
		return super.getLine(i);
	}
	
}
