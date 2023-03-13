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
    
    ArrayList<DisplayObject> placedObjects;
    
    
    private int scale = 25;
    private int dimX = 797;
    private int dimY = 600;
    
    private int maxStrafe = 11;
    

    public Map(Game game, String level) {
        this.game = game;
        difficulty=level;
        
        try {
		    mapImage = ImageIO.read(new File("assets/map_test2_chillnosie.png")).getScaledInstance(dimX, dimY, 0);
		} catch (IOException e) {
			System.out.println("Some or all map sprit not found.");
		}
        
        placedObjects = new ArrayList<DisplayObject>();
        placedObjects.add(new BakeSale(game, 233*scale, 199*scale, -Math.PI/2));
        placedObjects.add(new Dollar(game, 200*scale, 210*scale, -Math.PI/2));
        placedObjects.add(new CheeseCracker(game, 170*scale, 215*scale, -Math.PI/2));
        placedObjects.add(new Jacket(game, 275*scale, 250*scale, -Math.PI));
        placedObjects.add(new TrashCan(game, 295*scale, 287*scale, -Math.PI));
       
        
    }
    
    public int getMaxStrafe() {
    	return maxStrafe;
    }
    
    public int getScale() {
    	return scale;
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
		combinePath.addPath(new StraightPath(new Point(124*scale, 210*scale), new Point(274*scale, 210*scale)));
		combinePath.addPath(new CurvedPath(new Point(274*scale, 210*scale), new Point(274*scale, 221*scale), Math.PI/2));
		combinePath.addPath(new StraightPath(new Point(285*scale, 221*scale), new Point(285*scale, 355*scale)));
		combinePath.addPath(new CurvedPath(new Point(285*scale, 355*scale), new Point(296*scale, 355*scale), -Math.PI/2));
		combinePath.addPath(new StraightPath(new Point(296*scale, 366*scale), new Point(512*scale, 366*scale)));
		combinePath.addPath(new CurvedPath(new Point(512*scale, 366*scale), new Point(512*scale, 377*scale), Math.PI/2));
		combinePath.addPath(new StraightPath(new Point(523*scale, 377*scale), new Point(523*scale, 453*scale)));
		combinePath.addPath(new CurvedPath(new Point(523*scale, 453*scale), new Point(534*scale, 453*scale), -Math.PI/2));
    	
//    	combinePath.addPath(new StraightPath(new Point(124*scale, 210*scale), new Point(248*scale, 210*scale)));
//		combinePath.addPath(new CurvedPath(new Point(248*scale, 210*scale), new Point(248*scale, 199*scale), -Math.PI/2, true));
//		combinePath.addPath(new StraightPath(new Point(259*scale, 199*scale), new Point(259*scale, 100*scale)));
//		combinePath.addPath(new CurvedPath(new Point(285*scale, 355*scale), new Point(296*scale, 355*scale), -Math.PI/2));
//		combinePath.addPath(new StraightPath(new Point(296*scale, 366*scale), new Point(512*scale, 366*scale)));
//		combinePath.addPath(new CurvedPath(new Point(512*scale, 366*scale), new Point(512*scale, 377*scale), Math.PI/2));
//		combinePath.addPath(new StraightPath(new Point(523*scale, 377*scale), new Point(523*scale, 453*scale)));
//		combinePath.addPath(new CurvedPath(new Point(523*scale, 453*scale), new Point(534*scale, 453*scale), -Math.PI/2));
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