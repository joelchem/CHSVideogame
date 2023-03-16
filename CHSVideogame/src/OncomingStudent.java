import java.util.*;

import javax.imageio.ImageIO;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class OncomingStudent extends DisplayObject{
	private Game game;
	private int posX, posY, velocity, targetStrafe, strafe;
	private double distOnPath, heading;
	private Image[] sprites;
	
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
		
	}
	public int getVelocity() {
		return velocity;
	}
	
	public int getStrafe() {
		return strafe;
	}
	
	public double getHeading() {
		return game.getPlayer().getHeading();
	}

	
	public double getDistOnPath() {
		return distOnPath;
	}
	
	public void setDistOnPath(double newDist) {
		distOnPath = newDist;
	}

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
	}
}
