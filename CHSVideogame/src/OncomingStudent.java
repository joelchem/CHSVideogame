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

		
		Point pos = g.getMap().getPath().getPos(distOnPath, strafe);
		
		try {
		    sprites = new Image[] {
		    		ImageIO.read(new File("assets/player_front2.png")).getScaledInstance(getDimensionX(), getDimensionY(), 0)
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
		
	}
	
	public void checkProximity() {
		
	}
	
	public Image getSprite() {
		return sprites[0];
	}
}