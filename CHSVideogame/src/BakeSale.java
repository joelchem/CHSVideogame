import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BakeSale extends DisplayObject{
	Game theGame;

	private final int healthRestored = 5;
	
	public BakeSale(Game game, Map map, int x, int y, double heading) {
		super(game,x, y, heading, (int)(6*map.getScale()),(int)(5*map.getScale()));
		try {
		    Image im = ImageIO.read(new File("assets/bake_sale.png")).getScaledInstance(getDimensionX(), getDimensionY(), 0);
		    setSprite(im);
		    
		} catch (IOException e) {
			System.out.println("bake sale sprites not found.");
		}
		theGame = game;
	}
	public void onCollision() {
		if(theGame.getPlayer().getMoney()) {
			int healthCurrent = theGame.getPlayer().getHealth();
			theGame.getPlayer().setHealth(healthRestored+healthCurrent);
			theGame.getPlayer().updateMoney(false);;
		}
	}
}