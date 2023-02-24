import java.util.*;
import java.awt.*;

public class OncomingStudent extends DisplayObject{
	private ArrayList<Image> students;
	private Game game;
	private int velocityX, velocityY, positionX, positionY, index;
	private int playPX, playPY, playDX, playDY;
	private Player player;
	public final int DIMENSION_X, DIMENSION_Y, GRAZE_HEALTH, HEAD_ON_HEALTH, STRENGTH_DEC,
		GRAZE_INT; //these will equal something later, placeholder
	
	public OncomingStudent(Game g, int posX, int posY) {
		super(g, DIMENSION_X, DIMENSION_Y);
		game = g;
		positionX = posX;
		positionY = posY;
		player = g.getPlayer();
		index = (int) (Math.random()*students.size());
		playPX = player.getPositionX();
		playPY = player.getPositionY();
		playDX = player.getDimensionX();
		playDY = player.getDimensionY();
	}
	
	public void setXVelocity(int vel) {
		velocityX = vel;
	}
	
	public void setYVelocity(int vel) {
		velocityY = vel;
	}
	
	public int getVelocityX() {
		return velocityX;
	}
	
	public int getVelocityY() {
		return velocityY;
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
		int xOver=0;
		int yOver=0;
		//if right of student and left of player overlap
		if((positionX+(DIMENSION_X/2)>playPX-(playDX/2)&&playPX+(playDX/2)>positionX+(DIMENSION_X/2))) {
			xOver = Math.abs(positionX+(DIMENSION_X/2)-playPX-(playDX/2));
		}
		
		//if left of student and right of player overlap
		if(positionX-(DIMENSION_X/2)>playPX+(playDX/2)&&positionX-(DIMENSION_X/2)>playPX-(playDX/2)) {
			xOver = Math.abs(positionX-(DIMENSION_X/2)-playPX+(playDX/2));
		}
		
		//if top of student overlaps with bottom of player
		if(positionY-(DIMENSION_Y/2)<playPY+(playDY/2)&&positionY-(DIMENSION_Y/2)<playPY-(playDY/2)) {
			yOver = Math.abs(positionY-(DIMENSION_Y/2)-playPY+(playPY/2));
		}
	
		
		/*
		 * if player is colliding w student, determine severity
		 * 
		 * trigger damage indicator for player based on severity
		 * 
		 * subtract appropriate amount of health from player
		 */
		//get back
	}
	
	
	public void checkProximity() {
		/*
		 * check for other incoming students where the player is in between them blah blah blah
		 * 
		 * if so, update velocity of current oncoming student and other-->they move in opposite directions and dodge each other
		 * 
		 */
		//get back
	}
}
