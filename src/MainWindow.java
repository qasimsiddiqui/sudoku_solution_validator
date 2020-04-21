import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JPanel{

   // GridBagLayout GBLayout;
    JLabel nameLabel;
    JButton quitButton;

    public MainWindow () {

     //   GBLayout = new GridBagLayout();

        setLayout(null);
        setSize(600,500);
        setBackground(new Color(246, 255, 0, 0));
       // setLayout(GBLayout);

        nameLabel = new JLabel("Sudoku Solution Validator");
        quitButton = new JButton("Quit");


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
                removeAll();
                repaint();
                //System.exit(0);
            }
        });

        add(quitButton);
        add(nameLabel);

        quitButton.setBounds(400,400,100,30);
        nameLabel.setBounds(100,30,400,40);


        //addComponent(nameLabel, 0, 0, 1, 1, new Insets(2, 2, 2, 2), 1, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER);
        //addComponent(quitButton, 1, 0, 1, 1, new Insets(2, 2, 2, 2), 1, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER);
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

