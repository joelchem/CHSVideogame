import java.awt.Image;

public class CheeseCracker extends DisplayObject {
<<<<<<< Updated upstream
	public CheeseCracker(int x, int y, Game game, Image theImage) {
=======
	Image cheeseCrackerSprite;
	public CheeseCracker(int x, int y, Game game, Image image) {
>>>>>>> Stashed changes
		super(x,y,game);
		setSprite(theImage);
	}
	public void testForCollision( ) {
		//test for collision from superclass?
		// 5 to be replaced with whatever number
		updateStrength(Game.getPlayer().getStrength() - 5);
	}
 

}