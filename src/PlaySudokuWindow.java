import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class PlaySudokuWindow extends JFrame {

    private static final long serialVersionUID = 4683382057400604928L;
    SudokuPanel sudokuPanel;
    JPanel jPanel;
    JLabel backgroundImage;
    JLabel nameLabel;
    JLabel timerLabel;
    JLabel timerNameLabel;
    JLabel hintNameLabel;
    JLabel hintLabel;
    JButton backButton;
    JButton submitButton;
    JButton hintButton;
    JButton saveButton;

    int second = 0;
    int minute = 0;
    int hint = 0;
    int[][] sudoku;

    public PlaySudokuWindow(final int width, final int height, final int[][] matrix, int time, int NoOfHint) {
        this(width, height, matrix, NoOfHint);
        minute = time / 60;
        second = time % 60;
    }

    public PlaySudokuWindow(final int width, final int height, final int[][] matrix, int NoOfHint) {
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

        hint = NoOfHint;
        sudoku = matrix;
        sudokuPanel = new SudokuPanel(sudoku);

        backgroundImage = new JLabel();
        backgroundImage.setIcon(new ImageIcon("assets/images/Grey1.jpg"));

        nameLabel = new JLabel();
        nameLabel.setText(" Play Sudoku ");
        nameLabel.setFont(new Font("Satisfy", Font.BOLD, 50));
        nameLabel.setForeground(Color.WHITE);

        hintNameLabel = new JLabel("Hint:");
        hintNameLabel.setFont(new Font("monospaced", Font.BOLD, 25));
        hintNameLabel.setForeground(Color.WHITE);

        hintLabel = new JLabel(hint < 10 ? "0" + Integer.toString(hint) : Integer.toString(hint));
        hintLabel.setFont(new Font("monospaced", Font.BOLD, 25));
        hintLabel.setForeground(Color.WHITE);

        timerNameLabel = new JLabel("Time:");
        timerNameLabel.setFont(new Font("monospaced", Font.BOLD, 25));
        timerNameLabel.setForeground(Color.WHITE);

        timerLabel = new JLabel((minute < 10 ? "0" + minute : minute) + ":" + (second < 10 ? "0" + second : second));
        timerLabel.setForeground(Color.WHITE);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 25));

        backButton = new JButton("Back");
        backButton.setFont(new Font("Bradley Hand ITC", Font.BOLD, 20));
        backButton.setForeground(Color.BLACK);
        backButton.setBackground(Color.WHITE);
        backButton.setBorder(null);
        backButton.setFocusable(false);
        backButton.setIcon(new ImageIcon("assets/images/previous.png"));
        backButton.setIconTextGap(10);
        backButton.addActionListener(e -> {
            final HomeWindow homeWindow = new HomeWindow();
            homeWindow.setVisible(true);
            dispose();
        });

        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Bradley Hand ITC", Font.BOLD, 20));
        submitButton.setForeground(Color.BLACK);
        submitButton.setBackground(Color.WHITE);
        submitButton.setBorder(null);
        submitButton.setFocusable(false);
        submitButton.setIcon(new ImageIcon("assets/images/accept.png"));
        submitButton.setIconTextGap(10);
        submitButton.addActionListener(e -> {
            final int[][] sudokuPanelMatrix = sudokuPanel.getSudokuMatrix();
            final Validate validate = new Validate(sudokuPanelMatrix);
            if (validate.isSudokuValid()) {
                final int highScore = second + minute * 60;
                try {
                    final BufferedReader br = new BufferedReader(new FileReader("assets/highScore.txt"));
                    int oldHighScore = Integer.parseInt(br.readLine());
                    br.close();

                    if (highScore < oldHighScore) {
                        String nameString = JOptionPane.showInputDialog(this, "Congratulation! You got the High Score!",
                                "Enter Your Name", JOptionPane.INFORMATION_MESSAGE);
                        final BufferedWriter bw = new BufferedWriter(new FileWriter("assets/highScore.txt"));
                        bw.write(Integer.toString(highScore));
                        bw.write("\n" + nameString);
                        bw.close();
                    } else {
                        JOptionPane.showMessageDialog(this, "You did not make the High score. Try again.", "Nice Game!",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                    HomeWindow homeWindow = new HomeWindow();
                    homeWindow.setVisible(true);
                    dispose();
                } catch (final Exception exception) {
                    System.out.println(exception + ": " + exception.getMessage());
                    JOptionPane.showMessageDialog(this, "High Score file not found.", "File Not Found!",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "This sudoku is not valid.", "Invalid Sudoku",
                        JOptionPane.ERROR_MESSAGE);
            }
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
            final SolveSudoku solveSudoku = new SolveSudoku(sudokuPanel.getSudokuMatrix());
            if (solveSudoku.isSolvable()) {
                if (hint == 0) {
                    JOptionPane.showMessageDialog(this, "You have used all your hints.", "No more hints",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    hint--;
                    hintLabel.setText(hint < 10 ? "0" + Integer.toString(hint) : Integer.toString(hint));
                    sudokuPanel.setHintonFocusedCell(solveSudoku.getSolvedSudoku());
                }
            } else {
                JOptionPane.showMessageDialog(this, "This sudoku is not valid.", "Invalid Sudoku",
                        JOptionPane.ERROR_MESSAGE);
            }
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
            int n = JOptionPane.showConfirmDialog(this, "Do you want to Save and Quit?", "Save and Quit?",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (n == JOptionPane.YES_OPTION) {
                int[][] sudokuPanelMatrix = sudokuPanel.getSudokuMatrix();
                String temp = "";
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        temp += Integer.toString(sudokuPanelMatrix[i][j]);
                    }
                }
                try {
                    final BufferedWriter bw = new BufferedWriter(new FileWriter("assets/save.txt"));
                    bw.write(temp + "\n");
                    temp = Integer.toString(second + minute * 60);
                    bw.write(temp + "\n");
                    temp = Integer.toString(hint);
                    bw.write(temp);
                    bw.close();
                } catch (final IOException ex) {
                    System.out.println(ex + ": " + ex.getMessage());
                    JOptionPane.showMessageDialog(this, "Game could not be saved. Restart the Game.", "Could not save!",
                            JOptionPane.ERROR_MESSAGE);
                }
                dispose();
            }
        });

        backgroundImage.setBounds(0, 0, 600, 600);
        backButton.setBounds(30, 510, 120, 40);
        nameLabel.setBounds(150, 20, 310, 70);
        timerNameLabel.setBounds(430, 150, 120, 40);
        timerLabel.setBounds(510, 150, 120, 40);
        hintNameLabel.setBounds(450, 190, 120, 40);
        hintLabel.setBounds(525, 190, 120, 40);
        hintButton.setBounds(450, 250, 120, 50);
        submitButton.setBounds(450, 320, 120, 50);
        saveButton.setBounds(450, 390, 120, 50);
        sudokuPanel.setBounds(30, 105, 385, 385);

        jPanel.add(timerNameLabel);
        jPanel.add(submitButton);
        jPanel.add(hintButton);
        jPanel.add(saveButton);
        jPanel.add(timerLabel);
        jPanel.add(nameLabel);
        jPanel.add(hintNameLabel);
        jPanel.add(hintLabel);
        jPanel.add(sudokuPanel);
        jPanel.add(backButton);
        jPanel.add(backgroundImage);
        repaint();

        startTimer();
    }

    void startTimer() {
        final java.util.Timer timer = new Timer();

        final TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                second++;
                if (second == 60) {
                    second = 0;
                    minute++;
                }
                timerLabel.setText((minute < 10 ? "0" + minute : minute) + ":" + (second < 10 ? "0" + second : second));
            }
        };
        timer.scheduleAtFixedRate(timerTask, 1000, 1000);
    }
}
