import java.awt.Image;

public class DisplayObject {
    private Game game;
    private int posX;
    private int posY;
    private Image sprite;
    private int[] dimensions;
    //dimensions[0] = width
    //dimensions[1] = height
    DisplayObject(int x, int y, Game userGame) {
        posX = x;
        posY = y;
        game = userGame;
    }

    public int getDimensionX() {
        return dimensions[0];
    }

    public int getDimensionY() {
        return dimensions[1];
    }

    
    public void testForCollision() {
    	Player player = game.getPlayer();
    	//First check to see if the sprite is left or right of the player
    	//Then check up and down
    	
    	int playerPosX = player.getPositionX();
    	int playerPosY = player.getPositionY();
    	
    	//Call overlap with x and y values, if it passes, call onCollision
    	//NOTE: May need to modify the dimension adding in the future
    	
    	boolean overlapX = overLap(posX, posX+dimensions[0], player.getPositionX(), player.getPositionX()+)
    	
    	if (overLap(posX, posX+dimensions[0], )) {
    		onCollision();
    	}
    	
    	
    }
    
    private boolean overLap(int spriteStart, int spriteEnd, int playerStart, int playerEnd) {
    	
    	return false;
    }

    public void renderSprite() {
    	
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