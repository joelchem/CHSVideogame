import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class WaterFountain extends DisplayObject {
	//To be changed
	final static int health_restored = 5;
	private Game game;
	private boolean used;
	
	WaterFountain(Game game, int x, int y, double heading) {
		super(game,x, y, heading, 26*6,26*6);
		try {
		    Image im = ImageIO.read(new File("assets/water_fountain.png")).getScaledInstance(getDimensionX(), getDimensionY(), 0);
		    setSprite(im);
		    
		} catch (IOException e) {
			System.out.println("water foundtain sprites not found.");
		}
		this.game = game;
		this.used = false;
	}
	
	public void testForCollision() {
		if (!used) 
			super.testForCollision();
	}
	
	public void onCollision() {
		Player player = game.getPlayer();
		player.setHealth(player.getHealth() + health_restored);
		used = true;
	}
	
}