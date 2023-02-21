import java.awt.Image;

public class CheeseCracker extends DisplayObject {
	public CheeseCracker(int x, int y, Game game, Image theImage) {
		super(x,y,game);
		setSprite(theImage);
	}
	public void testForCollision( ) {
		//test for collision from superclass?
		// 5 to be replaced with whatever number
		updateStrength(Game.getPlayer().getStrength() - 5);
	}
 

}