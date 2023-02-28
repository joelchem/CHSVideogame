import java.util.*;
import java.awt.*;

public class OncomingStudent extends DisplayObject{
	private ArrayList<Image> students;
	private ArrayList<OncomingStudent> currentStudents;
	private Game game;
	private int velocityX, velocityY, positionX, positionY, index;
	private int playPX, playPY, playDX, playDY;
	private Player player;
	public final int DIMENSION_X, DIMENSION_Y, GRAZE_HEALTH, HEAD_ON_HEALTH, STRENGTH_DEC,
		GRAZE_INT, AVOID_VX, AVOID_VY, AVOID_DISX, AVOID_DISY; //these will equal something later, placeholder
	//AVOID_V what velocity students change to when splitting
	//AVOID_DIS distance they separate by 
	
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
		generateList();
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
			xOver = Math.abs(positionX+(DIMENSION_X/2)-playPX+(playDX/2));
		}
		
		//if left of student and right of player overlap
		if(positionX-(DIMENSION_X/2)>playPX+(playDX/2)&&positionX-(DIMENSION_X/2)>playPX-(playDX/2)) {
			xOver = Math.abs(positionX-(DIMENSION_X/2)-playPX-(playDX/2));
		}
		
		//if top of student overlaps with bottom of player
		if(positionY-(DIMENSION_Y/2)<playPY+(playDY/2)&&positionY-(DIMENSION_Y/2)>playPY-(playDY/2)) {
			yOver = Math.abs(positionY-(DIMENSION_Y/2)-playPY-(playPY/2));
		}
		
		//if bottom of student overlaps with top of player
		if(positionY+(DIMENSION_Y/2)>playPY-(playDY/2)&&positionY+(DIMENSION_Y/2)<playPY+(playDY/2)) {
			yOver = Math.abs(positionY+(DIMENSION_Y/2)-playPY+(playDY/2));
		}
		
		if(yOver>xOver) {
			if(yOver>0) {
				if(yOver>GRAZE_INT) {
					player.setHealth(player.getHealth()-HEAD_ON_HEALTH);
				} else {
					player.setHealth(player.getHealth()-GRAZE_HEALTH);
				}
			} else if(xOver>0) {
				if(xOver>GRAZE_INT) {
					player.setHealth(player.getHealth()-HEAD_ON_HEALTH);
				}else {
					player.setHealth(player.getHealth()-GRAZE_HEALTH);
				}
			}
		}
	}
	
	private void generateList() {
		int i = 0;
		while(game.getOncomingStudents(i)!=null) {
			currentStudents.add(game.getOncomingStudents(i));
		}
	}
	
	public void checkProximity() {
		int posX;
		int dimX;
		if(currentStudents.size()>=1) {
			for(OncomingStudent stud:currentStudents) {
				if(positionY==stud.getPositionY()) {
					posX = stud.getPositionX();
					dimX = stud.getDimensionX()/2;
					if(positionX>stud.getPositionX()) {
						if(positionX-(DIMENSION_X/2)==posX+dimX) {
							stud.setXVelocity(AVOID_VX);
							stud.setYVelocity(AVOID_VY);
							velocityX = -AVOID_VX;
							velocityY = AVOID_VY;
						}
					} else if(positionX<stud.getPositionX()) {
						if(positionX+(DIMENSION_X/2)==posX-dimX) {
							stud.setXVelocity(-AVOID_VX);
							stud.setYVelocity(AVOID_VY);
							velocityX = AVOID_VX;
							velocityY = AVOID_VY;
						}
					}
				}
			}
		}
		/*
		 * check for other incoming students where the player is in between them blah blah blah
		 * 
		 * if so, update velocity of current oncoming student and other-->they move in opposite directions and dodge each other
		 * 
		 */
	}
	
	public Image getImage() {
		return students.get(index);
	}
}
