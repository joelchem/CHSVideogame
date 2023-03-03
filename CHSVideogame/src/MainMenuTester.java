public class MainMenuTester{
    private Map menu;
    private ArrayList<String> expected, results;
    public MainMenuTester(Map menu){
        this.menu = menu;
        expected = new ArrayList<String>();
        results = new ArrayList<String>();
        testCases();
        meshBowl();
    }

    public void meshBowl(ArrayList<String> a, ArrayList<String> b){
        for(int i = 0; i<a.size(); i++){
            printf("Expected: %s \n Result: %s \n Conclusion: %d \n\n", 
            {a.get(i),b.get(i), a.get(i).equals(b.get(i))});
        }
    }

    public void testCases(){
        //difficulty 
        expected.add("medium")
        results.add(menu.getDifficulty());

        menu.setDifficulty("easy")
        expected.add("easy");
        results.add(menu.getDifficulty());

        menu.setDifficulty("hard")
        expected.add("hard");
        results.add(menu.getDifficulty());

        menu.setDifficulty("medium")
        expected.add("medium");
        results.add(menu.getDifficulty());

        //

    }
}