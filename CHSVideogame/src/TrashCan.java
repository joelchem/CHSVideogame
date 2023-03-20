import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TrashCan extends DisplayObject {
	Game theGame;
    TrashCan(Game game, Map map, int x, int y, double heading) {
		super(game,x, y, heading, 3*map.getScale(),4*map.getScale());

		try {
		    Image im = ImageIO.read(getClass().getClassLoader().getResource("trash-can.png")).getScaledInstance(getDimensionX(), getDimensionY(), 0);
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
		
		for(int i = 0; i < theGame.oncomingStudentsAmt(); i++) {
			OncomingStudent student = theGame.getOncomingStudents(i);
			if(student.getHitbox().isColliding(getHitbox())) {
				
				student.setTargetStrafe((int)(student.getStrafe()-Math.signum(student.getStrafe())*getDimensionX()));
			
			}
		}
		
		return collisionResult;
	}
	
	public Hitbox getHitbox() {
    	return new Hitbox(new Point(getX(), getY()), getDimensionX(), getDimensionY(), getHeading());

    }
	
    public void onCollision() {
        // implement separate collision test? cuz you have to come up behind the trash can, not bump
        theGame.getPlayer().setCrouch(true);
        // somewhere it will need to stop the movement for while the player is behind the trash can

        // somewhere it will need to stop the movement for while the player is behind the trashcan
        theGame.getPlayer().setVelocity(0);

    }
}
