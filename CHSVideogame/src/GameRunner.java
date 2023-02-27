import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

public class GameRunner implements ActionListener {
	
	private Game game;
	private CameraViewer camView;
	private Timer timer;
	
    public GameRunner(){
    	game = new Game();
    	camView = new CameraViewer(game);
    	new MainMenu(game, this);
    }

    public void startGameloop(){
       System.out.println("start");
       
       CameraViewer.startWindow(camView);
       timer = new Timer(20, this);
	   timer.setInitialDelay(100);
	   timer.start();
    }
    
    public void actionPerformed(ActionEvent e) {
    	calculateFrame();
    	camView.renderFrame();
	}
    
    private void calculateFrame(){
    	Player player = game.getPlayer();
        Point strafePos = game.getMap().getPath().getPos(player.getDistOnPath());
        Point pos = game.getMap().getPath().getPos(player.getDistOnPath());
        player.setPositionX((int)strafePos.getX());
        player.setPositionY((int)strafePos.getY());
        player.setHeading(game.getMap().getPath().heading(player.getDistOnPath()));
        game.getCamera().setX((int)pos.getX());
        game.getCamera().setY((int)pos.getY());
        game.getCamera().setHeading(game.getMap().getPath().heading(player.getDistOnPath()));
        player.setDistOnPath(player.getDistOnPath()+10);
    }
    
    public static void main(String[] args) {
		GameRunner runner = new GameRunner();
	}
}