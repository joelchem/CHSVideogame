import java.awt.Image;
public class CheeseCracker extends DisplayObject {
	private Game game;
	private final int cheese_cracker_increase = 5;
	private boolean exists;
	
	public CheeseCracker(int x, int y, Game game, Image sprite) {
		super(game, x, y, sprite);
		this.game = game;
		exists = true;
	}
	
	public void onCollision() {
		Player player = game.getPlayer();
		if (exists) {
			exists = false; 
			player.updateStrength(cheese_cracker_increase);
			setSprite(null);
		}
	}
	
	public void testForCollision() {
		if (exists) 
			super.testForCollision();
	}

}




