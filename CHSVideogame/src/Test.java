import java.awt.Image;
import java.awt.image.BufferedImage;

// display object testing
public class Test {
	public static void main(String [] args) {
		//basic getter/setter testin
		Game theGame = new Game();
		Image x = new BufferedImage(12, 12, 10);
		DisplayObject test = new DisplayObject(theGame,15,12,x );
		test.onCollision();
		System.out.println(test.getDimensionX());
		System.out.println(test.getDimensionY());
		Game theGame1 = new Game();
		Image x1 = new BufferedImage(12, 12, 10);
		DisplayObject test1 = new DisplayObject(theGame1,107,122,x1 );
		System.out.println(test1.getDimensionX());
		System.out.println(test1.getDimensionY());
		test1.setPosX(5);
		test1.setPosY(8);
		System.out.println(test1.getX());
		System.out.println(test1.getY());
		test1.setPosX(77);
		test1.setPosY(85);
		System.out.println(test1.getX());
		System.out.println(test1.getY());
		test1.setPosX(787);
		test1.setPosY(885);
		System.out.println(test1.getX());
		System.out.println(test1.getY());
		Player thePlayer = new Player(theGame1);
		thePlayer.setPositionX(15);
		thePlayer.setPositionX(12);
		test1.onCollision();
	}
}
