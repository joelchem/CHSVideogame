public class GameRunner {
    Game game;
    Map map;
    public GameRunner(Game game, Map map){
        this.game = game;
        this.map = map;
    }
    public void startGameloop(){
        //might need to re-explain how calculateFrame interacts within the method here
        calculateFrame();
        //x is a placeholder for value based on difficulty 
        int x = 0;
        game.setMap(new Map());
        game.setPlayer(new Player());
        game.setDifficulty(map.getDifficulty());

        for(int i = 0; i < x; i++){
            game.addDisplayObject(new DisplayObject());
            game.addOncomingStudent(new OncomingStudent());
        }

    }

    private void calculateFrame(){
    }
}
