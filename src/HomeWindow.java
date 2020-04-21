import com.sun.tools.javac.Main;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.plaf.metal.MetalBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.UIManager.*;

public class HomeWindow extends JFrame {

    SudokuPanel sudokuPanel;
    JPanel jPanel;
    JLabel backgroundImage;
    JLabel nameLabel;
    JButton quitButton;
    JButton playSudokuButton;
    JButton completeSolutionButton;
    JButton checkSolutionButton;

    public static void main(String[] args) {
        try{
            for(LookAndFeelInfo info : getInstalledLookAndFeels()){
                if("Nimbus".equals(info.getName())){
                    setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch (Exception e){
            java.util.logging.Logger.getLogger(HomeWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
        HomeWindow homeWindow = new HomeWindow();
        homeWindow.setVisible(true);
    }

    public HomeWindow(){
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) ((dimension.getWidth() - getWidth()) / 3.7);
        int height = (int) ((dimension.getHeight() - getHeight()) / 8);

        setTitle("Sudoku Solution Validator");
        setUndecorated(true);
        setLocation(width,height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,500);

        jPanel = new JPanel();
        jPanel.setLayout(null);
        add(jPanel);
        validate();
        jPanel.setBounds(0,0,600,500);

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
        nameLabel = new JLabel("Sudoku Solution Validator");
        quitButton = new JButton("Quit");
        playSudokuButton = new JButton("Play Sudoku");
        completeSolutionButton = new JButton("Complete Solution");
        checkSolutionButton = new JButton("Validate your Solution");
        backgroundImage = new JLabel();

        backgroundImage.setIcon(new ImageIcon("images/Grey2.jpg"));

        nameLabel.setFont(new Font("monospaced", Font.BOLD + Font.ITALIC, 35));
        nameLabel.setForeground(Color.WHITE);

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

        playSudokuButton.setFont(new Font("Bradley Hand ITC", Font.BOLD, 20));
        playSudokuButton.setForeground(Color.BLACK);
        playSudokuButton.setBackground(Color.WHITE);
        playSudokuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jPanel.remove(nameLabel);
                jPanel.remove(playSudokuButton);
                jPanel.remove(completeSolutionButton);
                jPanel.remove(checkSolutionButton);
                sudokuPanel.setBounds(50,50,500,500);
                jPanel.add(sudokuPanel);
                repaint();
            }
        });


        completeSolutionButton.setFont(new Font("Bradley Hand ITC", Font.BOLD, 20));
        completeSolutionButton.setForeground(Color.BLACK);
        completeSolutionButton.setBackground(Color.WHITE);

        checkSolutionButton.setFont(new Font("Bradley Hand ITC", Font.BOLD, 20));
        checkSolutionButton.setForeground(Color.BLACK);
        checkSolutionButton.setBackground(Color.WHITE);

        backgroundImage.setBounds(0,0,600,500);
        quitButton.setBounds(440,430,120,40);
        playSudokuButton.setBounds(170,170,250,40);
        completeSolutionButton.setBounds(170,230,250,40);
        checkSolutionButton.setBounds(170,290,250,40);
        nameLabel.setBounds(35,30,540,50);

        jPanel.add(quitButton);
        jPanel.add(nameLabel);
        jPanel.add(playSudokuButton);
        jPanel.add(completeSolutionButton);
        jPanel.add(checkSolutionButton);
        jPanel.add(backgroundImage);
    }
}
