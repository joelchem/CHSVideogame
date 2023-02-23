import java.awt.Image;

public class Jacket extends DisplayObject{
	Image jacketSprite;
	Game game;
	public Jacket(int x, int y, Game game, Image image) {
		super(game,y,x);
		jacketSprite =image;
		this.game = game;
	}
	public void onCollision() {
		game.getPlayer().setJacket(true);
	}

}