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
import javax.swing.*;
public class MainMenu extends JPanel implements ActionListener {
    JButton start;
    JButton easy;
    JButton medium;
    JButton hard;
    JButton map;
    JButton back;
    JTextField nameInp;
    JFrame frame1;
    JFrame frame2;
    JFrame frame3;
    JPanel contentPane3a;
    JPanel contentPane3b;
    ImageIcon background = null;
    Image pic;
    private final int width = 1000;
    private final int height = 800;
    Game game;
    GameRunner gameRunner;
    File scoreBoard;

    public MainMenu(Game g, GameRunner gr) {
    	game = g;
    	gameRunner = gr;
    	
        frame1 = new JFrame();
        frame2 = new JFrame();
        JLabel label = new JLabel();
        JLabel label2 = new JLabel();
        label2.setIcon(new ImageIcon("assets//CHS_MenuBackground.png"));
        label.setIcon(new ImageIcon("assets//CHS_MenuBackground.png"));
        frame1.add(label);
        frame2.add(label2);
        ImageIcon startButton = new ImageIcon("assets//CHS_StartButton.png");
        ImageIcon title = new ImageIcon("assets//CHSVideogame_title.png");
        ImageIcon title2 = new ImageIcon("assets//CHSVideogame_title2.png");
        ImageIcon mapButton = new ImageIcon("assets//CHS_MapButton.png");
        ImageIcon backButton = new ImageIcon("assets//CHS_BackButton.png");
        ImageIcon easyButton = new ImageIcon("assets//CHS_EasyButton.png");
        ImageIcon normalButton = new ImageIcon("assets//CHS_NormalButton.png");
        ImageIcon hardButton = new ImageIcon("assets//CHS_Hard.png");
        JLabel picLabel = new JLabel(title);
        JLabel picLabel2 = new JLabel(title2);
        picLabel.setBounds(-20, -310, 1000, 900);
        picLabel2.setBounds(0, -210, 1000, 900);
        label.add(picLabel);
        label.add(picLabel2);
        start = new JButton("", startButton);
        start.setBounds(400, 400, 200, 105);
        start.setActionCommand("Start");
        start.addActionListener(this);
        label.add(start);
        nameInp = new JTextField(8);
        nameInp.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        nameInp.setText("Unnamed");
        nameInp.setBounds(400, 300, 200, 50);
        label.add(nameInp);
        easy = new JButton("", easyButton);
        easy.setBounds(425, 250, 170, 70);
        easy.setActionCommand("Easy");
        easy.addActionListener(this);
        label2.add(easy);
        label2.add(easy);
        medium = new JButton("", normalButton);
        medium.setBounds(425, 350, 170, 70);
        medium.setActionCommand("Medium");
        medium.addActionListener(this);
        label2.add(medium);
        hard = new JButton("", hardButton);
        hard.setBounds(425, 450, 170, 70);
        hard.setActionCommand("Hard");
        hard.addActionListener(this);
        label2.add(hard);
        map = new JButton("", mapButton);
        map.setBounds(400, 550, 200, 105);
        map.setActionCommand("Map");
        map.addActionListener(this);
        label.add(map);
        back = new JButton("", backButton);
        back.setBounds(50, 600, 150, 50);
        back.setActionCommand("Back");
        back.addActionListener(this);
        label2.add(back);
        //this.game = game;
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
    
    public String getName() {
    	return nameInp.getText();
    }
    
    public class JScoreBoard extends JPanel {
        public void paintComponent(Graphics g) {

            g.setColor(Color.black);
            g.fillRect(0, 0, 1000, 1000);
            Scanner s;
            try {
                s = new Scanner(game.getScoreBoardFile());
                int x = 60;
                int y = 0;
                while (s.hasNextLine()) {
                    g.setColor(Color.white);
                    g.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
                    String[] scoreName = s.nextLine().split(",");
                    String name = scoreName[0];
                    String score = scoreName[1];
                    String date = scoreName[2];
                    String time = scoreName[3];
                    y += 50;
                    g.drawString(name, x, y);
                    x += 80;
                    g.drawString(score, x, y);
                    x += 80;
                    g.drawString(date, x, y);
                    x += 140;
                    g.drawString(time, x, y);
                    x = 60;
                }
                s.close();
                g.setColor(Color.gray);

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    public void gameOver(int score) throws FileNotFoundException {
        frame3.setVisible(true);
        frame1.setVisible(false);

        timeDate t = new timeDate(getName(), score);
        frame3.repaint();
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Start")) {
            gameRunner.setupGameloop();
            frame1.setVisible(false);
        } else if (e.getActionCommand().equals("Easy")) {
            game.setDifficulty("easy");
            easy.setBorder(BorderFactory.createLineBorder(Color.green, 4));
            easy.setBorderPainted(true);
            medium.setBorderPainted(false);
            hard.setBorderPainted(false);
            easy.setBackground(Color.ORANGE);
            medium.setBackground(start.getBackground());
            hard.setBackground(start.getBackground());
        } else if (e.getActionCommand().equals("Medium")) {
            game.setDifficulty("medium");
            medium.setBorder(BorderFactory.createLineBorder(Color.orange, 4));
            medium.setBorderPainted(true);
            easy.setBorderPainted(false);
            hard.setBorderPainted(false);
            medium.setBackground(Color.ORANGE);
            easy.setBackground(start.getBackground());
            hard.setBackground(start.getBackground());
        } else if (e.getActionCommand().equals("Hard")) {
            game.setDifficulty("hard");
            hard.setBorder(BorderFactory.createLineBorder(Color.red, 4));
            hard.setBorderPainted(true);
            easy.setBorderPainted(false);
            medium.setBorderPainted(false);
            hard.setBackground(Color.ORANGE);
            medium.setBackground(start.getBackground());
            easy.setBackground(start.getBackground());
        } else if (e.getActionCommand().equals("Map")) {
            frame1.setVisible(false);
            frame2.setVisible(true);
        } else if (e.getActionCommand().equals("Back")) {
            frame1.setVisible(true);
            frame2.setVisible(false);
        }
    }
    public class timeDate {
        timeDate(String name, int score) {
            ArrayList < String[] > lines = fileMatrix(game.getScoreBoardFile());
            lines = insertScore(lines, name, score);

            writeScores(fileMatrixToString(lines));
        }
        public String addToString(String name, int score) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd,HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            return name + "," + score + "," + dtf.format(now) + "\n";
            // writing files needs to be done with a whole string, not multiple write commands (or else it would erase file between commands)
        }
        // assumes previous scores are already ordered from most to least
        public void writeScores(String scores) {
            try {
                FileWriter writer = new FileWriter(game.getScoreBoardFile());

                writer.write(scores);
                writer.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        public ArrayList < String[] > fileMatrix(File fileName) {
            try {
                FileReader fr = new FileReader(fileName);
                BufferedReader br = new BufferedReader(fr);
                String line;
                ArrayList < String[] > lines = new ArrayList < String[] > ();
                while ((line = br.readLine()) != null) {
                    lines.add(line.split(","));
                }
                return lines;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }
        public String fileMatrixToString(ArrayList < String[] > scores) {
            String scoreBoard = "";
            for (String[] list: scores) {
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
        public ArrayList < String[] > insertScore(ArrayList < String[] > scores, String name, int score) {
            ArrayList < String[] > newScores = new ArrayList < String[] > ();
            newScores.add(scores.get(0));
            boolean inserted = false;
            String[] newScore = {
                name,
                score + "",
                DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDateTime.now()),
                DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now())
            };
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



