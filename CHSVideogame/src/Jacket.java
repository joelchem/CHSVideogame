import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Jacket extends DisplayObject{
	private Game game;
	private boolean exists;
	Jacket(Game game, Map map, int x, int y, double heading) {
		super(game,x, y, heading, 3*map.getScale(),3*map.getScale());
		try {
		    Image im = ImageIO.read(getClass().getClassLoader().getResource("jacket.png")).getScaledInstance(getDimensionX(), getDimensionY(), 0);
		    setSprite(im);
		    
		} catch (IOException e) {
			System.out.println("jacket sprites not found.");
		}
		this.game = game;
		this.exists = true;
	}
	
	public boolean testForCollision() {
		if (exists) 
			return super.testForCollision();
		return false;
	}

    public void onCollision() {
        game.getPlayer().setJacket(true);
        exists = false;
        setSprite(null);
    }
}