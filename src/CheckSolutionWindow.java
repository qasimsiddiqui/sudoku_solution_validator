import javax.swing.*;
import java.awt.*;

public class CheckSolutionWindow extends JFrame {

    private static final long serialVersionUID = -6340674619705334143L;
    SudokuPanel sudokuPanel;
    JPanel jPanel;
    JLabel backgroundImage;
    JLabel nameLabel;
    JButton backButton;
    JButton checkButton;
    JButton hintButton;

    public CheckSolutionWindow(int width, int height) {
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

        // int[][] sudoku = new int[][]{
        // {0, 0, 0, 0, 0, 0, 0, 0, 0},
        // {0, 0, 0, 0, 0, 0, 0, 0, 0},
        // {0, 0, 0, 0, 0, 0, 0, 0, 0},
        // {0, 0, 0, 0, 0, 0, 0, 0, 0},
        // {0, 0, 0, 0, 0, 0, 0, 0, 0},
        // {0, 0, 0, 0, 0, 0, 0, 0, 0},
        // {0, 0, 0, 0, 0, 0, 0, 0, 0},
        // {0, 0, 0, 0, 0, 0, 0, 0, 0},
        // {0, 0, 0, 0, 0, 0, 0, 0, 0}
        // };

        int[][] sudoku = new int[][] { { 5, 3, 4, 6, 7, 8, 9, 1, 2 }, { 6, 7, 2, 1, 9, 5, 3, 4, 8 },
                { 1, 9, 8, 3, 4, 2, 5, 6, 7 }, { 8, 5, 9, 7, 6, 1, 4, 2, 3 }, { 4, 2, 6, 8, 5, 3, 7, 9, 1 },
                { 7, 1, 3, 9, 2, 4, 8, 5, 6 }, { 9, 6, 1, 5, 3, 7, 2, 8, 4 }, { 2, 8, 7, 4, 1, 9, 6, 3, 5 },
                { 3, 4, 5, 2, 8, 6, 1, 7, 9 } };
        sudokuPanel = new SudokuPanel(sudoku);

        backgroundImage = new JLabel();
        backgroundImage.setIcon(new ImageIcon("assets/images/Grey1.jpg"));

        nameLabel = new JLabel();
        nameLabel.setText(" Validate your Solution ");
        nameLabel.setFont(new Font("Satisfy", Font.BOLD, 50));
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
            validate.printSudoku();
            if (!validate.isSudokuValid()) {
                JOptionPane.showMessageDialog(null, "Invalid Sudoku", "INVALID SUDOKU", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "You have entered a valid Sudoku Solution.\nCongratulations !!",
                        "Congratulations !!", JOptionPane.INFORMATION_MESSAGE);
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
            int[][] sudokuPanelMatrix = sudokuPanel.getSudokuMatrix();
            Validate validate = new Validate(sudokuPanelMatrix);
            boolean[] valid = validate.getValid();
            sudokuPanel.markAsInvalid(valid);
            if (validate.isSudokuValid()) {
                JOptionPane.showMessageDialog(null, "You have entered a valid Sudoku Solution.\nCongratulations !!",
                        "Congratulations !!", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        backgroundImage.setBounds(0, 0, 600, 600);
        backButton.setBounds(30, 510, 120, 40);
        hintButton.setBounds(450, 240, 120, 50);
        checkButton.setBounds(450, 310, 120, 50);
        sudokuPanel.setBounds(30, 105, 385, 385);
        nameLabel.setBounds(50, 20, 490, 70);

        jPanel.add(checkButton);
        jPanel.add(hintButton);
        jPanel.add(nameLabel);
        jPanel.add(sudokuPanel);
        jPanel.add(backButton);
        jPanel.add(backgroundImage);
        repaint();
    }
}