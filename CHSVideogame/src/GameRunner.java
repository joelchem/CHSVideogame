import java.util.ArrayList;

import Game.Difficulty;

public class GameRunner {
    Game game;
    Map map;
    Player player;

    public GameRunner(Game game, Map map){
        this.game = game;
        this.map = game.getMap();
        startGameloop();
    }

    public void startGameloop(){
        int totalStudentCount;

        int diffValue;
        int maxValue = (int) game.getRemainingDistance();
        //might need to re-explain how calculateFrame interacts within the method here
        //x is a placeholder for value based on difficulty 
        int totalInteractableObject = 0;
        game.setPlayer(new Player());
        game.setDifficulty(map.getDifficulty());
        //come back to later after main menu is created

        //logic for weighted averages in items

        //Students logic
        if(game.EASY){
            totalStudentCount = 50;
            totalInteractableObj = 30;
            // maxValue = ;
        } else if(game.MEDIUM){
            totalStudentCount = 100;
            totalInteractableObj = 20;
            // maxValue = ;
        } else if(game.HARD){
            totalStudentCount = 200;
            totalInteractableObj = 10;
            // maxValue = ;
        }
        // for(int i = 0; i<3; i++){
        //     switch(i){
        //         case 0:
        //             totalStudentCount = ;
        //             maxValue = ;

        //         case 1:
        //             totalStudentCount = ;
        //             maxValue = ;
        //         case 2:
        //             totalStudentCount = ;
        //             maxValue = ;

        //         default:
        //             totalStudentCount = 0;
        //             maxValue = 0;
        //     }
        // }
        //maxValue is the maximum bounds for student creation
        //minValue is the minimum amount from the player
        // for(int i = 0; i < totalStudentCount; i++){
        //     int x = Math.floor(Math.random() * ((maxValue-minValue)+1) + minValue);
        //     int y = Math.floor(Math.random() * ((maxValue-x)-minValue) + minValue);
        //     game.addOncomingStudent(new OncomingStudent(game, x, y);
        // }

        // while(!totalAmountofDO){
        //     weighted algorithm for item selection
        
        // for (double r = Math.random() * totalWeightStudents; idx < items.size() - 1; ++idx) {
        //         r -= items.get(idx).getWeight();
        //     if (r <= 0.0) break;
        // }
        // Item myRandomItem = items[idx];

        // for(int i = 0; i < x; i++){
        //     game.addInteractableObject(new InteractableObject());
        //     game.addOncomingStudent(new OncomingStudent());
        // }
        
        //student loop

        for(int i = 0;  i < totalStudentCount; i++){
            game.addOncomingStudent(new OncomingStudent());
        }
        for(int i = 0;  i < totalInteractableObj; i++){
            game.addInteractableObject(new InteractableObject());
        }
        calculateFrame();
    }

    private void calculateFrame(){
    }
}
