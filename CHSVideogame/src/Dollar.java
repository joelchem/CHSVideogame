public class Dollar extends DisplayObject{
	
	Game theGame;
	public Dollar(int dimensionX, int dimensionY, Game game) {
		super(game,dimensionX,dimensionY);
		theGame = game;
	}
	
	public void onCollision() {
    	theGame.getPlayer().updateMoney(true);
    }

}

