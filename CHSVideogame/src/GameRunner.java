import java.util.ArrayList;
import Math.*;

public class GameRunner {
    Game game;
    MainMenu menu;
    Player player;
    private final int FPS = 60;

    public GameRunner(){
        this.game = new Game();
        this.menu = new MainMenu(game, this);
        startGameLoop();
    }

    public void startGameloop(){
        game.mapSet(new Map(menu.getDifficulty()));
        int totalStudentCount;
        int diffValue;
        int maxValue = (int) game.getRemainingDistance();
        //might need to re-explain how calculateFrame interacts within the method here
        //x is a placeholder for value based on difficulty 
        int totalInteractableObject = 0;
        //come back to later after main menu is created

        //logic for weighted averages in items

        //Students logic
        if(game.getDifficulty().equals("EASY")){
            totalStudentCount = 50;
            totalInteractableObj = 30;
            // maxValue = ;
        } else if(game.getDifficulty().equals("MEDIUM")){
            totalStudentCount = 100;
            totalInteractableObj = 20;
            // maxValue = ;
        } else if(game.getDifficulty().equals("HARD")){
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
        if(game.getGameOver()){
            game.ends();
        }
        else{
            player.setPositionX(player.getPositionX() + (player.getVelocityX() * (1/FPS)));
            player.setPositionY(player.getPositionY() + (player.getVelocityY() * (1/FPS)));
            for(int i = 0; i<game.OncomingStudents.size(); i++){
                game.getOncomingStudents(i).testForCollision();
                if(player.getCrouch()){
                    //camera.stopMoving();
                    //player.setYVelocity(0);
                }
                OncomingStudent student = game.getOncomingStudents(i);
                game.getOncomingStudents(i).setPositionX(student.getPositionX() + (student.getVelocityX() * (1/FPS)));
                game.getOncomingStudents(i).setPositionY(student.getPositionY() + (student.getVelocityY() * (1/FPS)));

                if(Math.abs(player.getPositionY - student.getPositionY()) > certainDistance){
                    consider adding extra student;
                }
            }

            game.testForCollision();
            game.setCamera();
    }
    	int x = game.getPlayer().getPositionX();
    	int y = game.getPlayer().getPositionY();
    }
    
    public static void main(String[] args) {
		
	}
}
