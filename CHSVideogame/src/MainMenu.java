import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.DefaultFormatterFactory;

import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.io.BufferedReader;
import java.io.IOException; // Import the IOException class to handle errors
import java.io.FileWriter;
import java.io.FileReader;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.awt.Image;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.*;

public class MainMenu extends JPanel implements ActionListener {
	private JButton start;
	private JButton easy;
	private JButton medium;
	private JButton hard;
	private JButton map;
	private JButton back;
   private JButton replay;
	private JTextField nameInp;
	private JFrame frame1;
	private JFrame frame2;
	private JFrame frame3;
	private JPanel contentPane3a;
	private JPanel contentPane3b;
   private JPanel contentPane3c;
	private ImageIcon background = null;
	private Image pic;
	private final int width = 1000;
	private final int height = 800;
	private Game game;
	private GameRunner gameRunner;
	private int score;
	private String name;
	private File scoreBoard;
	private JLabel label3;

	public MainMenu(Game g, GameRunner gr) {
		game = g;
		gameRunner = gr;

		frame1 = new JFrame();
		frame2 = new JFrame();
		JLabel label = new JLabel();
		JLabel label2 = new JLabel();
		label3 = new JLabel();
		label3.setIcon(new ImageIcon(getClass().getClassLoader().getResource("test.jpg")));
		label2.setIcon(new ImageIcon(getClass().getClassLoader().getResource("CHS_MenuBackground.png")));
		label.setIcon(new ImageIcon(getClass().getClassLoader().getResource("CHS_MenuBackground.png")));
		frame1.add(label);
		frame2.add(label2);

		ImageIcon startButton = new ImageIcon(getClass().getClassLoader().getResource("CHS_StartButton.png"));
		ImageIcon title = new ImageIcon(getClass().getClassLoader().getResource("CHSVideogame_title.png"));
		ImageIcon title2 = new ImageIcon(getClass().getClassLoader().getResource("CHSVideogame_title2.png"));
		ImageIcon mapButton = new ImageIcon(getClass().getClassLoader().getResource("CHS_MapButton.png"));
		ImageIcon backButton = new ImageIcon(getClass().getClassLoader().getResource("CHS_BackButton.png"));
//		ImageIcon easyButton = new ImageIcon(getClass().getClassLoader().getResource("CHS_EasyButton.png"));
//		ImageIcon normalButton = new ImageIcon(getClass().getClassLoader().getResource("CHS_NormalButton.png"));
//		ImageIcon hardButton = new ImageIcon(getClass().getClassLoader().getResource("CHS_Hard.png"));
		ImageIcon easyButton = new ImageIcon(getClass().getClassLoader().getResource("easy-button.png"));
		ImageIcon normalButton = new ImageIcon(getClass().getClassLoader().getResource("medium-button.png"));
		ImageIcon hardButton = new ImageIcon(getClass().getClassLoader().getResource("hard-button.png"));
      ImageIcon replayButton = new ImageIcon(getClass().getClassLoader().getResource("CHS_BackButton.png"));

      ImageIcon imageIcon = replayButton; 
      Image image = imageIcon.getImage(); 
      Image newimg = image.getScaledInstance(240, 120,  java.awt.Image.SCALE_SMOOTH);   
      replayButton = new ImageIcon(newimg);  
      replay = new JButton("", replayButton);
      replay.addActionListener(this);
      replay.setActionCommand("Replay");	
      replay.setOpaque(false);
      replay.setContentAreaFilled(false);
      replay.setBorderPainted(false);


		JLabel picLabel = new JLabel(title);
		JLabel picLabel2 = new JLabel(title2);

		picLabel.setBounds(-20, -310, 1000, 900);
		picLabel2.setBounds(0, -210, 1000, 900);

		label.add(picLabel);
		label.add(picLabel2);

//        start = new JButton("", startButton);
//        start.setBounds(400, 400, 200, 105);
//        start.setActionCommand("Start");
//        start.addActionListener(this);
//
//        label.add(start);

		nameInp = new JTextField("Unnamed", 8);
      nameInp.setDocument(new JTextFieldLimit(8));
      nameInp.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
      nameInp.setBounds(400, 300, 200, 50);
      nameInp.setText("Unnamed");
      label.add(nameInp);


		easy = new JButton("", easyButton);
//        easy.setBounds(425, 250, 170, 70);
		easy.setBounds(425, 400, 170, 70);
		easy.setActionCommand("Easy");
		easy.addActionListener(this);

//        label2.add(easy);
		label.add(easy);

		medium = new JButton("", normalButton);
//        medium.setBounds(425, 350, 170, 70);
		medium.setBounds(425, 500, 170, 70);
		medium.setActionCommand("Medium");
		medium.addActionListener(this);

//        label2.add(medium);
		label.add(medium);

		hard = new JButton("", hardButton);
//      hard.setBounds(425, 450, 170, 70);
		hard.setBounds(425, 600, 170, 70);
		hard.setActionCommand("Hard");
		hard.addActionListener(this);

//        label2.add(hard);
		label.add(hard);

		map = new JButton("", mapButton);
		map.setBounds(400, 550, 200, 105);
		map.setActionCommand("Map");
		map.addActionListener(this);

//        label.add(map);

		back = new JButton("", backButton);
		back.setBounds(50, 600, 150, 50);
		back.setActionCommand("Back");
		back.addActionListener(this);

		label2.add(back);
		frame1.setSize(width, height);
		frame1.setVisible(true);
		frame2.setVisible(false);
		frame2.setSize(width, height);
		frame1.setDefaultCloseOperation(frame1.EXIT_ON_CLOSE);

		contentPane3a = new JScoreBoard();
		contentPane3a.setLayout(null);
		contentPane3a.setBounds(width / 2 - 250, 50, 500, 650);

		// scoreboard
		contentPane3b = new JPanel();
		contentPane3b.add(label3);
		contentPane3b.setSize(width, height);
      contentPane3c = new JPanel();
      contentPane3c.setBounds(width/2+replayButton.getIconWidth()+10, 
      height/2 - replayButton.getIconHeight()/2, replayButton.getIconWidth(), replayButton.getIconHeight());
      contentPane3c.add(replay);
      contentPane3c.setSize(replayButton.getIconWidth(), replayButton.getIconHeight());


		frame3 = new JFrame();
		frame3.setLayout(null);
		frame3.add(contentPane3a);
		frame3.add(contentPane3b);
      frame3.add(contentPane3c);
		frame3.setSize(width, height);
		frame3.setDefaultCloseOperation(frame3.EXIT_ON_CLOSE);
		frame1.setResizable(false);
		frame2.setResizable(false);
		frame3.setResizable(false);
	}

