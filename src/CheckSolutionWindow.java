import javax.swing.*;
import java.awt.*;

public class CheckSolutionWindow extends JFrame{

    SudokuPanel sudokuPanel;
    JPanel jPanel;
    JLabel backgroundImage;
    JLabel nameLabel;
    JButton backButton;
    JButton checkButton;
    JButton hintButton;
    JButton saveButton;

    public CheckSolutionWindow(int width, int height){
        setTitle("Sudoku Solution Validator");
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
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        sudokuPanel = new SudokuPanel(sudoku);


        backgroundImage = new JLabel();
        backgroundImage.setIcon(new ImageIcon("assets/images/Grey1.jpg"));

        nameLabel = new JLabel();
        nameLabel.setText("Validate your Solution");
        nameLabel.setFont(new Font("monospaced", Font.BOLD + Font.ITALIC, 35));
        nameLabel.setForeground(Color.WHITE);

        backButton = new JButton("Back");
        backButton.setFont(new Font("Bradley Hand ITC", Font.BOLD, 20));
        backButton.setForeground(Color.BLACK);
        backButton.setBackground(Color.WHITE);
        backButton.setBorder(null);
        backButton.setFocusable(false);
        backButton.setIcon(new ImageIcon("assets/images/previous.png"));
        backButton.setIconTextGap(10);
        backButton.addActionListener(e -> {
            HomeWindow homeWindow = new HomeWindow();
            homeWindow.setVisible(true);
            dispose();
        });

        checkButton = new JButton("Check");
        checkButton.setFont(new Font("Bradley Hand ITC", Font.BOLD, 20));
        checkButton.setForeground(Color.BLACK);
        checkButton.setBackground(Color.WHITE);
        checkButton.setBorder(null);
        checkButton.setFocusable(false);
        checkButton.setIcon(new ImageIcon("assets/images/accept.png"));
        checkButton.setIconTextGap(10);
        checkButton.addActionListener(e -> {
            int[][] sudokuPanelMatrix = sudokuPanel.getSudokuMatrix();
            Validate validate = new Validate(sudokuPanelMatrix);
        });

        hintButton = new JButton("Hint    ");
        hintButton.setFont(new Font("Bradley Hand ITC", Font.BOLD, 20));
        hintButton.setForeground(Color.BLACK);
        hintButton.setBackground(Color.WHITE);
        hintButton.setBorder(null);
        hintButton.setFocusable(false);
        hintButton.setIcon(new ImageIcon("assets/images/hint.png"));
        hintButton.setIconTextGap(10);
        hintButton.addActionListener(e -> {

        });

        saveButton = new JButton("Save  ");
        saveButton.setFont(new Font("Bradley Hand ITC", Font.BOLD, 20));
        saveButton.setForeground(Color.BLACK);
        saveButton.setBackground(Color.WHITE);
        saveButton.setBorder(null);
        saveButton.setFocusable(false);
        saveButton.setIcon(new ImageIcon("assets/images/save.png"));
        saveButton.setIconTextGap(10);
        saveButton.addActionListener(e -> {

        });

        backgroundImage.setBounds(0,0,600,600);
        backButton.setBounds(20,510,120,40);
        hintButton.setBounds(450,150,120,50);
        checkButton.setBounds(450,220,120,50);
        saveButton.setBounds(450,290,120,50);
        sudokuPanel.setBounds(30,90,385,385);


        jPanel.add(checkButton);
        jPanel.add(hintButton);
        jPanel.add(saveButton);
        jPanel.add(nameLabel);
        jPanel.add(sudokuPanel);
        jPanel.add(backButton);
        jPanel.add(backgroundImage);
        repaint();

    }
}