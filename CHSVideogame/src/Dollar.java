import java.awt.Image;

public class Dollar extends DisplayObject{
	Game game;
	private boolean exists;
	public Dollar(int dimensionX, int dimensionY, Game game, Image sprite) {
		super(game,dimensionX,dimensionY, sprite);
		this.game = game;
		exists = true;
		
	}
	
	public void onCollision() {
    	Player player = game.getPlayer();
		if (exists) {
			exists = false; 
			player.updateMoney(true);
			setSprite(null);
		}
    }
	
	public void testForCollision() {
		if (exists) 
			super.testForCollision();
	}
}
