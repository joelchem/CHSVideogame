import java.awt.Image;
import java.awt.Point;

public class DisplayObject {
    private Game game;
    private int posX;
    private int posY;
    private double heading;
    private Image sprite;
    private int dimensionX;
    private int dimensionY;
    
    DisplayObject(Game userGame, int x, int y, double heading, int dimensionX, int dimensionY) {
        this.dimensionX = dimensionX;
        this.dimensionY = dimensionY;
        this.sprite = sprite;
        
        setPosX(x);
        setPosY(y);
        setHeading(heading);
        
        game = userGame;
    }

    public int getDimensionX() {
        return dimensionX;
    }

    public int getDimensionY() {
        return dimensionY;
    }
    
    public Hitbox getHitbox() {
    	return new Hitbox(new Point(getX(), getY()), getDimensionX(), getDimensionY(), getHeading());
    }

    public boolean testForCollision() {
    	Player player = game.getPlayer();
    	if(getHitbox().isColliding(player.getHitbox())) {
    		onCollision();
    		return true;
    	}
    	return false;
    	
    }

    public void setSprite(Image theSprite) {
    	sprite = theSprite;
    }

    public Image getSprite() {
    	return sprite;
    }

    //To be specialized, used for determining conditions in the event that the player collides with a given interactable object
    public void onCollision() {
    	System.out.println("The player has collided with this object");
    }
    
    public double getHeading() {
    	return heading;
    }
    
    public void setHeading(double head) {
    	heading = head;
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
} 