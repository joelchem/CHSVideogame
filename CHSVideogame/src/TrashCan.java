import java.awt.Image;

public class TrashCan extends DisplayObject {
	Game theGame;
    public TrashCan(int x, int y, Game game, Image sprite) {
    	super(game,x,y,sprite);
		theGame=game;
    }
    public void onCollision() {
    	// implement separate collision test? cuz you have to come up behind the trashcan, not bump
    	theGame.getPlayer().setCrouch(true);
    }
}
