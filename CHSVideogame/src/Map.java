import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Map {
    String difficulty;
    double distance;
    Game game;
    Image mapImage;
    Path path;
    
    ArrayList<DisplayObject> placedObjects;;
    
    private final int dimX = 800*10;
    private final int dimY = 600*10;
    

    public Map(Game game, String level) {
        this.game = game;
        difficulty=level;
        
        try {
		    mapImage = ImageIO.read(new File("assets/map_test2_noise.png")).getScaledInstance(dimX, dimY, 0);
		} catch (IOException e) {
			System.out.println("Some or all map sprit not found.");
		}
        
        placedObjects = new ArrayList<DisplayObject>();
        placedObjects.add(new BakeSale(game, 2330, 1990, Math.PI/2));
       
        
    }
    
    public int getDimensionX() {
		return dimX;
	}
	
	public int getDimensionY() {
		return dimY;
	}
    
    public Image getMapImage() {
        return mapImage;
    }
    
    public Path getPath() {
    	Path combinePath = new Path();
		combinePath.addPath(new StraightPath(new Point(1240, 2100), new Point(2740, 2100)));
		combinePath.addPath(new CurvedPath(new Point(2740, 2100), new Point(2740, 2210), Math.PI/2));
		combinePath.addPath(new StraightPath(new Point(2850, 2210), new Point(2850, 3550)));
		combinePath.addPath(new CurvedPath(new Point(2850, 3550), new Point(2960, 3550), -Math.PI/2));
		combinePath.addPath(new StraightPath(new Point(2960, 3660), new Point(5120, 3660)));
		combinePath.addPath(new CurvedPath(new Point(5120, 3660), new Point(5120, 3770), Math.PI/2));
		combinePath.addPath(new StraightPath(new Point(5230, 3770), new Point(5230, 4530)));
		combinePath.addPath(new CurvedPath(new Point(5230, 4530), new Point(5340, 4530), -Math.PI/2));
		return combinePath;
    }
    
    public int placedObjectLen() {
    	return placedObjects.size();
    }
    
    public DisplayObject getPlacedObject(int i) {
    	return placedObjects.get(i);
    }
    
    public String getDifficulty() {
        return difficulty;
    }
    public double getDistance() {
        return distance;
    }
    
}