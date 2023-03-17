import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player {
	
	private int strength, health;
	private boolean isCrouching;
	private int velocity;
	private Image[] sprites; // still need sprites
	private Image crouchSprite;
	private Image damageSprite;
	private int positionX, positionY;
	private int prevPosX, prevPosY;
	private int offset;
	private int spriteFrame;
	private double distOnPath;
	private Game game;
	private boolean money;
	private boolean hasJacket;
	private double heading;
	private int dimX;
	private int dimY;
	
	private final int maxHealth = 100;
	private final int maxStrength = 31000;
	private final int defaultVel = 20;

	private long lastMovement;
	private long lastHit;
	
	
	public Player(Game g) {
		game = g;
		dimX = 40*g.getMap().getScale()/25;
		dimY = 80*g.getMap().getScale()/25;
		try {
		    sprites = new Image[] {
		    		ImageIO.read(new File("assets/player_back1.png")).getScaledInstance(dimX, dimY, 0)
		    };
		    crouchSprite = ImageIO.read(new File("assets/player_crouch.png")).getScaledInstance(dimX, dimY, 0);
		    damageSprite = ImageIO.read(new File("assets/player_damage1.png")).getScaledInstance(dimX, dimY, 0);
		} catch (IOException e) {
			System.out.println("Some or all player sprites not found.");
		}
		
		spriteFrame = 0;
		health = 100;
		strength = 31000;
		isCrouching = false;
		velocity = defaultVel;
		positionX = 0;
		positionY = 0;
		offset = 0;
		distOnPath = 50;
		money = false;
		hasJacket = false;
		lastHit = 0;
		lastMovement=0;
	}
	
	public boolean isInvulnerable() {
		long diff = System.currentTimeMillis()%1000000-lastHit%1000000;
//		System.out.println(diff);
		return diff < 500;
	}
	
	public int getDefaultVelocity() {
		return defaultVel;
	}
	
	public Hitbox getHitbox() {
		return new MotionHitbox(new Point(positionX, positionY), dimX, dimY, heading, new Point(prevPosX, prevPosY));
	}
	
	public void setHeading(double newHead) {
		heading = newHead;
		if(game.getMap().getPath().isCurve(distOnPath)) {
			lastMovement = System.currentTimeMillis();
		}
	}
	
	public double getHeading() {
		return game.getMap().getPath().heading(getDistOnPath());
	}
	
	public double getDistOnPath() {
		return distOnPath;
	}
	
	public void setDistOnPath(double newDist) {
		distOnPath = newDist;
	}
	
	public Image getSprite() {
		if(getCrouch()) {
			return crouchSprite;
		}
		if(isInvulnerable() && ((int)(System.currentTimeMillis()-lastHit)/100)%2==0) {
			return damageSprite;
		}
		return sprites[spriteFrame];
	}
	
	public int getDimensionX() {
		return dimX;
	}
	
	public int getDimensionY() {
		return dimY;
	}
	
	public void updateMoney(boolean b) {
//		System.out.println("player money changed: "+money+" to "+b);
		money = b;
	}
	
	public boolean getMoney() {
		return money;
	}
	
	public void setVelocity(int x) {
		velocity = x;
	}
	
	public int getVelocity() {
		double timeDelta = ((System.currentTimeMillis()-lastMovement)/1000.);
		if(lastMovement == 0)
			timeDelta = 0;
		return (int) (velocity*(1+timeDelta/2)*game.getMap().getScale());
	}
	
	public void setCrouch(boolean b) {
//		System.out.println("player crocuh changed: "+isCrouching+" to "+b);
		isCrouching = b;
	}
	
	public boolean getCrouch() {
		return isCrouching;
	}
	
	public void setHealth(int x) {
		x = Math.min(maxHealth, Math.max(0, x));
		if(x!=health) {}
//			System.out.println("player health changed: "+health+" to "+x);
		if(x<health) {
			lastHit = System.currentTimeMillis();
		}
		health = x;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	public int getMaxStrength() {
		return maxStrength;
	}
	
	public void setStrength(int x) {
		x = Math.min(maxStrength, Math.max(0, x));
//		System.out.println("player strength changed: "+strength+" to "+x);
		strength = x;
	}
	
	public int getStrength() {
		return strength;
	}
	
	public int getPositionX() {
//		return (int) game.getMap().getPath().getPos(getDistOnPath(), offset).getX();
		return positionX;
	}
	
	public int getPositionY() {
//		return (int) game.getMap().getPath().getPos(getDistOnPath(), offset).getY();
		return positionY;
	}
	public void setPositionX(int posX) {
		prevPosX = positionX;
		positionX = posX;
	}
	
	public void setPositionY(int posY) {
		prevPosY = positionY;
		positionY = posY;
	}
	
	public void setJacket(boolean b) {
//		System.out.println("player jacket changed: "+hasJacket+" to "+b);
		hasJacket = b;
	}
	
	public boolean getJacket() {
		return hasJacket;
	}
	
	public void setOffset(int strafe) {
		Map map = game.getMap();
		int oldOffset = offset;
		offset = Math.max(-1*map.getMaxStrafe()*map.getScale(), Math.min(map.getMaxStrafe()*map.getScale(), strafe));
		if(oldOffset != offset) {
			lastMovement = System.currentTimeMillis();
		}
	}
	
	public int getOffset() {
		return offset;
	}
}


