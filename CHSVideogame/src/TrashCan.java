import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TrashCan extends DisplayObject {
	private Game theGame;
	private boolean exists;
    public TrashCan(Game game,int x, int y, double heading) {
        super(game,x,y,heading, 80,80);
        theGame=game;
        try {
		    Image im = ImageIO.read(new File("assets/trashcan.png")).getScaledInstance(getDimensionX(), getDimensionY(), 0);
		    setSprite(im);
		    
		} catch (IOException e) {
			System.out.println("trashcan sprites not found.");
		}
		this.theGame = game;
		this.exists = true;
    }
    public void onCollision() {
        // implement separate collision test? cuz you have to come up behind the trash can, not bump
        theGame.getPlayer().setCrouch(true);
        // somewhere it will need to stop the movement for while the player is behind the trash can
    }
}
