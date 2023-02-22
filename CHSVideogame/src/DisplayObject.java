import java.awt.Image;

public class DisplayObject {
    private Game game;
    private int posX;
    private int posY;
    private Image sprite;
    private int dimensionX;
    private int dimensionY;
    //dimensions[0] = width
    //dimensions[1] = height
    DisplayObject(Game userGame, int dimensionX, int dimensionY) {
        this.dimensionX = dimensionX;
        this.dimensionY = dimensionY;
        game = userGame;
    }

    public int getDimensionX() {
        return dimensionX;
    }

    public int getDimensionY() {
        return dimensionY;
    }

    public void testForCollision() {
    	Player player = game.getPlayer();
    	//First check to see if the sprite is left or right of the player
    	//Then check up and down
    	
    	int playerPosX = player.getPositionX();
    	int playerPosY = player.getPositionY();
    	
    	//Call overlap with x and y values, if it passes, call onCollision
    	//NOTE: May need to modify the dimension adding in the future
    	
    	boolean overlapX = overLap(posX, posX+dimensionX, player.getPositionX(), 5);
    	boolean overlapY = overLap(posY, posY+dimensionY, player.getPositionY(), 6);
    			
    	//Player method to be determined in the future
    	if (overlapX && overlapY) {
    		onCollision();
    	}
    	
    	
    }
    
    private boolean overLap(int spriteStart, int spriteEnd, int playerStart, int playerEnd) {
    	if (spriteStart < spriteEnd )    	
    	
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
    	int xCoord = 0;
    	int yCoord = 0;
    
    }

    public int getX() {
        return posX;
    }

    public int getY() {
        return posY;
    }
} 