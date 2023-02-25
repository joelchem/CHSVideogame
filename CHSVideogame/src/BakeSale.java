import java.awt.Image;

public class BakeSale extends DisplayObject{
	Game theGame;
	public BakeSale(int dimensionX, int dimensionY, Game game, Image sprite) {
		super(game,dimensionX,dimensionY, sprite);
		theGame = game;
	}
	public void onCollision() {
		if (theGame.getPlayer().getMoney()) {
			theGame.getPlayer().updateStrength(5);
		}
	}
}