import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
public class CheeseCracker extends DisplayObject {
	private Game game;
	private final int strengthRestored = 5;
	private boolean exists;
	
	public CheeseCracker(Game game, int x, int y, double heading) {
		super(game,x, y, heading, 80,80);
		try {
		    Image im = ImageIO.read(new File("assets/cracker.png")).getScaledInstance(getDimensionX(), getDimensionY(), 0);
		    setSprite(im);
		    
		} catch (IOException e) {
			System.out.println("carcker sprites not found.");
		}
		this.game = game;
		this.exists = true;
	}
	
	public void onCollision() {
		Player player = game.getPlayer();
		exists = false; 
		player.setStrength(player.getHealth()+strengthRestored);
		setSprite(null);
	}
	
	public boolean testForCollision() {
		if (exists) 
			return super.testForCollision();
		return false;
	}

}



