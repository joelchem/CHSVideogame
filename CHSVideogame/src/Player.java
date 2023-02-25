import java.awt.Image;

public class Player {
	
	private int strength, health;
	private boolean isCrouching;
	private int velocity;
	private Image[] sprites; // still need sprites
	private int positionX, positionY;
	private double distance;
	private int spriteFrame;
	private Game game;
	private boolean money;
	private boolean isTurning;
	private int turnX, turnY;
	private boolean hasJacket;
	private int dimX;
	private int dimY;
	
	public Player(Game g) {
		game = g;
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
	
	public void setTurning(boolean turning) {
		isTurning = turning;
	}
	
	public boolean getTurning() {
		return isTurning;
	}
	
	public int getTurnX() {
		return turnX;
	}
	
	public int getTurnY() {
		return turnY;
	}
	
	public void setTurnX(int newTurnX) {
		turnX = newTurnX;
	}
	
	public void setTurnY(int newTurnY) {
		turnY = newTurnY;
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


