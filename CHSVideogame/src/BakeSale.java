import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BakeSale extends DisplayObject{
	Game theGame;

	
	public BakeSale(Game game, int x, int y, double heading) {
		super(game,x, y, heading, 46*3,44*3);
		try {
		    Image im = ImageIO.read(new File("assets/bake_sale.png")).getScaledInstance(getDimensionX(), getDimensionY(), 0);
		    setSprite(im);
		    
		} catch (IOException e) {
			System.out.println("bake sale sprites not found.");
		}
		theGame = game;
	}
	public void onCollision() {
		System.out.println("hello");
	}
}