import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainMenu implements ActionListener{
	JButton start;
	JButton easy;
	JButton medium;
	JButton hard;
	JButton map;
	JButton back;
	JPanel contentPane1;
	JPanel contentPane2;
	JPanel contentPane3a;
	JPanel contentPane3b;
	JFrame frame1;
	JFrame frame2;
	JFrame frame3;
	Game game;
	GameRunner gameRunner;
	
	private final int width = 1000;
	private final int height = 1000;

	// public MainMenu(Game game, GameRunner gameRunner) {
	// //this.game = game;
	// //this.gameRunner = gameRunner;
	// width = 500;
	// height = 500;
	// JPanel contentPane = new JPanel();
	// contentPane.setLayout(null);
	// contentPane.setBackground(Color.CYAN);
	// start = centeredButtonMaker("start", 100, 50, 500, 400);
	// contentPane.add(start);
	// easy = centeredButtonMaker("easy", 100, 50, 500, 550);
	// contentPane.add(easy);
	// medium = centeredButtonMaker("medium", 100, 50, 500, 700);
	// contentPane.add(medium);
	// hard = centeredButtonMaker("hard", 100, 50, 500, 850);
	// contentPane.add(hard);
	// frame = new JFrame();
	// frame.add(contentPane);
	// frame.setSize(width, height);
	// frame.setVisible(true);
	// frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	// 
    public class JScoreBoard extends JPanel {

        @Override
        public void paintComponent(Graphics g) {
        	g.setColor(Color.GRAY);
        	g.fillRect(0, 0, width, height);
        	g.setColor(Color.black);
            g.fillRect(200, 62, 30, 10);
        }
    }
	public MainMenu(Game game, GameRunner gr) {
		this.game = game;
		gameRunner = gr;
		
		contentPane1 = new JPanel();
		contentPane1.setLayout(null);
		contentPane1.setBackground(Color.CYAN);
		start = centeredButtonMaker("Start", 100, 50, 500, 400);
		contentPane1.add(start);
		map = centeredButtonMaker("Map", 100, 50, 500, 500);
		contentPane1.add(map);
		frame1 = new JFrame();
		frame1.add(contentPane1);
		frame1.setSize(width, height);
		frame1.setVisible(true);
		frame1.setDefaultCloseOperation(frame1.EXIT_ON_CLOSE);
		
		contentPane2 = new JPanel();
		contentPane2.setLayout(null);
		contentPane2.setBackground(Color.CYAN);
		easy = centeredButtonMaker("Easy", 100, 50, 500, 300);
		contentPane2.add(easy);
		medium = centeredButtonMaker("Medium", 100, 50, 500, 400);
		medium.setBackground(Color.ORANGE);
		contentPane2.add(medium);
		hard = centeredButtonMaker("Hard", 100, 50, 500, 500);
		contentPane2.add(hard);
		back = centeredButtonMaker("Back", 100, 50, 100, 700);
		contentPane2.add(back);
		frame2 = new JFrame();
		frame2.add(contentPane2);
		frame2.setSize(width, height);
		frame2.setDefaultCloseOperation(frame2.EXIT_ON_CLOSE);
		

		contentPane3a = new JScoreBoard();
		contentPane3a.setLayout(null);
		contentPane3a.setBounds(200, 100, 400, 600);
		// scoreboard
		
		contentPane3b = new JPanel();
		contentPane3b.setLayout(null);
		contentPane3b.setBackground(Color.CYAN);
		contentPane3b.setSize(width, height);
		
		frame3 = new JFrame();
		frame3.setLayout(null);
		frame3.add(contentPane3a);
		frame3.add(contentPane3b);
		frame3.setSize(width, height);
		frame3.setDefaultCloseOperation(frame3.EXIT_ON_CLOSE);
		
	}
	public void gameOver() throws FileNotFoundException {
		File f = new File("/Users/jwu1421/eclipse-workspace/CHSVideoGame/src/scoreBoard");
		//change path later
		Scanner s = new Scanner(f);
		System.out.println(s.nextLine());
		s.close();
		frame3.setVisible(true);
		frame1.setVisible(false);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Start")) {
			gameRunner.startGameloop();
			frame1.setVisible(false);
		} else if (e.getActionCommand().equals("Easy")) {
			game.setDifficulty("easy");
			easy.setBackground(Color.ORANGE);
			medium.setBackground(Color.white);
			hard.setBackground(Color.white);
		} else if (e.getActionCommand().equals("Medium")) {
			game.setDifficulty("medium");
			medium.setBackground(Color.ORANGE);
			easy.setBackground(Color.white);
			hard.setBackground(Color.white);
		} else if (e.getActionCommand().equals("Hard")) {
			game.setDifficulty("hard");
			hard.setBackground(Color.ORANGE);
			medium.setBackground(Color.white);
			easy.setBackground(Color.white);
		} else if(e.getActionCommand().equals("Map")) {
			frame1.setVisible(false);
			frame2.setVisible(true);
		} else if(e.getActionCommand().equals("Back")) {
			frame1.setVisible(true);
			frame2.setVisible(false);
		}
	}

	private JButton centeredButtonMaker(String name, int sizeX, int sizeY,
		int X, int Y) {
		JButton button = new JButton(name);
		button.setBounds(X - sizeX / 2, Y - sizeY / 2, sizeX, sizeY);
		button.setActionCommand(name);
		button.addActionListener(this);
		return button;
	}
}
