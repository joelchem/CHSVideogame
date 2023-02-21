import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class CameraViewer extends JPanel{
	
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D g = (Graphics2D) graphics;
	}
	
}
