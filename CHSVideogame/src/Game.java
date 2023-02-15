import java.io.File;
import java.util.ArrayList;
public class Game {
    static enum Difficulty{
        EASY,
        MEDIUM,
        HARD
    }
    private Map map;
    private Camera camera;
    private ArrayList<InteractableObject> objects;
    private ArrayList<OncomingStudent> students;
    private Player player;
    private int score;
    private Difficulty diff;
    private File scoreboard;
    private double remainingDistance;
    private MainMenu menu;
    private boolean gameOver;

    public Game(){
        this.camera = new Camera();
        this.objects = new ArrayList<InteractableObject>();
        this.students = new ArrayList<OncomingStudent>();
        this.score = 0;
        this.scoreboard = new File("/usr/local/bin/Top_Scores");
        this.menu = new MainMenu();
        this.gameOver = false;
    }
    public void startGameloop(){
        //might need to re-explain how calculateFrame interacts within the method here
        calculateFrame();
        //x is a placeholder for value based on difficulty 
        int x = 0;
        map = new Map();
        player = new Player();
        for(int i = 0; i < x; i++){
            objects.add(new InteractableObject());
            students.add(new OncomingStudent());
        }

    }

    private void calculateFrame(){
    }

    public InteractableObject getInteractableObject(int index){
        return objects.get(index);
    }

    public OncomingStudent getOncomingStudents(int index){
        return students.get(index);
    }
    public void setOncomingStudent(OncomingStudent student, int index){
        students.set(index, student);
    }

    public Map getMap(){
        return this.map;
    }

    public void setMap(Map newMap){
        this.map = newMap;
        //or
    }
    public Camera getCamera(){
        return this.camera;
    }
    public void setCamera(Camera newCamera){
        this.camera = newCamera;
    }
    public Player getPlayer(){
        return player;
    }
    public int getScore(){
        return score;
    }
    public File getScoreBoardFile(){
        return scoreboard;
    }
    public void setScore(int x){
        this.score = x;
        //or
        this.score += x;
    }
    public void setDifficulty(Difficulty difficulty){
        this.diff = difficulty;
    }
    public Difficulty getDifficulty(){
        return this.diff;
    }
    public double getRemainingDistance(){
        return this.remainingDistance;
    }
    public void setRemainingDistance(double dist){
        this.remainingDistance = dist;
    }
    public void setGameOver(){
        if(player.getHealth()==0 || player.getStrength()==0 || this.remainingDistance == 0)
            this.gameOver = true;
    }
    public boolean getGameOver(){
        return this.gameOver;
    }
}
