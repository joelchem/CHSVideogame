import java.util.*;
import java.awt.*;

public class OncomingStudent extends DisplayObject{
	private ArrayList<Image> students;
	private double dimensionX, dimensionY;
	private Game game;
	private int velocityX, velocityY, positionX, positionY, index;
	
	public OncomingStudent(Game g, int posX, int posY) {
		super.DisplayObject(g, );
		game = g;
		positionX = posX;
		positionY = posY;
		index = (int) (Math.random()*students.size());
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
	
	public void checkForCollision() {
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
