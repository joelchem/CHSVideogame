public class Test {

	public static boolean areRotatedRectanglesColliding(double x1, double y1, double width1, double height1,
			double angle1, double x2, double y2, double width2, double height2, double angle2) {

		// Calculate the corners of the first rectangle
		double[] corners1 = getCorners(x1, y1, width1, height1, angle1);

		// Calculate the corners of the second rectangle
		double[] corners2 = getCorners(x2, y2, width2, height2, angle2);

		// Check if any of the corners of rectangle 1 are inside rectangle 2
		for (int i = 0; i < 4; i++) {
			if (isPointInsideRectangle(corners1[i], corners1[i + 1], corners2)) {
				return true;
			}
		}

		// Check if any of the corners of rectangle 2 are inside rectangle 1
		for (int i = 0; i < 4; i++) {
			if (isPointInsideRectangle(corners2[i], corners2[i + 1], corners1)) {
				return true;
			}
		}

		// Check if any of the edges of the two rectangles intersect
		for (int i = 0; i < 4; i++) {
			double x1a = corners1[i];
			double y1a = corners1[i + 1];
			double x1b = corners1[(i + 1) % 4];
			double y1b = corners1[(i + 2) % 4];

			for (int j = 0; j < 4; j++) {
				double x2a = corners2[j];
				double y2a = corners2[j + 1];
				double x2b = corners2[(j + 1) % 4];
				double y2b = corners2[(j + 2) % 4];

				if (doLinesIntersect(x1a, y1a, x1b, y1b, x2a, y2a, x2b, y2b)) {
					return true;
				}
			}
		}

		// If no collision is detected, return false
		return false;
	}

	// Helper method to calculate the corners of a rectangle given its center,
	// width, height, and angle
	public static double[] getCorners(double x, double y, double width, double height, double angle) {
		double[] corners = new double[8];
		double cos = Math.cos(angle);
		double sin = Math.sin(angle);
		double halfWidth = width / 2.0;
		double halfHeight = height / 2.0;

		// Top left corner
		corners[0] = x + (-halfWidth * cos - halfHeight * sin);
		corners[1] = y + (-halfWidth * sin + halfHeight * cos);

		// Top right corner
		corners[2] = x + (halfWidth * cos - halfHeight * sin);
		corners[3] = y + (halfWidth * sin + halfHeight * cos);

		// Bottom right corner
		corners[4] = x + (halfWidth * cos + halfHeight * sin);
		corners[5] = y + (halfWidth * sin - halfHeight * cos);

		// Bottom left corner
		corners[6] = x + (-halfWidth * cos + halfHeight * sin);
		corners[7] = y + (-halfWidth * sin - halfHeight * cos);

		return corners;
	}

	// Helper method to check if a point is inside a rectangle
	public static boolean isPointInsideRectangle(double x, double y, double[] corners) {
		int numVertices = corners.length / 2;
		boolean isInside = false;
		for (int i = 0, j = numVertices - 1; i < numVertices; j = i++) {
			double xi = corners[i * 2];
			double yi = corners[i * 2 + 1];
			double xj = corners[j * 2];
			double yj = corners[j * 2 + 1];

			boolean intersect = ((yi > y) != (yj > y)) && (x < (xj - xi) * (y - yi) / (yj - yi) + xi);
			if (intersect) {
				isInside = !isInside;
			}
		}

		return isInside;
	}

	// Helper method to check if two line segments intersect
	public static boolean doLinesIntersect(double x1a, double y1a, double x1b, double y1b, double x2a, double y2a,
			double x2b, double y2b) {
		double ua = ((x2b - x2a) * (y1a - y2a) - (y2b - y2a) * (x1a - x2a))
				/ ((y2b - y2a) * (x1b - x1a) - (x2b - x2a) * (y1b - y1a));
		double ub = ((x1b - x1a) * (y1a - y2a) - (y1b - y1a) * (x1a - x2a))
				/ ((y2b - y2a) * (x1b - x1a) - (x2b - x2a) * (y1b - y1a));
		return (ua >= 0 && ua <= 1 && ub >= 0 && ub <= 1);
	}

	public static void main(String[] args) {
		
		System.out.println(
				areRotatedRectanglesColliding(-2.5, 0, 4, 4, 0, 2.5, 0, 4, 4, 0)
				);
	}
}
