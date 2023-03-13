import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Dollar extends DisplayObject{
	private Game game;
	private boolean exists;
	Dollar(Game game, Map map, int x, int y, double heading) {
		super(game,x, y, heading, 3*map.getScale(),(int)(2.4*map.getScale()));
		try {
		    Image im = ImageIO.read(new File("assets/dollar.png")).getScaledInstance(getDimensionX(), getDimensionY(), 0);
		    setSprite(im);
		    
		} catch (IOException e) {
			System.out.println("dollar sprites not found.");
		}
		this.game = game;
		this.exists = true;
	}
	
	public void onCollision() {
    	Player player = game.getPlayer();
		exists = false; 
		player.updateMoney(true);
		setSprite(null);
    }
	
	public boolean testForCollision() {
		if (exists) 
			return super.testForCollision();
		return false;
	}
	
	
	
}
