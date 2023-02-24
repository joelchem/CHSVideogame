import java.awt.Image;

public class Dollar extends DisplayObject{
	Game theGame;
	public Dollar(int dimensionX, int dimensionY, Game game, Image sprite) {
		super(game,dimensionX,dimensionY, sprite);
		theGame = game;
	}
	
	public void onCollision() {
    	theGame.getPlayer().updateMoney(true);
    }

}

