import java.awt.Image;
import java.awt.image.BufferedImage;

public class GameTestDriver {
	public static void main(String [] args) {
		System.out.println("format is expected value:value");
		Game g = new Game();
		System.out.println("ideally wont say 'let me in' ");
		g.setDifficulty("Easy");
		System.out.print("Easy:");
		System.out.println(g.getDifficulty());
		g.setDifficulty("Moderate");
		System.out.print("Moderate:");
		System.out.println(g.getDifficulty());
		g.setDifficulty("Hard");
		System.out.print("Hard:");
		System.out.println(g.getDifficulty());
		
		Image placeHolder = new BufferedImage(12, 12, 10);
		CheeseCracker cheeseCrack = new CheeseCracker(g,5,7,10);
		g.addDisplayObject(cheeseCrack);
		System.out.print("CheeseCracker:");
		System.out.println(g.getDisplayObject(0));
		
		Image placeHolder2 = new BufferedImage(12, 12, 10);
		CheeseCracker cheeseCrack2 = new CheeseCracker(g,5,7,10);
		g.addDisplayObject(cheeseCrack2);
		System.out.print("CheeseCracker:");
		System.out.println(g.getDisplayObject(1));
		
		Image placeHolder3 = new BufferedImage(14, 13, 10);
		Jacket Jack = new Jacket(g,5,7,28);
		g.addDisplayObject(Jack);
		System.out.print("Jacket:");
		System.out.println(g.getDisplayObject(2));
		
		Game greg = new Game();
		//do tests for oncoming students. 
	}
}
