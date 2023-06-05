import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TrashCan extends DisplayObject {
	private Game theGame;
	private int distOnPath;
    TrashCan(Game game, Map map, int distance, int offset, double heading) {
//    	super(game,distance, offset, heading, 3*map.getScale(),4*map.getScale());
		super(game, (int) map.getPath().getPos(distance, offset).getX(),
				(int) map.getPath().getPos(distance, offset).getY(),
				heading, 3*map.getScale(),4*map.getScale());
//		
		distOnPath = distance;
		try {
		    Image im = ImageIO.read(getClass().getClassLoader().getResource("trash-can.png")).
					getScaledInstance(getDimensionX(), getDimensionY(), 0);
		    setSprite(im);
		    
		} catch (IOException e) {
			System.out.println("trash sprites not found.");
		}
		this.theGame = game;
	}
	
	public boolean testForCollision() {
		Player p = theGame.getPlayer();
		boolean collisionResult = super.testForCollision();
		collisionResult = collisionResult && p.getDistOnPath() < distOnPath;
		if(!collisionResult
				&& p.getCrouch()) {
			p.setCrouch(false);
			p.setVelocity(p.getDefaultVelocity());
		}
		
		for(int i = 0; i < theGame.oncomingStudentsAmt(); i++) {
			OncomingStudent student = theGame.getOncomingStudents(i);
			if(student.getHitbox().isColliding(getHitbox())) {
				
				student.setTargetStrafe((int)(student.getStrafe()-Math.signum(student.getStrafe())
						*getDimensionX()));
			
			}
		}
		
		return collisionResult;
	}
	
    public void onCollision() {
        theGame.getPlayer().setCrouch(true);
        theGame.getPlayer().setVelocity(0);
    }
}
