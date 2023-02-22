import java.awt.*;

public class DisplayObject {
    private Game game;
    private int posX;
    private int posY;
    private Image sprite;
    private int dimensionX;
    private int dimensionY;
    
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
    	
    	int lengthX = dimensionX/2;
    	int lengthY = dimensionY/2;
    	
    	int playerLengthX = 0;
    	int playerLenthY = 0;
    	
    	//Call overlap with x and y values, if it passes, call onCollision
    	//NOTE: May need to modify the dimension adding in the future
    	
    	boolean overlapX = overLap(posX-lengthX, posX+lengthX, player.getPositionX()-playerLengthX, player.getPositionX()+playerLengthX);
    	boolean overlapY = overLap(posY-lengthY, posY+lengthY, player.getPositionY(), playerLengthY);
    			
    	//Player method to be determined in the future
    	if (overlapX && overlapY) {
    		onCollision();
    	}
    	
    	
    }
    
    //Checks to see if there is an overlap on whatever plane
    //Compares the start positions of both sprites and checks to see if there is an over
    private boolean overLap(int spriteStart, int spriteEnd, int playerStart, int playerEnd) {
    	if (playerStart < spriteStart) {
    		if (playerEnd > spriteStart && playerEnd < spriteEnd) 
    			return true;
    		else 
    			return false;
    	} else if (playerStart > spriteStart){
    		if (playerStart < spriteEnd) 
    			return true;
    		else {
    			return false;
    		}
    	} 
    	
    	return true;
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
    
    public int setPosX(int x) {
    	posX = x;
    }
    
    public int setPosY(int y) {
    	posY = y;
    }
} 