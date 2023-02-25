import java.awt.Image;

public class Jacket extends DisplayObject{
    Game game;
    public Jacket(int x, int y, Game game, Image sprite) {
        super(game,y,x, sprite);
        this.game = game;
    }

    public void onCollision() {
        game.getPlayer().setJacket(true);
    }
}