import java.io.*;
import java.util.*;

public class Game {
	private Map map;
	private Camera camera;
	private ArrayList<OncomingStudent> oncomingStudents;
	private ArrayList<DisplayObject> displayObjects;
	private Player player;
	private File scoreBoard;
	private double remainingDistance;
	private String difficulty;

	public Game() {
		difficulty = "medium";
		camera = new Camera(this);
		map = new Map(this, "medium");
		oncomingStudents = new ArrayList<OncomingStudent>();
		displayObjects = new ArrayList<DisplayObject>();
		player = new Player(this);
		scoreBoard = new File("CHSVideogameScoreboard.txt");
		if(!scoreBoard.exists()) {
			try {
				scoreBoard.createNewFile();
				FileWriter myWriter = new FileWriter(scoreBoard);
			    myWriter.write("Name,Score,Date,Time\r\n");
			    myWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			scoreBoard.createNewFile();
		} catch (Exception ex) {
		}

	}
	
	public DisplayObject getDisplayObject(int index) {
		return displayObjects.get(index);
	}
	
	public int displayObjectAmt() {
		return displayObjects.size();
	}

	public void addDisplayObject(DisplayObject obj) {
		displayObjects.add(obj);
	}

	public void removeOncomingStudent(int index) {
		oncomingStudents.remove(index);
	}

	public OncomingStudent getOncomingStudents(int index) {
		return oncomingStudents.get(index);
	}

	public void addOncomingStudent(OncomingStudent student) {
		oncomingStudents.add(student);
	}
	public int oncomingStudentsAmt() {
		return oncomingStudents.size();
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map mapSet) {
		map = mapSet;
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera cam) {
		camera = cam;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player play) {
		player = play;
	}

	public File getScoreBoardFile() {
		return scoreBoard;
	}

	public void setDifficulty(String diff) {
		difficulty = diff;
		map = new Map(this, diff);
	}

	public String getDifficulty() {
		return difficulty;
	}

	public double getRemainingDistance() {
		return remainingDistance;
	}

	public void setRemainingDistance(double x) {
		remainingDistance -= x;
	}

}