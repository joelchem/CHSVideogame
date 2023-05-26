<<<<<<< Updated upstream
public class MainMenu{
=======
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
    private JButton start;
    private JButton easy;
    private JButton medium;
    private JButton hard;
    private JButton map;
    private JButton back;
    private JButton replayButton;
    private JTextField nameInp;
    private JFrame frame1;
    private JFrame frame2;
    private JFrame frame3;
    private JPanel contentPane3a;
    private JPanel contentPane3b;
    private ImageIcon background = null;
    private Image pic;
    private final int width = 1000;
    private final int height = 800;
    private Game game;
    private GameRunner gameRunner;
    private int score;
    private String name;
    private File scoreBoard;

    public MainMenu(Game g, GameRunner gr) {
    	game = g;
    	gameRunner = gr;
    	
        frame1 = new JFrame();
        frame2 = new JFrame();
        JLabel label = new JLabel();
        JLabel label2 = new JLabel();
        JLabel replayLabel = new JLabel();

        label2.setIcon(new ImageIcon(getClass().getClassLoader().getResource("CHS_MenuBackground.png")));
        label.setIcon(new ImageIcon(getClass().getClassLoader().getResource("CHS_MenuBackground.png")));
        frame1.add(label);
        frame2.add(label2);

        ImageIcon startButton = new ImageIcon(getClass().getClassLoader().
                getResource("CHS_StartButton.png"));
        ImageIcon title = new ImageIcon(getClass().getClassLoader().
                getResource("CHSVideogame_title.png"));
        ImageIcon title2 = new ImageIcon(getClass().getClassLoader().
                getResource("CHSVideogame_title2.png"));
        ImageIcon mapButton = new ImageIcon(getClass().getClassLoader().
                getResource("CHS_MapButton.png"));
        ImageIcon backButton = new ImageIcon(getClass().getClassLoader().
                getResource("CHS_BackButton.png"));
        ImageIcon easyButton = new ImageIcon(getClass().getClassLoader().
                getResource("CHS_EasyButton.png"));
        ImageIcon normalButton = new ImageIcon(getClass().getClassLoader().
                getResource("CHS_NormalButton.png"));
        ImageIcon hardButton = new ImageIcon(getClass().getClassLoader().getResource("CHS_Hard.png")); 

        ImageIcon replayImage = new ImageIcon(getClass().getClassLoader().getResource("PlayAgain.png"));

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
        

        // replayButton = new JButton("", replayImage);
        // replayButton.setBounds(425, 250, 170, 70);
        // replayButton.setActionCommand("replay");
        // replayButton.addActionListener(this);

        // label2.add(replayButton);

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
        frame1.setResizable(false);
        frame2.setResizable(false);
        frame3.setResizable(false);
    }
>>>>>>> Stashed changes
    
}