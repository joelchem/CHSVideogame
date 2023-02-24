import java.io.*;
import java.util.*;

public class Game {
	private Map map;
	private Camera camera;
	private ArrayList<OncomingStudent> oncomingStudents;
	private ArrayList<DisplayObject> displayObjects;
	private Player player;
	private int score;
	private File scoreBoard;
	private double remainingDistance;
	private String difficulty;
	private boolean gameOver;
	private boolean lost;

	public Game() {
		map = new Map(this);
		camera = new Camera(this);
		oncomingStudents = new ArrayList<>();
		displayObjects = new ArrayList<>();
		player = new Player(this);
		score = 0;
		scoreBoard = new File("\\CHSVideogame\\assets\\scoreBoard.txt");
		try {
			scoreBoard.createNewFile();
		} catch (Exception ex) {
			System.out.println("LET ME IN!!!!!!");
		}
		difficulty = " ";
		gameOver = false;
	}

	public DisplayObject getDisplayObject(int index) {
		return displayObjects.get(index);
	}

	public void addDisplayObject(DisplayObject obj) {
		displayObjects.add(obj);
	}

	public void removeDisplayObject(int index) {
		displayObjects.remove(index);
	}

	public OncomingStudent getOncomingStudents(int index) {
		return oncomingStudents.get(index);
	}

	public void addOncomingStudent(OncomingStudent student) {
		oncomingStudents.add(student);
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

	public int getScore() {
		return score;
	}

	public void setScore(int x) {
		score += x;
	}

	public File getScoreBoardFile() {
		return scoreBoard;
	}

	public void setDifficulty(String diff) {
		difficulty = diff;
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

	public void setGameOver() {
		int health = player.getHealth();
		int strength = player.getStrength();
		if (health == 0 || strength == 0) {
			gameOver = true;
			lost = true;
		} else if (remainingDistance == 0) {
			gameOver = true;
			lost = false;
		} else {
			gameOver = false;
		}
	}
	// also sets lost

	public boolean getLost() {
		return lost;
	}

	public boolean getGameOver() {
		return gameOver;
	}
}