import java.awt.Image;
import java.util.HashMap;

public class Map {
    String difficulty;
    double distance;
    Game game;
    Image mapImage;
    Path path;
    int xCoor=0;
    int yCoor=0;
    int [][] fountain = new int[2][2];
    int [][] jacket = new int[1][1];
    int [][] bakeSale = new int [1][1];
//    int [][] cracker = new int [][];
    

    public Map(Game game, String level) {
        this.game = game;
        difficulty=level;
       
        
    }
    
    public Path getPath() {
        return path;
    }
    
    public Image getMapImage() {
        return mapImage;
    }
    
    public String getDifficulty() {
        return difficulty;
    }
    public double getDistance() {
        return distance;
    }
    
}