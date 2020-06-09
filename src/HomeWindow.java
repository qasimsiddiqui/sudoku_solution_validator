import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import java.util.Timer;

import static javax.swing.UIManager.*;

public class HomeWindow extends JFrame {

    SudokuPanel sudokuPanel;
    JPanel jPanel;
    JLabel backgroundImage;
    JLabel nameLabel;
    JLabel timerLabel;
    JLabel timerNameLabel;
    JButton quitButton;
    JButton backButton;
    JButton checkButton;
    JButton hintButton;
    JButton saveButton;
    JButton playSudokuButton;
    JButton completeSolutionButton;
    JButton checkSolutionButton;

    int second = 0;
    int minute = 0;

    public static void main(String[] args) {
        try{
            for(LookAndFeelInfo info : getInstalledLookAndFeels()){
                if("Nimbus".equals(info.getName())){
                    setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch (Exception e){
            System.out.println(e+ "  =  "+e.getMessage());
        }
        HomeWindow homeWindow = new HomeWindow();
        homeWindow.setVisible(true);
    }

    public HomeWindow(){
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) ((dimension.getWidth() - getWidth()) / 3.7);
        int height = (int) ((dimension.getHeight() - getHeight()) / 8);

        setTitle("Sudoku Solution Validator");
        //setUndecorated(true);
        setResizable(false);
        setLocation(width,height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,600);

        jPanel = new JPanel();
        jPanel.setLayout(null);
        add(jPanel);
        validate();
        jPanel.setBounds(0,0,600,600);

        int[][] sudoku = new int[][]{
                {1, 0, 0, 0, 6, 0, 0, 0, 0},
                {6, 0, 0, 5, 0, 0, 0, 0, 4},
                {0, 2, 0, 4, 0, 0, 5, 0, 0},
                {0, 8, 0, 0, 0, 5, 0, 0, 7},
                {0, 0, 0, 0, 0, 0, 9, 3, 0},
                {0, 0, 4, 3, 0, 0, 0, 0, 8},
                {0, 9, 0, 0, 0, 7, 0, 5, 0},
                {0, 7, 0, 0, 0, 0, 6, 0, 9},
                {0, 0, 0, 1, 0, 0, 4, 0, 0}
        };

        sudokuPanel = new SudokuPanel(sudoku);


        backgroundImage = new JLabel();
        backgroundImage.setIcon(new ImageIcon("images/Grey1.jpg"));

        nameLabel = new JLabel();
        nameLabel.setFont(new Font("monospaced", Font.BOLD + Font.ITALIC, 35));
        nameLabel.setForeground(Color.WHITE);

        timerNameLabel = new JLabel("Time:");
        timerNameLabel.setFont(new Font("monospaced", Font.BOLD, 25));
        timerNameLabel.setForeground(Color.WHITE);

        timerLabel = new JLabel("00:00");
        timerLabel.setForeground(Color.WHITE);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 25));
        java.util.Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                second++;
                if(second == 60){
                    second = 0;
                    minute++;
                }
                timerLabel.setText((minute < 10 ? "0"+minute : minute) + ":" + (second < 10 ? "0"+second : second));
            }
        };
        timer.scheduleAtFixedRate(timerTask,1000,1000);


        quitButton = new JButton("Quit");
        quitButton.setFont(new Font("Bradley Hand ITC", Font.BOLD, 20));
        quitButton.setForeground(Color.BLACK);
        quitButton.setBackground(Color.WHITE);
        quitButton.setBorder(null);
        quitButton.setFocusable(false);
        quitButton.setIcon(new ImageIcon("images/delete.png"));
        quitButton.setIconTextGap(10);
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        backButton = new JButton("Back");
        backButton.setFont(new Font("Bradley Hand ITC", Font.BOLD, 20));
        backButton.setForeground(Color.BLACK);
        backButton.setBackground(Color.WHITE);
        backButton.setBorder(null);
        backButton.setFocusable(false);
        backButton.setIcon(new ImageIcon("images/previous.png"));
        backButton.setIconTextGap(10);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createHomeScreen();
            }
        });

        checkButton = new JButton("Check");
        checkButton.setFont(new Font("Bradley Hand ITC", Font.BOLD, 20));
        checkButton.setForeground(Color.BLACK);
        checkButton.setBackground(Color.WHITE);
        checkButton.setBorder(null);
        checkButton.setFocusable(false);
        checkButton.setIcon(new ImageIcon("images/accept.png"));
        checkButton.setIconTextGap(10);
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createHomeScreen();
            }
        });

        hintButton = new JButton("Hint    ");
        hintButton.setFont(new Font("Bradley Hand ITC", Font.BOLD, 20));
        hintButton.setForeground(Color.BLACK);
        hintButton.setBackground(Color.WHITE);
        hintButton.setBorder(null);
        hintButton.setFocusable(false);
        hintButton.setIcon(new ImageIcon("images/hint.png"));
        hintButton.setIconTextGap(10);
        hintButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 int[][] sudokuMatrix = {
                         {50, 3, 4, 6, 7, 8, 9, 1, 2},
                         {6, 7, 2, 1, 9, 5, 3, 4, 8},
                         {1, 9, 8, 3, 4, 2, 5, 6, 7},
                         {8, 5, 9, 7, 6, 1, 4, 2, 3},
                         {4, 2, 6, 8, 5, 3, 7, 9, 1},
                         {7, 1, 3, 9, 2, 4, 8, 5, 6},
                         {9, 6, 1, 5, 3, 7, 2, 8, 4},
                         {2, 8, 7, 4, 1, 9, 6, 3, 5},
                         {3, 4, 5, 2, 8, 6, 1, 7, 9}};
                 Validate validate = new Validate(sudokuMatrix);
                 System.out.println("hintButton = " + validate.isValid());
             }
        });

        saveButton = new JButton("Save  ");
        saveButton.setFont(new Font("Bradley Hand ITC", Font.BOLD, 20));
        saveButton.setForeground(Color.BLACK);
        saveButton.setBackground(Color.WHITE);
        saveButton.setBorder(null);
        saveButton.setFocusable(false);
        saveButton.setIcon(new ImageIcon("images/save.png"));
        saveButton.setIconTextGap(10);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createHomeScreen();
            }
        });

        playSudokuButton = new JButton("Play Sudoku");
        playSudokuButton.setFont(new Font("Bradley Hand ITC", Font.BOLD, 20));
        playSudokuButton.setForeground(Color.BLACK);
        playSudokuButton.setBackground(Color.WHITE);
        playSudokuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createPlaySudokuScreen();
            }
        });

        completeSolutionButton = new JButton("Complete Solution");
        completeSolutionButton.setFont(new Font("Bradley Hand ITC", Font.BOLD, 20));
        completeSolutionButton.setForeground(Color.BLACK);
        completeSolutionButton.setBackground(Color.WHITE);


        checkSolutionButton = new JButton("Validate your Solution");
        checkSolutionButton.setFont(new Font("Bradley Hand ITC", Font.BOLD, 20));
        checkSolutionButton.setForeground(Color.BLACK);
        checkSolutionButton.setBackground(Color.WHITE);

        backgroundImage.setBounds(0,0,600,600);
        quitButton.setBounds(460,510,120,40);
        backButton.setBounds(20,510,120,40);
        playSudokuButton.setBounds(170,170,250,40);
        completeSolutionButton.setBounds(170,230,250,40);
        checkSolutionButton.setBounds(170,290,250,40);
        nameLabel.setBounds(35,30,540,50);
        timerNameLabel.setBounds(430,100,120,40);
        timerLabel.setBounds(510,100,120,40);
        hintButton.setBounds(450,150,120,50);
        checkButton.setBounds(450,220,120,50);
        saveButton.setBounds(450,290,120,50);
        sudokuPanel.setBounds(30,90,385,385);

        createHomeScreen();
    }

    void createHomeScreen(){
        jPanel.removeAll();

        nameLabel.setText("Sudoku Solution Validator");
        nameLabel.setBounds(35,30,540,50);

        jPanel.add(quitButton);
        jPanel.add(nameLabel);
        jPanel.add(playSudokuButton);
        jPanel.add(completeSolutionButton);
        jPanel.add(checkSolutionButton);
        jPanel.add(backgroundImage);
        repaint();
    }

    void createPlaySudokuScreen(){
        jPanel.removeAll();

        nameLabel.setText("Play Sudoku");
        nameLabel.setBounds(170,30,250,50);

        jPanel.add(timerNameLabel);
        jPanel.add(checkButton);
        jPanel.add(hintButton);
        jPanel.add(saveButton);
        jPanel.add(timerLabel);
        jPanel.add(nameLabel);
        jPanel.add(sudokuPanel);
        jPanel.add(quitButton);
        jPanel.add(backButton);
        jPanel.add(backgroundImage);
        repaint();
    }
}
