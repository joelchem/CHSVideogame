import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Map {
    private String difficulty;
    private double distance;
    private Game game;
    private Image mapImage;
    private Path path;
    
    private ArrayList<DisplayObject> placedObjects;

    private int scale = 20;
    private int dimX = 797;
    private int dimY = 600;
    
    private int maxStrafe = 11;
    
    public Map(Game game, String level) {
        this.game = game;
        scale = (int)(20*(game.getCamera().getDimX()/700.));
        difficulty=level;
        
        if(difficulty.equals("medium")) {
        	 try {
     		    mapImage = ImageIO.read(getClass().getClassLoader().getResource("med_test_map.png")).
                        getScaledInstance(dimX, dimY, 0);
     		} catch (IOException e) {
     			System.out.println("Some or all map sprite not found.");
     		}

             placedObjects = new ArrayList<DisplayObject>();
             placedObjects.add(new BakeSale(game, this, 533*scale, 396*scale, -Math.PI));
             placedObjects.add(new Dollar(game, this, 200*scale, 210*scale, -Math.PI/2));
             placedObjects.add(new CheeseCracker(game, this, 292*scale, 242*scale, -Math.PI));
             placedObjects.add(new TrashCan(game, this, 236*scale, 10*scale, -Math.PI));
             placedObjects.add(new WaterFountain(game, this, 295*scale, 330*scale, -Math.PI));
             
             int jacketX = (int)(Math.random()*150+320);
             placedObjects.add(new Jacket(game, this, jacketX*scale, 357*scale, -Math.PI/2));

        } else if(difficulty.equals("easy")){
        	try {
     		    mapImage = ImageIO.read(getClass().getClassLoader().getResource("easy_test_map.png")).
                        getScaledInstance(dimX, dimY, 0);
     		} catch (IOException e) {
     			System.out.println("Some or all map sprit not found.");
     		}
             
             placedObjects = new ArrayList<DisplayObject>();
             placedObjects.add(new BakeSale(game, this, 425*scale, 199*scale, -Math.PI/2));
             placedObjects.add(new Dollar(game, this, 200*scale, 210*scale, -Math.PI/2));
             placedObjects.add(new CheeseCracker(game, this, 500*scale, 215*scale, -Math.PI/2));
             placedObjects.add(new TrashCan(game, this, 438*scale, 10*scale, -Math.PI));
             placedObjects.add(new WaterFountain(game, this, 533*scale, 400*scale, -Math.PI));
             
             int jacketX = (int)(Math.random()*150+320);
             placedObjects.add(new Jacket(game, this, jacketX*scale, 220*scale, -Math.PI/2));
        } else {
        	try {
     		    mapImage = ImageIO.read(getClass().getClassLoader().getResource("hard_test_map.png")).
                        getScaledInstance(dimX, dimY, 0);
     		} catch (IOException e) {
     			System.out.println("Some or all map sprit not found.");
     		}
        	
        	placedObjects = new ArrayList<DisplayObject>();
            placedObjects.add(new BakeSale(game, this, 533*scale, 343*scale, -Math.PI));
            placedObjects.add(new Dollar(game, this, 200*scale, 210*scale, -Math.PI/2));
            placedObjects.add(new CheeseCracker(game, this, 412*scale, 262*scale, -Math.PI));
            placedObjects.add(new CheeseCracker(game, this, 406*scale, 365*scale, Math.PI));
            placedObjects.add(new CheeseCracker(game, this, 412*scale, 463*scale, -Math.PI));
            placedObjects.add(new TrashCan(game, this, 460*scale, 10*scale, -Math.PI));
            placedObjects.add(new WaterFountain(game, this, 295*scale, 400*scale, -Math.PI));
            
            int jacketX = (int)(Math.random()*150+320);
            placedObjects.add(new Jacket(game, this, jacketX*scale, 357*scale, Math.PI/2));
        	
        }
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
    	if(difficulty.equals("medium")) {
			combinePath.addPath(new StraightPath(new Point(124*scale, 210*scale), 
                    new Point(274*scale, 210*scale)));
			combinePath.addPath(new CurvedPath(new Point(274*scale, 210*scale), 
                    new Point(274*scale, 221*scale), Math.PI/2));
			combinePath.addPath(new StraightPath(new Point(285*scale, 221*scale), 
                    new Point(285*scale, 355*scale)));
			combinePath.addPath(new CurvedPath(new Point(285*scale, 355*scale), 
                    new Point(296*scale, 355*scale), -Math.PI/2));
			combinePath.addPath(new StraightPath(new Point(296*scale, 366*scale), 
                    new Point(512*scale, 366*scale)));
			combinePath.addPath(new CurvedPath(new Point(512*scale, 366*scale), 
                    new Point(512*scale, 377*scale), Math.PI/2));
			combinePath.addPath(new StraightPath(new Point(523*scale, 377*scale), 
                    new Point(523*scale, 453*scale)));
			combinePath.addPath(new CurvedPath(new Point(523*scale, 453*scale), 
                    new Point(534*scale, 453*scale), -Math.PI/2));
    	} else if(difficulty.equals("easy")) {
    		combinePath.addPath(new StraightPath(new Point(124*scale, 210*scale), 
                    new Point(512*scale, 210*scale))); 
        	combinePath.addPath(new CurvedPath(new Point(512*scale, 210*scale), 
                    new Point(512*scale, 221*scale), Math.PI/2));
        	combinePath.addPath(new StraightPath(new Point(523*scale, 221*scale), 
                    new Point(523*scale, 453*scale)));
        	combinePath.addPath(new CurvedPath(new Point(523*scale, 453*scale), 
                    new Point(534*scale, 453*scale), -Math.PI/2));
    	} else {
    		combinePath.addPath(new StraightPath(new Point(124*scale, 210*scale), 
                    new Point(274*scale, 210*scale)));
			combinePath.addPath(new CurvedPath(new Point(274*scale, 210*scale), 
                    new Point(274*scale, 221*scale), Math.PI/2));
			combinePath.addPath(new StraightPath(new Point(285*scale, 221*scale), 
                    new Point(285*scale, 249*scale)));
			combinePath.addPath(new CurvedPath(new Point(285*scale, 249*scale), 
                    new Point(296*scale, 249*scale), -Math.PI/2));
			combinePath.addPath(new StraightPath(new Point(296*scale, 260*scale), 
                    new Point(512*scale, 260*scale)));
			combinePath.addPath(new CurvedPath(new Point(512*scale, 260*scale), 
                    new Point(512*scale, 271*scale), Math.PI/2));
			combinePath.addPath(new StraightPath(new Point(523*scale, 271*scale), 
                    new Point(523*scale, 355*scale)));
			combinePath.addPath(new CurvedPath(new Point(523*scale, 355*scale), 
                    new Point(512*scale, 355*scale), Math.PI/2, true));
			combinePath.addPath(new StraightPath(new Point(512*scale, 366*scale), 
                    new Point(296*scale, 366*scale)));
			combinePath.addPath(new CurvedPath(new Point(296*scale, 366*scale), 
                    new Point(296*scale, 377*scale), -Math.PI/2, true));
			combinePath.addPath(new StraightPath(new Point(285*scale, 377*scale), 
                    new Point(285*scale, 452*scale)));
			combinePath.addPath(new CurvedPath(new Point(285*scale, 452*scale), 
                    new Point(296*scale, 452*scale), -Math.PI/2));
			combinePath.addPath(new StraightPath(new Point(296*scale, 463*scale), 
                    new Point(534*scale, 463*scale)));
			
    	}
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