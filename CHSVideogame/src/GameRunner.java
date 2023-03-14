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
    	System.out.println(game.getPlayer().getDistOnPath());
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
	   timer.setInitialDelay(0);
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
    	lastFrame = currTime;
    	
    	System.out.println(game.getPlayer().getDistOnPath());
    	
    	for(int i = game.oncomingStudentsAmt()-1; i >= 0; i--) {
    		OncomingStudent student = game.getOncomingStudents(i);
    		Point newPos = game.getMap().getPath().getPos(student.getDistOnPath(), student.getStrafe());
    		student.setPosX((int)newPos.getX());
    		student.setPosY((int)newPos.getY());
    		student.setHeading(game.getMap().getPath().heading(student.getDistOnPath()));
    		student.setDistOnPath(student.getDistOnPath()-(int)(student.getVelocity()*timeDelta));
    		
    		if(game.getPlayer().getDistOnPath()-student.getDistOnPath()>game.getCamera().getDimY()) {
    			game.removeOncomingStudent(i);
    		}
    		
    	}
    	
    	
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
        for(int i = 0; i < game.oncomingStudentsAmt(); i++) {
        	OncomingStudent student = game.getOncomingStudents(i);
        	student.testForCollision();
        }
        
        if((int)(Math.random()*7)==0) {
        	double viewDist = game.getCamera().getDimY();
        	int velocity = (int)(Math.random()*10)*game.getMap().getScale();
        	int strafe = (int)(Math.random()*23-11);
        	game.addOncomingStudent(new OncomingStudent(game, 
        			viewDist+game.getPlayer().getDistOnPath(), strafe, velocity));
        }
        
    }
    
    public static void main(String[] args) {
		GameRunner runner = new GameRunner();
	}
}