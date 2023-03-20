import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
public class CheeseCracker extends DisplayObject {
	private Game game;
	private final int strengthRestored = 5000;
	private boolean exists;
	
	public CheeseCracker(Game game, Map map, int x, int y, double heading) {
		super(game,x, y, heading, 3*map.getScale(),3*map.getScale());
		try {
		    Image im = ImageIO.read(getClass().getClassLoader().getResource("cracker.png")).
					getScaledInstance(getDimensionX(), getDimensionY(), 0);
		    setSprite(im);
		    
		} catch (IOException e) {
			System.out.println("cracker sprites not found.");
		}
		this.game = game;
		this.exists = true;
	}
	
	public void onCollision() {
		Player player = game.getPlayer();
		exists = false; 
		player.setStrength(player.getStrength()+strengthRestored);
		setSprite(null);
	}
	
	public boolean testForCollision() {
		if (exists) 
			return super.testForCollision();
		return false;
	}

}



