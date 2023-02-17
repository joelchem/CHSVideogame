
public class Camera {
	
	private int positionX;
	private int positionY;
	private double heading;
	private Game game;
	
	Camera(Game gameObj) {
		game = gameObj;
		heading = 0;
		positionX = 0;
		positionY = 0;
	}
	
}
