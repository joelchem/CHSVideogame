import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.Timer;

public class GameRunner implements ActionListener {
	
	private Game game;
	private CameraViewer camView;
	private MainMenu mainMenu;
	private Timer timer;
	private boolean started;
	
	private long lastFrame;
	private long timeStarted;
	
    public GameRunner(){
    	started = false;
    	game = new Game();

<<<<<<< HEAD
    public void startGameloop(){
=======
    	camView = new CameraViewer(game, this);
    	mainMenu = new MainMenu(game, this);
    }
    
    public boolean getStarted() {
    	return started;
    }
    
    public void startGameloop() {
    	started = true;
    	timeStarted = System.currentTimeMillis();
    	lastFrame = System.currentTimeMillis()%1000000;
    	timer = new Timer(20, this);
    	timer.setInitialDelay(0);
    	timer.start();
    }
    
    public void setupGameloop(){
       
>>>>>>> 6fb8592e2edf329363c3174932a130d0bce8ee8e
       Map map = game.getMap();
       for(int i = 0; i < map.placedObjectLen(); i++) {
    	   game.addDisplayObject(map.getPlacedObject(i));
       }
       CameraViewer.startWindow(camView);
       lastFrame = System.currentTimeMillis()%1000000;
       calculateFrame();
       camView.renderFrame();
       
    }
    
    public void actionPerformed(ActionEvent e) {
    	calculateFrame();
    	camView.renderFrame();
	}
    
    private void calculateFrame(){
    	
    	Player p = game.getPlayer();
    	if(p.getHealth()==0 || p.getStrength() == 0) {
    		int score = 0;
    		timer.stop();
    		try {
				mainMenu.gameOver(score);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	if(p.getDistOnPath()>=game.getMap().getPath().length()) {
    		double s = (double)(p.getHealth())/(double)(p.getMaxHealth()) + (double)(p.getStrength())/(double)(p.getMaxStrength());
//    		s *= 100.*(double)game.getMap().getPath().length()/(double)(System.currentTimeMillis()-timeStarted);
    		System.out.println("TIme: "+(System.currentTimeMillis()-timeStarted));
    		int score = (int)(100*s);
    		timer.stop();
    		try {
				mainMenu.gameOver(score);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}
    	
    	long currTime = System.currentTimeMillis()%1000000;
    	double timeDelta = (double)(currTime - lastFrame)/1000.;
    	
    	p.setStrength(p.getStrength()-(int)(timeDelta*1000));
    	
    	
    	lastFrame = currTime;
    	
    	for(int i = game.oncomingStudentsAmt()-1; i >= 0; i--) {
    		OncomingStudent student = game.getOncomingStudents(i);
    		Point newPos = game.getMap().getPath().getPos(student.getDistOnPath(), student.getStrafe());
    		student.setPosX((int)newPos.getX());
    		student.setPosY((int)newPos.getY());
    		student.setHeading(game.getMap().getPath().heading(student.getDistOnPath()));
    		student.setDistOnPath(student.getDistOnPath()-(int)(student.getVelocity()*timeDelta));
    		
    		if(game.getPlayer().getDistOnPath()-student.getDistOnPath()>game.getCamera().getDimY() || 
    				student.getDistOnPath()>game.getMap().getPath().length()) {
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
        
        if((int)(Math.random()*7)==0 && game.oncomingStudentsAmt() < 20) {
        	double viewDist = game.getCamera().getDimY();
        	int velocity = (int)(Math.random()*6+3)*game.getMap().getScale();
        	int strafe = (int)(Math.random()*23-11);
        	game.addOncomingStudent(new OncomingStudent(game, 
        			viewDist+game.getPlayer().getDistOnPath(), strafe, velocity));
        }
    }
    
    public static void main(String[] args) {
		GameRunner runner = new GameRunner();
	}
}