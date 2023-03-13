import java.util.ArrayList;

public class MainMenuTester{
    private Map menu;
    private Game game;
    private ArrayList<String> expectedDiff, resultDiff;
    public MainMenuTester(Game game){
        this.game = game;
        this.menu = game.getMap();
        expectedDiff = new ArrayList<String>();
        resultDiff = new ArrayList<String>();
        testCases();
        checkDifficulty(expectedDiff, resultDiff);

    }

    public void checkDifficulty(ArrayList<String> a, ArrayList<String> b){
        for(int i = 0; i<a.size(); i++){
            boolean bool = a.get(i).equals(b.get(i));
            System.out.printf("Expected: %s \nResult: %s \nConclusion: %s\n---------\n", 
            a.get(i),b.get(i), bool ? "true" : "false");
        }
    }
    public void checkGameMethods(ArrayList<String> a, ArrayList<String> b){
        for(int i = 0; i<a.size(); i++){
            
        }
    }

    public void testCases(){
        //difficulty 
        expectedDiff.add("medium");
        resultDiff.add(game.getDifficulty());

        game.setDifficulty("easy");
        expectedDiff.add("easy");
        resultDiff.add(game.getDifficulty());

        game.setDifficulty("hard");
        expectedDiff.add("hard");
        resultDiff.add(game.getDifficulty());

        game.setDifficulty("medium");
        expectedDiff.add("medium");
        resultDiff.add(game.getDifficulty());

        //display object 
        game.addDisplayObject(new CheeseCracker(game, 0, 0, 0));
        DisplayObject obj1 = game.getDisplayObject(0);
        obj1.getClass();

        game.addDisplayObject(new BakeSale(game, 0, 0, 0));
        DisplayObject obj2 = game.getDisplayObject(1);

        game.addDisplayObject(new Jacket(game, 0, 0, 0));
        DisplayObject obj3 = game.getDisplayObject(2);

        game.addDisplayObject(new Dollar(game, 0, 0, 0));
        DisplayObject obj4 = game.getDisplayObject(3);

        game.addDisplayObject(new TrashCan(0, 0, game, null));
        DisplayObject obj5 = game.getDisplayObject(4);

        game.addDisplayObject(new WaterFountain(game, 0, 0, 0));
        DisplayObject obj6 = game.getDisplayObject(5);

        game.addOncomingStudent(new OncomingStudent(game, 0, 0, null));
        DisplayObject obj7 = game.getDisplayObject(6);
        //

    }
    public static void main(String[] args) {
        MainMenuTester test = new MainMenuTester(new Game());
    }
}