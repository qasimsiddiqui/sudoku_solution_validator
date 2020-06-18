import javax.swing.*;
import java.awt.*;

import static javax.swing.UIManager.*;

public class HomeWindow extends JFrame {

    JPanel jPanel;
    JLabel backgroundImage;
    JLabel nameLabel;
    JButton quitButton;
    JButton playSudokuButton;
    JButton solveSolutionButton;
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
        setResizable(false);
        setLocation(width,height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,600);

        jPanel = new JPanel();
        jPanel.setLayout(null);
        add(jPanel);
        validate();
        jPanel.setBounds(0,0,600,600);

        backgroundImage = new JLabel();
        backgroundImage.setIcon(new ImageIcon("assets/images/Grey1.jpg"));

        nameLabel = new JLabel(" Sudoku Solution Validator");
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

        playSudokuButton = new JButton("Play Sudoku");
        playSudokuButton.setFont(new Font("Bradley Hand ITC", Font.BOLD, 20));
        playSudokuButton.setForeground(Color.BLACK);
        playSudokuButton.setBackground(Color.WHITE);
        playSudokuButton.addActionListener(e -> {
            PlaySudokuWindow playSudokuWindow = new PlaySudokuWindow(width,height);
            playSudokuWindow.setVisible(true);
            dispose();
        });

        solveSolutionButton = new JButton("Solve Solution");
        solveSolutionButton.setFont(new Font("Bradley Hand ITC", Font.BOLD, 20));
        solveSolutionButton.setForeground(Color.BLACK);
        solveSolutionButton.setBackground(Color.WHITE);
        solveSolutionButton.addActionListener(e -> {
            SolveSudokuWindow solveSudokuWindow = new SolveSudokuWindow(width,height);
            solveSudokuWindow.setVisible(true);
            dispose();
        });

        checkSolutionButton = new JButton("Validate your Solution");
        checkSolutionButton.setFont(new Font("Bradley Hand ITC", Font.BOLD, 20));
        checkSolutionButton.setForeground(Color.BLACK);
        checkSolutionButton.setBackground(Color.WHITE);
        checkSolutionButton.addActionListener(e->{
            CheckSolutionWindow checkSolutionWindow = new CheckSolutionWindow(width,height);
            checkSolutionWindow.setVisible(true);
            dispose();
        });

        backgroundImage.setBounds(0,0,600,600);
        quitButton.setBounds(460,510,120,40);
        playSudokuButton.setBounds(170,170,250,40);
        solveSolutionButton.setBounds(170,230,250,40);
        checkSolutionButton.setBounds(170,290,250,40);
        nameLabel.setBounds(20,40,570,70);

        jPanel.add(quitButton);
        jPanel.add(nameLabel);
        jPanel.add(playSudokuButton);
        jPanel.add(solveSolutionButton);
        jPanel.add(checkSolutionButton);
        jPanel.add(backgroundImage);
        repaint();
    }
}
