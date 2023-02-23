<<<<<<< HEAD
import java.awt.Image;

=======
>>>>>>> 8a7cc077f0cadb7e74d134ec332783327d79b3f1
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