import java.io.*;
import java.util.*;

public class Game {
	private Map map;
	private Camera camera;
	private ArrayList<InteractableObject> interactableObjects;
	private ArrayList<OncomingStudent> oncomingStudents;
	private Player player;
	private int score;
	private File scoreBoard;
	private double remainingDistance;
	private String difficulty;
	
    public Game(){
        camera = new Camera(this);
        oncomingStudents = new ArrayList<>();
        interactableObjects = new ArrayList<>();
        score = 0;
        player = new Player(this);
        scoreBoard = new File("\\CHSVideogame\\assets\\scoreBoard.txt");
        try {
        	scoreBoard.createNewFile();
        } catch(Exception ex) {
        	System.out.println("LET ME IN");
        }
    }
}
