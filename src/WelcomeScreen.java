import javax.swing.*;
import java.awt.*;

public class WelcomeScreen extends JPanel {

    GridBagLayout GBLayout;
    JLabel welcomeLabel;

    public WelcomeScreen(){
        GBLayout = new GridBagLayout();
        setLayout(GBLayout);
        setBackground(Color.white);

        welcomeLabel = new JLabel("Sudoku Solution Validator");
        addComponent(welcomeLabel,0,0,1,1,new Insets(20,20,20,20),10,10,GridBagConstraints.BOTH,GridBagConstraints.NORTH);

        JButton validateSudoku = new JButton("Validate Sudoku");
        addComponent(validateSudoku,1,0,1,1,new Insets(20,20,20,20),2,2,GridBagConstraints.BOTH,GridBagConstraints.CENTER);

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
