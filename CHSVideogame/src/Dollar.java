import java.awt.Image;

public class Dollar extends DisplayObject{
	Image DollarSprite;
	Game theGame;
	public Dollar(int dimensionX, int dimensionY, Game game, Image image) {
		super(game,dimensionX,dimensionY);
		DollarSprite = image;
		theGame = game;
	}
	
	public void onCollision() {
    	theGame.getPlayer().updateMoney(true);
    }
}