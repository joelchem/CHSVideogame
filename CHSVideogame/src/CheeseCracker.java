import java.awt.Image;
public class CheeseCracker extends DisplayObject {
	Image cheeseCrackerSprite;
	Game theGame;
	public CheeseCracker(int x, int y, Game game, Image sprite) {
		super(game, x, y, sprite);
		theGame=game;
	}
	public void onCollision() {
		//test for collision from superclass?
		// 5 to be replaced with whatever number
	    theGame.getPlayer().updateStrength(5);
		theGame.removeDisplayObject(0);
	}
}




