import java.time.LocalDateTime;
import java.io.File;
import java.io.FileNotFoundException; 
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileWriter; 
import java.util.Scanner;
import java.time.format.DateTimeFormatter;  

public class TimeDate {
	public static void main(String[] args) {
		TimeDate t = new TimeDate();
		String scores = "name,scores,date,time\n";
		scores = t.addToString(scores, "Sam", 999999);
		scores = t.addToString(scores, "Nicole", 4200);
		scores = t.addToString(scores, "Jo", 1000);
		scores = t.addToString(scores, "Dia", 392);
		scores = t.addToString(scores, "x", 192);
		scores = t.addToString(scores, "Mike", 0);
		scores = t.addToString(scores, "Enoch", 0);
		scores = t.addToString(scores, "jake", 0);
		scores = t.addToString(scores, "lol", 0);
		scores = t.addToString(scores, "AGHIOWF", 0);
		t.writeScores(scores);
	}
	public String addToString(String scores, String name, int score) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd,HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		return scores += name + "," + score + "," + dtf.format(now) + "\n";
		// writing files needs to be done with a whole string, not multiple write commands (or else it would erase file between commands)
	}
	// assumes previous scores are already ordered from most to least
	public void writeScores(String scores) {
		try {
			FileWriter writer = new FileWriter("/Users/jwu1421/eclipse-workspace/CHSVideoGame/src/scoreBoard");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd,HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			writer.write(scores);
			writer.close();
		}
	    catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	     }
	}
}
