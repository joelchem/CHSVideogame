import java.awt.Image;

public class CheeseCracker extends DisplayObject {
	Image cheeseCrackerSprite;
	public CheeseCracker(int x, int y, Game game, Image image) {
		super(x,y,game);
		cheeseCrackerSprite =image;
	}
	public void testForCollision( ) {
		//test for collision - from superclass?
		// 5 to be replaced with whatever number
		updateStrength(getStrength() - 5);
	}
 

}