import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;

public class SelectDifficultyWindow extends JFrame {

    private static final long serialVersionUID = -3199890274435724350L;
    JPanel jPanel;
    JLabel backgroundImage;
    JLabel nameLabel;
    JButton quitButton;
    JButton easyButton;
    JButton normalButton;
    JButton hardButton;
    JButton resumeButton;

    int time = 0;
    int[][] matrix = new int[9][9];

    public SelectDifficultyWindow(int width, int height) {
        setTitle("Sudoku Solution Validator");
        setResizable(false);
        setLocation(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 600);

        jPanel = new JPanel();
        jPanel.setLayout(null);
        add(jPanel);
        validate();
        jPanel.setBounds(0, 0, 600, 600);

        backgroundImage = new JLabel();
        backgroundImage.setIcon(new ImageIcon("assets/images/Grey1.jpg"));

        nameLabel = new JLabel(" Select Difficulty ");
        nameLabel.setFont(new Font("Satisfy", Font.BOLD, 50));
        nameLabel.setForeground(Color.WHITE);

        quitButton = new JButton("Quit");
        quitButton.setFont(new Font("Bradley Hand ITC", Font.BOLD, 20));
        quitButton.setForeground(Color.BLACK);
        quitButton.setBackground(Color.WHITE);
        quitButton.setBorder(null);
        quitButton.setFocusable(false);
        quitButton.setIcon(new ImageIcon("assets/images/delete.png"));
        quitButton.setIconTextGap(10);
        quitButton.addActionListener(e -> System.exit(0));

        easyButton = new JButton("Easy");
        easyButton.setFont(new Font("Bradley Hand ITC", Font.BOLD, 20));
        easyButton.setForeground(Color.BLACK);
        easyButton.setBackground(Color.WHITE);
        easyButton.addActionListener(e -> {
            GenerateSudoku generateSudoku = new GenerateSudoku();
            matrix = generateSudoku.createGame(GenerateSudoku.EASY);
            PlaySudokuWindow playSudokuWindow = new PlaySudokuWindow(width, height, matrix);
            playSudokuWindow.setVisible(true);
            dispose();
        });
        easyButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                easyButton.setBackground(Color.BLUE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                easyButton.setBackground(Color.WHITE);
            }
        });

        normalButton = new JButton("Normal");
        normalButton.setFont(new Font("Bradley Hand ITC", Font.BOLD, 20));
        normalButton.setForeground(Color.BLACK);
        normalButton.setBackground(Color.WHITE);
        normalButton.addActionListener(e -> {
            GenerateSudoku generateSudoku = new GenerateSudoku();
            matrix = generateSudoku.createGame(GenerateSudoku.NORMAL);
            PlaySudokuWindow playSudokuWindow = new PlaySudokuWindow(width, height, matrix);
            playSudokuWindow.setVisible(true);
            dispose();
        });
        normalButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                normalButton.setBackground(Color.BLUE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                normalButton.setBackground(Color.WHITE);
            }
        });

        hardButton = new JButton("Hard");
        hardButton.setFont(new Font("Bradley Hand ITC", Font.BOLD, 20));
        hardButton.setForeground(Color.BLACK);
        hardButton.setBackground(Color.WHITE);
        hardButton.addActionListener(e -> {
            GenerateSudoku generateSudoku = new GenerateSudoku();
            matrix = generateSudoku.createGame(GenerateSudoku.HARD);
            PlaySudokuWindow playSudokuWindow = new PlaySudokuWindow(width, height, matrix);
            playSudokuWindow.setVisible(true);
            dispose();
        });
        hardButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                hardButton.setBackground(Color.BLUE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hardButton.setBackground(Color.WHITE);
            }
        });

        resumeButton = new JButton(getSavedGame() ? "Resume" : "No Saved Game");
        resumeButton.setFont(new Font("Bradley Hand ITC", Font.BOLD, 20));
        resumeButton.setForeground(Color.BLACK);
        resumeButton.setBackground(Color.WHITE);
        resumeButton.setEnabled(getSavedGame());
        resumeButton.addActionListener(e -> {
            PlaySudokuWindow playSudokuWindow = new PlaySudokuWindow(width, height, matrix, time);
            playSudokuWindow.setVisible(true);
            dispose();
        });

        backgroundImage.setBounds(0, 0, 600, 600);
        quitButton.setBounds(460, 510, 120, 40);
        easyButton.setBounds(170, 170, 250, 40);
        normalButton.setBounds(170, 230, 250, 40);
        hardButton.setBounds(170, 290, 250, 40);
        resumeButton.setBounds(170, 350, 250, 40);
        nameLabel.setBounds(130, 20, 380, 70);

        jPanel.add(quitButton);
        jPanel.add(nameLabel);
        jPanel.add(easyButton);
        jPanel.add(normalButton);
        jPanel.add(hardButton);
        jPanel.add(resumeButton);
        jPanel.add(backgroundImage);
        repaint();
    }

    private boolean getSavedGame() {
        char[] sudokuLine = new char[81];
        String tempString = "";
        String timeString = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader("assets/save.txt"));
            tempString = br.readLine();
            timeString = br.readLine();
            br.close();

            sudokuLine = tempString.toCharArray();
            int index = 0;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    matrix[i][j] = Integer.parseInt(String.valueOf(sudokuLine[index]));
                    index++;
                }
            }
            time = Integer.parseInt(timeString);
            return true;
        } catch (Exception e) {
            System.out.println("The save File was not found.");
            return false;
        }
    }
}