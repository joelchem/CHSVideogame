import java.awt.Image;

public class TrashCan extends DisplayObject {
	Image trashcanSprite;
	Game theGame;
    public TrashCan(int x, int y, Game game, Image theImage) {
    	super(x,y,game);
		setSprite(theImage);
		theGame=game;
		trashcanSprite=theImage;
    }
}
