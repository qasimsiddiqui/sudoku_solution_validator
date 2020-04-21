import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SudokuPanel extends JPanel {

    GridBagLayout GBLayout = new GridBagLayout();
    JTextField[][] cell;

     public SudokuPanel(int[][] sudoku){
        setLayout(GBLayout);
        setBackground(Color.gray);
        setSize(500,400);
        cell = new JTextField[9][9];

        for(int i=0; i<9; i++) {
            for (int j = 0; j < 9; j++) {
                cell[i][j] = new JTextField();
                cell[i][j].setHorizontalAlignment(JTextField.CENTER);
                cell[i][j].setFont(new Font("Arial", Font.BOLD, 20));
                cell[i][j].setColumns(2);
                if (i > 2 && i < 6) {
                    if (j > 2 && j < 6) {
                        cell[i][j].setBackground(new Color(229, 224, 147));
                    } else {
                        cell[i][j].setBackground(Color.white);
                    }
                } else {
                    if (j > 2 && j < 6) {
                        cell[i][j].setBackground(Color.white);
                    } else {
                        cell[i][j].setBackground(new Color(229, 224, 147));
                    }
                }

                if (sudoku[i][j] != 0) {
                    cell[i][j].setText(Integer.toString(sudoku[i][j]));
                }

                cell[i][j].setBounds((i*40)+10,(j*40)+10,40,40);
                add(cell[i][j]);
                //addComponent(cell[i][j], i, j, 1, 1, new Insets(2, 2, 2, 2), 1, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER);
                final int finalI = i;
                final int finalJ = j;
                cell[i][j].addKeyListener(new KeyListener() {


                    @Override
                    public void keyTyped(KeyEvent keyEvent) {

                        char keyTyped = keyEvent.getKeyChar();

                        String compare = Character.toString(keyTyped);
                        Pattern pattern = Pattern.compile("[1-9]");
                        Matcher matcher = pattern.matcher(compare);

                        if (matcher.find()) {
                            cell[finalI][finalJ].setText(Character.toString(keyTyped));
                            keyEvent.consume();
                        } else if(keyTyped == '0') {
                            cell[finalI][finalJ].setText("");
                            keyEvent.consume();
                        }
                        else{
                            keyEvent.consume();
                        }
                    }

                    @Override
                    public void keyPressed(KeyEvent keyEvent) {

                    }

                    @Override
                    public void keyReleased(KeyEvent keyEvent) {

                    }
                });
            }
        }
    }

    private void addComponent(Component component, int row, int column, int width, int height, Insets insets, double weightx, double weighty, int fill, int anchor){
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridy = row;     //row to be placed in
        constraints.gridx = column;     //column to be placed in
        constraints.gridwidth = width;
        constraints.gridheight = height;
        constraints.insets = insets;
        constraints.weightx = weightx;
        constraints.weighty = weighty;
        constraints.fill = fill;
        constraints.anchor = anchor;

        add(component, constraints);
    }
}
