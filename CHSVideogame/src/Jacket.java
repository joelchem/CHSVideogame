public class Jacket extends DisplayObject{
	Game game;
	public Jacket(int x, int y, Game game) {
		super(game,y,x);
		this.game = game;
	}

	public void onCollision() {
		game.getPlayer().setJacket(true);
	}
}