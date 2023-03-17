import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class WaterFountain extends DisplayObject {
	//To be changed
	final static int health_restored = 30;
	private Game game;
	private boolean used;
	
	WaterFountain(Game game, Map map, int x, int y, double heading) {
		super(game,x, y, heading, 4*map.getScale(),4*map.getScale());
		try {
		    Image im = ImageIO.read(new File("assets/water_fountain.png")).getScaledInstance(getDimensionX(), getDimensionY(), 0);
		    setSprite(im);
		    
		} catch (IOException e) {
			System.out.println("water foundtain sprites not found.");
		}
		this.game = game;
		this.used = false;
	}
	
	public boolean testForCollision() {
		if (!used) 
			return super.testForCollision();
		return false;
	}
	
	public void onCollision() {
		Player player = game.getPlayer();
		player.setHealth(player.getHealth() + health_restored);
		used = true;
	}
	
}