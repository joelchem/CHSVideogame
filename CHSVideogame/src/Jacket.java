import java.awt.Image;

public class Jacket {
	Image jacketSprite;
	public Jacket(int x, int y, Game game, Image image) {
		super(x,y,game);
		jacketSprite =image;
	}
	public void testForCollision() {
		//test for collision - from superclass?
		// 5 to be replaced with whatever number
		updateStrength(getStrength() - 5);
	}

}