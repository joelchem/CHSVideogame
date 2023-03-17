import java.util.*;
import java.awt.*;
import java.awt.geom.*;

public class OncomingStudent extends DisplayObject{
	private ArrayList<Image> students;
	private Game game;
	private int velocity, positionX, positionY, index;
	private Player player;
	//private Path path;
	private ArrayList<OncomingStudent> currentStudents;
	private int offset;
	private double heading;
	private int goal;
	private boolean set;
	private double distOnPath;
	private int dimensionX, dimensionY;
	private int GRAZE_HEALTH, HEAD_ON_HEALTH, AVOID_DISX, AVOID_V; //these will equal something later, placeholder
	//AVOID_V what velocity students change to when splitting
	//AVOID_DIS distance they separate by 
	
<<<<<<< HEAD
	public OncomingStudent(Game g, int posX, int posY, double heading, int dimensionX, int dimensionY) {
		super(g, posX, posY, heading, dimensionX, dimensionY);
		game = g;
		positionX = posX;
		positionY = posY;
		player = g.getPlayer();
		index = (int) (Math.random()*students.size());
		//path = g.getMap().getPath();
		offset = 0;
=======
	public OncomingStudent(Game g, double distOnPath, int strafe, int vel) {
		super(g, 0, 0, 0, 40*g.getMap().getScale()/25, 80*g.getMap().getScale()/25);
		
		this.game = g;
		this.distOnPath = distOnPath;
		this.strafe = strafe*game.getMap().getScale();
		this.velocity = vel;
		this.targetStrafe = strafe;		
		this.targetStrafe = this.strafe;
		Point pos = g.getMap().getPath().getPos(distOnPath, strafe);
		int spriteNum = (int)(Math.random()*4)+1;
		try {
		    sprites = new Image[] {
		    		ImageIO.read(new File("assets/oncoming"+spriteNum+".png")).getScaledInstance(getDimensionX(), getDimensionY(), 0)
		    };
		} catch (IOException e) {
			System.out.println("Some or all oncoming student sprites not found.");
		}
>>>>>>> 17f24b282b331093330b1b501adcc4a54f5c7245
		
		generateList();
	}
	
	public double getDistOnPath() {
		return distOnPath;
	}
	
	public void setDistOnPath(double dist) {
		distOnPath = dist;
	}
<<<<<<< HEAD
	
	private Point getPoint() {
		return new Point(this.getPositionX(), this.getPositionY());
	}
	
	public void setHeading(double head) {
		heading = head;
	}
	
	public double getHeading() {
		return heading;
	}
	
	public Hitbox getLHitbox() {
		//hitbox for grazes
		return new Hitbox(getPoint(), dimensionX+10, dimensionY+10, heading);
	}
	
	public Hitbox getSepHitbox() {
		//hitbox for detecting whether or not to separate
		return new Hitbox(getPoint(), dimensionX+10, dimensionY, heading);
	}
	
	public void setOffset(int strafe) {
		Map map = game.getMap();
		offset = Math.max(-1*map.getMaxStrafe()*map.getScale(), Math.min(map.getMaxStrafe()*map.getScale(), strafe));
	}
	
	public int getOffset() {
		return offset;
	}
	
	public void setVelocity(int vel) {
		velocity = vel;
	}
	
	public int getVelocity() {
		return velocity;
	}
	
	public int getPositionX() {
		return positionX;
	}
	
	public int getPositionY() {
		return positionY;
	}
	
	public void setPositionX(int x) {
		positionX = x;
	}
	
	public void setPositionY(int y) {
		positionY = y;
	}
	
	//specialize on collision
	
	public void onCollision() {
		super.onCollision();
		if(this.getLHitbox().isColliding(player.getHitbox())) {
			if(this.getHitbox().isColliding(player.getHitbox())){
				player.setHealth(player.getHealth()-HEAD_ON_HEALTH);
			} else {
				player.setHealth(player.getHealth()-GRAZE_HEALTH);
			}
		}	
	}
	
	private void generateList() {
		int i = 0;
		while(game.getOncomingStudents(i)!=null) {
			currentStudents.add(game.getOncomingStudents(i));
		}
=======

	public int getX() {
		return posX;
	}

	public int getY() {
		return posY;
	}

	public void setPosX(int x) {
		posX = x;
	}

	public void setPosY(int y) {
		posY = y;
	}
	
	public void onCollision() {
<<<<<<< HEAD
<<<<<<< HEAD
		if(!game.getPlayer().isInvulnerable())
			game.getPlayer().setHealth(game.getPlayer().getHealth()-1);
>>>>>>> 6fb8592e2edf329363c3174932a130d0bce8ee8e
	}
	
	public void checkProximity() {
		Hitbox studentHB;
		double playerDist = player.getDistOnPath();
		int playerOff = player.getOffset();
		Hitbox thisHB = this.getSepHitbox();
		double target;
		if(currentStudents.size()>=1) {
			for(OncomingStudent student:currentStudents) {
				studentHB = student.getSepHitbox();
				if(studentHB.isColliding(thisHB)) {
					target = ((student.getOffset()+getOffset())/2);
					if(Math.abs(target-playerOff)<=5&&distOnPath-playerDist<=dimensionY+5) {
						set = false;
						student.setSet(false);
						separate(student);
					}
				}
			}
		}
	}
	
	public int getGoal() {
		return goal;
	}
<<<<<<< HEAD
	
	public void setGoal(int x) {
		goal = x;
	}
	
	private void setSet(boolean tOrFalse) {
		set = tOrFalse;
	}
	
	private void separate(OncomingStudent student) {
		int studentOff = student.getOffset();
		int studentGoal;
		//goal is ideally set to half of player's width
		if(set == false) {
			goal = Math.abs(studentOff+AVOID_DISX);
			studentGoal = Math.abs(getOffset()+AVOID_DISX);
			student.setGoal(studentGoal);
			student.setSet(true);
			set = true;
		} else {
			studentGoal = student.getGoal();
		}
		
		if(studentOff>getOffset()) {
			if(Math.abs(studentOff)!=studentGoal&&Math.abs(offset)!=goal) {
				student.setVelocity(AVOID_V);
				setVelocity(-AVOID_V);
			} else {
				student.setVelocity(0);
				setVelocity(0);
			}
		} else {
			student.setVelocity(-AVOID_V);
			setVelocity(AVOID_V);
			if(Math.abs(studentOff)!=studentGoal&&Math.abs(offset)!=goal) {
				student.setVelocity(AVOID_V);
				setVelocity(-AVOID_V);
			} else {
				student.setVelocity(0);
				setVelocity(0);
			}
		}
	}
	
	public Image getImage() {
		return students.get(index);
	}
	
	public static void main(String[]args) {
		
=======
=======
>>>>>>> 17f24b282b331093330b1b501adcc4a54f5c7245
		if(!game.getPlayer().isInvulnerable()) {
			Player p = game.getPlayer();
			
			int healthLost = 10;
			
			if(Math.abs(p.getOffset()-getStrafe())<p.getDimensionX()/2) {
				healthLost +=  10;
			}
			
			if(p.getJacket()) {
				healthLost /= 2;
			}
			
			game.getPlayer().setHealth(game.getPlayer().getHealth()-healthLost);
		}
	}

	
	public int getTargetStrafe() {
		return targetStrafe;
	}
	
	public void setTargetStrafe(int strafe) {
		targetStrafe = strafe;
	}
	
	public void setStrafe(int strafe) {
		Map map = game.getMap();
		int oldOffset = strafe;
		this.strafe = Math.max(-1*map.getMaxStrafe()*map.getScale(), 
				Math.min(map.getMaxStrafe()*map.getScale(), strafe));
	}
	
	public void checkProximity() {
		
		for(int i = 0; i < game.oncomingStudentsAmt(); i++) {
			OncomingStudent student = game.getOncomingStudents(i);
			if(student!=this && student.getStrafe()==student.getTargetStrafe()) {
//				System.out.println("here2");
				int pOff = game.getPlayer().getOffset();
				if(Math.abs(student.getDistOnPath()-getDistOnPath())<getDimensionY()*3
					&& Math.abs(student.getStrafe()-getStrafe())<getDimensionX()*2
					&& (student.getDistOnPath()-game.getPlayer().getDistOnPath())<getDimensionY()*3
					&&((student.getStrafe()<=pOff&&pOff<=getStrafe())
							||(getStrafe()<=pOff&&pOff<=student.getStrafe()))) {
					int thisStrafe = getStrafe();
					int otherStrafe = student.getStrafe();
					
					double mult = Math.signum(getStrafe()-student.getStrafe());
					setTargetStrafe((int)(thisStrafe+getDimensionX()*1.5*mult));
					student.setTargetStrafe((int)(otherStrafe-getDimensionX()*1.5*mult));

				}
				
			}
		}

		
	}
	public Image getSprite() {
		return sprites[0];
>>>>>>> 17f24b282b331093330b1b501adcc4a54f5c7245
	}
}

=======
}
>>>>>>> 6fb8592e2edf329363c3174932a130d0bce8ee8e
