import java.awt.Image;

public class Dollar extends DisplayObject{
	Image DollarSprite;
	public Dollar(int x, int y, Game game, Image image) {
		super(x,y,game);
		DollarSprite = image;
	}
	
	//probably going to be changed to a specialization of on collision. 
	public void testForCollision() {
		
	}

}