import java.awt.Image;
import java.util.ArrayList;

public class Player {
	
	private int strength, health;
	private boolean isCrouching;
	private int velocity;
	private ArrayList<Image> sprites;
	private int positionX, positionY;
	private double distance;
	private int spriteFrame;
	private Game game;
	private boolean money;
	private boolean isTurning;
	private int turnX, turnY;
	
	public Player(Game g) {
		
	}
	
	public void renderSprite(int screenX, int screenY) {
		
	}
	
	public void updateMoney(boolean b) {
		
	}
	
	public boolean getMoney() {
		return false;
	}
	public int getVelocity() {
		return 0;
	}
	
	public void setVelocity(int  x) {
		
	}
	
	public void setCrouch(boolean b) {
		
	}
	
	public boolean getCrouch() {
		return false;
	}
	
	public void setHealth(int x) {
		
	}
	
	public int getHealth() {
		return 0;
	}
	
	public void updateStrength(int x) {
		
	}
	
	public int getStrength() {
		return 0;
	}
	
	public void setTurning(boolean turning) {
		
	}
	
	public boolean getTurning() {
		return false;
	}
	
	public int getTurnX() {
		return 0;
	}
	
	public int getTurnY() {
		return 0;
	}
	
	public void setTurnX(int newTurnX) {
		
	}
	
	public void setTurnY(int newTurnY) {
		
	}
	
	public int getPositionX() {
		return 0;
	}
	
	public int getPositionY() {
		return 0;
	}
	public void setPositionX(int posX) {
		
	}
	
	public void setPositionY(int posY) {
		
	}
	
	public void crouch() {
		
	}

}