	public void reload() {
		frame3 = new JFrame();
		frame3.setLayout(null);
		frame3.add(contentPane3a);
		frame3.add(contentPane3b);
      frame3.add(contentPane3c);
		frame3.setSize(width, height);
		frame3.setDefaultCloseOperation(frame3.EXIT_ON_CLOSE);
		frame1.setResizable(false);
		frame2.setResizable(false);
		frame3.setResizable(false);
	}

	public String getName() {
		return nameInp.getText();
	}
   class JTextFieldLimit extends PlainDocument {
		   private int limit;
		   JTextFieldLimit(int limit) {
		      super();
		      this.limit = limit;
		   }
		   JTextFieldLimit(int limit, boolean upper) {
		      super();
		      this.limit = limit;
		   }
		   public void insertString(int offset, String str, javax.swing.text.AttributeSet attr) {
		      if (str == null)
		         return;
		      if ((getLength() + str.length()) <= limit) {
		         try {
					super.insertString(offset, str, attr);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
		      }
	   }
}


	public class JScoreBoard extends JPanel {
		public void paintComponent(Graphics g) {
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
			if (score != 0) {
				g.setColor(Color.green);
				g.fillRect(0, 0, 5000, 100);
				g.setColor(Color.white);
				g.drawString("You reached the cafeteria!", 50, 60);
			} else {
				g.setColor(Color.red);
				g.fillRect(0, 0, 5000, 100);
				g.setColor(Color.white);
				g.setFont(new Font(Font.DIALOG, Font.BOLD, 40));
				if (game.getPlayer().getStrength() == 0) {
					g.drawString("You ran out of strength!", 25, 60);
				}
				else if (game.getPlayer().getHealth() == 0) {
					g.drawString("You ran out of health!", 40, 60);
				}
			}
			// contentPane3b.add(label3);
			g.setColor(Color.black);
			g.fillRect(0, 85, 1000, 950);
			Scanner s;
			try {
				s = new Scanner(game.getScoreBoardFile());
				int x = 60;
				int y = 100;
				while (s.hasNextLine()) {
					g.setColor(Color.white);
					g.setFont(new Font(Font.DIALOG, Font.BOLD, 16));

					String[] scoreName = s.nextLine().split(",");
					String name = scoreName[0];
					String score = scoreName[1];
					String date = scoreName[2];
					String time = scoreName[3];

					y += 42;
					g.drawString(name, x, y);
					x += 80;
					g.drawString(score, x, y);
					x += 80;
					g.drawString(date, x, y);
					x += 140;
					g.drawString(time, x, y);
					x = 60;
				}

				y += 42;
				g.setColor(Color.YELLOW);
				g.drawString("Your Score: " + score, x, y);
				s.close();
				g.setColor(Color.gray);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public void gameOver(int score) throws FileNotFoundException {
		frame3.setVisible(true);
		frame1.setVisible(false);
		this.score = score;
		timeDate t = new timeDate(getName(), score);
		frame3.repaint();
	}

	public void actionPerformed(ActionEvent e) {
//        if (e.getActionCommand().equals("Start")) {
//            gameRunner.setupGameloop();
//            frame1.setVisible(false);
		/* } else */ 
		if (e.getActionCommand().equals("Easy")) {
			game.setDifficulty("easy");
//            easy.setBorder(BorderFactory.createLineBorder(Color.green, 4));
//            easy.setBorderPainted(true);
//            medium.setBorderPainted(false);
//            hard.setBorderPainted(false);
//            easy.setBackground(Color.ORANGE);
//            medium.setBackground(start.getBackground());
//            hard.setBackground(start.getBackground());

			gameRunner.setupGameloop(); // new
			frame1.setVisible(false); // new
		} else if (e.getActionCommand().equals("Medium")) {
			game.setDifficulty("medium");
//			medium.setBorder(BorderFactory.createLineBorder(Color.orange, 4));
//			medium.setBorderPainted(true);
//			easy.setBorderPainted(false);
//			hard.setBorderPainted(false);
//			medium.setBackground(Color.ORANGE);
//			easy.setBackground(start.getBackground());
//			hard.setBackground(start.getBackground());
			gameRunner.setupGameloop(); // new
			frame1.setVisible(false); // new
		} else if (e.getActionCommand().equals("Hard")) {
			game.setDifficulty("hard");
//			hard.setBorder(BorderFactory.createLineBorder(Color.red, 4));
//			hard.setBorderPainted(true);
//			easy.setBorderPainted(false);
//			medium.setBorderPainted(false);
//			hard.setBackground(Color.ORANGE);
//			medium.setBackground(start.getBackground());
//			easy.setBackground(start.getBackground());
			gameRunner.setupGameloop(); // new
			frame1.setVisible(false); // new
		} else if (e.getActionCommand().equals("Replay")) {
			frame3.setVisible(false);
			gameRunner.end();
			new GameRunner();
		}

// else if (e.getActionCommand().equals("Map")) {
//            frame1.setVisible(false);
//            frame2.setVisible(true);
//        } else if (e.getActionCommand().equals("Back")) {
//            frame1.setVisible(true);
//            frame2.setVisible(false);
//        }
	}

	public class timeDate {
		timeDate(String name, int score) {
			ArrayList<String[]> lines = fileMatrix(game.getScoreBoardFile());
			lines = insertScore(lines, name, score);

			writeScores(fileMatrixToString(lines));
		}
      

		// assumes previous scores are already ordered from most to least
		public void writeScores(String scores) {
			try {
				FileWriter writer = new FileWriter(game.getScoreBoardFile());

				writer.write(scores);
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public ArrayList<String[]> fileMatrix(File fileName) {
			try {
				FileReader fr = new FileReader(fileName);
				BufferedReader br = new BufferedReader(fr);
				String line;
				ArrayList<String[]> lines = new ArrayList<String[]>();
				while ((line = br.readLine()) != null) {
					lines.add(line.split(","));
				}
				return lines;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		public String fileMatrixToString(ArrayList<String[]> scores) {
			String scoreBoard = "";
			for (String[] list : scores) {
				for (int i = 0; i < list.length; i++) {
					scoreBoard += list[i];
					if (i != list.length - 1) {
						scoreBoard += ",";
					}
				}
				scoreBoard += "\n";
			}
			return scoreBoard;
		}

		public ArrayList<String[]> insertScore(ArrayList<String[]> scores, String name, int score) {
			ArrayList<String[]> newScores = new ArrayList<String[]>();
			newScores.add(scores.get(0));
			boolean inserted = false;
			String[] newScore = { name, score + "",
					DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDateTime.now()),
					DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()) };

			if (scores.size() < 2) {
				newScores.add(newScore);
				return newScores;
			}

			int index = 1;

			for (int i = 1; i < scores.size(); i++) {
				if (Integer.parseInt(scores.get(i)[1]) < score) {
					index = i;
					i = 11;
				} else {
					index++;
				}
			}

			for (int i = 1, newScoresIndex = 0; i < scores.size() + 1 && newScoresIndex < 10; i++, newScoresIndex++) {
				if (i < index) {
					newScores.add(scores.get(i));
				} else if (i == index) {
					newScores.add(newScore);
				} else if (i > index) {
					newScores.add(scores.get(i - 1));
				}
			}

			return newScores;
		}
	}
}
