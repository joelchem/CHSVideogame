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
    private ArrayList<OncommingStudent> students;
    private Player player;
    private int score;
    

    public Game(){
        System.out.println("More tests");
    }
}
