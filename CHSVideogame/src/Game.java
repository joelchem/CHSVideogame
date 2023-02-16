import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
public class Game {
    public static enum Difficulty{
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
    private FileWriter fileWrite;

    public Game(){
        this.camera = new Camera();
        this.objects = new ArrayList<InteractableObject>();
        this.students = new ArrayList<OncomingStudent>();
        this.score = 0;
        this.scoreboard = new File("Top_Scores.txt");
        try {
            if(this.scoreboard.createNewFile()){
                System.out.println("Top_Scores.txt File created in CHSVideogame root directory");
                fileWrite = new FileWriter(scoreboard, true);
            }
            else{
                System.out.println("Top_Scores.txt File is already in CHSVideogame root directory");
            }
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.menu = new MainMenu();
        this.gameOver = false;
    }

    public InteractableObject getInteractableObject(int index){
        return objects.get(index);
    }

    public OncomingStudent getOncomingStudents(int index){
        return students.get(index);
    }
    public void setOncomingStudent(OncomingStudent student, int index){
        this.students.set(index, student);
    }
    public void addOncomingStudent(OncomingStudent student){
        this.students.add(student);
    }
    public void addInteractableObject(InteractableObject object){
        this.objects.add(object);
    }

    public Map getMap(){
        return map;
    }

    public void setMap(Map newMap){
        this.map = newMap;
        //or
    }
    public Camera getCamera(){
        return camera;
    }
    public void setCamera(Camera newCamera){
        this.camera = newCamera;
    }
    public Player getPlayer(){
        return player;
    }
    public void setPlayer(Player play){
        this.player = play;
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
        return diff;
    }
    public double getRemainingDistance(){
        return remainingDistance;
    }
    public void setRemainingDistance(double dist){
        this.remainingDistance = dist;
    }
    public void setGameOver(){
        if(player.getHealth()==0 || player.getStrength()==0 || this.remainingDistance == 0)
            this.gameOver = true;
        //some logic
        //should create a new endscreen object
        fileWrite.write(score);
        fileWrite.close();
        
    }
    public boolean getGameOver(){
        return gameOver;
    }
}
