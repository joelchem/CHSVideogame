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
	private int positionX, positionY;
	private int offset;
	private int spriteFrame;
	private double distOnPath;
	private Game game;
	private boolean money;
	private boolean hasJacket;
	private double heading;
	private int dimX;
	private int dimY;
	
	private final int maxHealth = 10;
	private final int maxStrength = 10;
	private final int defaultVel = 30;

	private long lastMovement;
	
	
	public Player(Game g) {
		game = g;
		dimX = 40*g.getMap().getScale()/25;
		dimY = 80*g.getMap().getScale()/25;
		try {
		    sprites = new Image[] {
		    		ImageIO.read(new File("assets/player_back1.png")).getScaledInstance(dimX, dimY, 0)
		    };
		    crouchSprite = ImageIO.read(new File("assets/player_crouch.png")).getScaledInstance(dimX, dimY, 0);
		} catch (IOException e) {
			System.out.println("Some or all player sprites not found.");
		}
		
		spriteFrame = 0;
		health = 6;
		strength = 6;
		isCrouching = false;
		velocity = defaultVel;
		positionX = 0;
		positionY = 0;
		offset = 0;
		distOnPath = 0;
		money = false;
		hasJacket = false;
		
	}
	
	public int getDefaultVelocity() {
		return defaultVel;
	}
	
	public Hitbox getHitbox() {
		return new Hitbox(new Point(positionX, positionY), dimX, dimY, heading);
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
		return sprites[spriteFrame];
	}
	
	public int getDimensionX() {
		return dimX;
	}
	
	public int getDimensionY() {
		return dimY;
	}
	
	public void updateMoney(boolean b) {
		System.out.println("player money changed: "+money+" to "+b);
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
		System.out.println("player health changed: "+health+" to "+x);
		health = x;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setStrength(int x) {
		x = Math.min(maxHealth, Math.max(0, x));
		System.out.println("player strength changed: "+strength+" to "+x);
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
		positionX = posX;
	}
	
	public void setPositionY(int posY) {
		positionY = posY;
	}
	
	public void setJacket(boolean b) {
		System.out.println("player jacket changed: "+hasJacket+" to "+b);
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


