import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player {
	
	private int strength, health;
	private boolean isCrouching;
	private int velocity;
	private Image[] sprites; // still need sprites
	private int positionX, positionY;
	private int spriteFrame;
	private double distOnPath;
	private Game game;
	private boolean money;
	private boolean hasJacket;
	private double heading;
	private final int dimX = 17;
	private final int dimY = 36;
	
	public Player(Game g) {
		game = g;
		try {
		    sprites = new Image[] {
		    		ImageIO.read(new File("assets/player_back1.png")).getScaledInstance(dimX, dimY, 0)
		    };
		} catch (IOException e) {
			System.out.println("Some or all player sprites not found.");
		}
		
		spriteFrame = 0;
		health = 10;
		strength = 10;
		isCrouching = false;
		velocity = 0;
		positionX = 0;
		positionY = 0;
		distOnPath = 0;
		money = false;
		hasJacket = false;
		
	}
	
	public void setHeading(double newHead) {
		heading = newHead;
	}
	
	public double getHeading() {
		return heading;
	}
	
	public double getDistOnPath() {
		return distOnPath;
	}
	
	public void setDistOnPath(double newDist) {
		distOnPath = newDist;
	}
	
	public Image getSprite() {
		return sprites[spriteFrame];
	}
	
	public int getDimensionX() {
		return dimX;
	}
	
	public int getDimensionY() {
		return dimY;
	}
	
	public void updateMoney(boolean b) {
		money = b;
	}
	
	public boolean getMoney() {
		return money;
	}
	
	public void setVelocity(int x) {
		velocity = x;
	}
	
	public int getVelocity() {
		return velocity;
	}
	
	public void setCrouch(boolean b) {
		isCrouching = b;
	}
	
	public boolean getCrouch() {
		return isCrouching;
	}
	
	public void setHealth(int x) {
		health = x;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void updateStrength(int x) {
		strength+=x;
	}
	
	public int getStrength() {
		return strength;
	}
	
	public int getPositionX() {
		return positionX;
	}
	
	public int getPositionY() {
		return positionY;
	}
	public void setPositionX(int posX) {
		positionX = posX;
	}
	
	public void setPositionY(int posY) {
		positionY = posY;
	}
	
	public void setJacket(boolean b) {
		hasJacket = b;
	}
	
	public boolean getJacket() {
		return hasJacket;
	}
}


