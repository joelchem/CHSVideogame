import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

public class GameRunner implements ActionListener {
	
	private Game game;
	private CameraViewer camView;
	private Timer timer;
	private long lastFrame;
	
    public GameRunner(){
    	game = new Game();
    	camView = new CameraViewer(game);
    	new MainMenu(game, this);
    }

    public void startGameloop(){
       
       Map map = game.getMap();
       for(int i = 0; i < map.placedObjectLen(); i++) {
    	   game.addDisplayObject(map.getPlacedObject(i));
       }
       
       
       CameraViewer.startWindow(camView);
       timer = new Timer(20, this);
	   timer.setInitialDelay(100);
	   timer.start();
	   lastFrame = System.currentTimeMillis();
    }
    
    public void actionPerformed(ActionEvent e) {
    	calculateFrame();
    	camView.renderFrame();
	}
    
    private void calculateFrame(){
    	
    	long currTime = System.currentTimeMillis();
    	double timeDelta = (double)(currTime - lastFrame)/1000.;
    	if(timeDelta < 0)
    		System.out.println(currTime+" "+lastFrame+" "+timeDelta);
    	lastFrame = currTime;
    	
    	Player player = game.getPlayer();
        Point strafePos = game.getMap().getPath().getPos(player.getDistOnPath(), player.getOffset());
        Point pos = game.getMap().getPath().getPos(player.getDistOnPath());
        player.setPositionX((int)strafePos.getX());
        player.setPositionY((int)strafePos.getY());
        player.setHeading(game.getMap().getPath().heading(player.getDistOnPath()));
        game.getCamera().setX((int)pos.getX());
        game.getCamera().setY((int)pos.getY());
        game.getCamera().setHeading(game.getMap().getPath().heading(player.getDistOnPath()));
        player.setDistOnPath(player.getDistOnPath()+(int)(player.getVelocity()*timeDelta));
        
        for(int i = 0; i < game.displayObjectAmt(); i++) {
        	DisplayObject obj = game.getDisplayObject(i);
        	obj.testForCollision();
        }
    }
    
    public static void main(String[] args) {
		GameRunner runner = new GameRunner();
	}
}