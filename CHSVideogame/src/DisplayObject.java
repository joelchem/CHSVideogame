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

    }
    
    public void renderSprite() {

    }

    //To be specialized, used for determining conditions in the event that the player collides with a given interactable object
    public void onCollision() {

    }

    public int getX() {
        return posX;
    }

    public int getY() {
        return posY;
    }
} 