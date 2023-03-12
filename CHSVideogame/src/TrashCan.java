import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TrashCan extends DisplayObject {
    Game theGame;
    TrashCan(Game game, Map map, int x, int y, double heading) {
		super(game,x, y, heading, 17*4,26*4);
		try {
		    Image im = ImageIO.read(new File("assets/trash-can.png")).getScaledInstance(getDimensionX(), getDimensionY(), 0);
		    setSprite(im);
		    
		} catch (IOException e) {
			System.out.println("trash  sprites not found.");
		}
		this.theGame = game;
	}
	
	public boolean testForCollision() {
		Player p = theGame.getPlayer();
		boolean collisionResult = super.testForCollision();
		if(!collisionResult
				&& p.getCrouch()) {
			p.setCrouch(false);
			p.setVelocity(p.getDefaultVelocity());
		}
		return collisionResult;
	}
	
	public Hitbox getHitbox() {
    	return new Hitbox(new Point(getX(), getY()), getDimensionX(), getDimensionY(), getHeading());
    }
	
    public void onCollision() {
        // implement separate collision test? cuz you have to come up behind the trashcan, not bump
        theGame.getPlayer().setCrouch(true);
        // somewhere it will need to stop the movement for while the player is behind the trashcan
        theGame.getPlayer().setVelocity(0);
    }
}
