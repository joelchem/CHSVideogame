import java.awt.Image;

public class WaterFountain extends DisplayObject {
	//To be changed
	final static int health_restored = 5;
	private Image fountainSprite;
	private Game game;

	WaterFountain(Game userGame, int dimensionX, int dimensionY, Image image) {
		super(userGame, dimensionX, dimensionY);
		game = userGame;
		fountainSprite = image;
	}
	
	public void onCollision() {
		Player player = game.getPlayer();
		player.setHealth(player.getHealth() + health_restored);
	}
	
}