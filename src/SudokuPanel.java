import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SudokuPanel extends JPanel {

    private static final long serialVersionUID = -6115150501496366604L;
    JTextField[][] cell;

    public SudokuPanel(int[][] sudoku) {
        setLayout(null);
        setBackground(Color.gray);
        setSize(500, 400);
        cell = new JTextField[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cell[i][j] = new JTextField();
                cell[i][j].setHorizontalAlignment(JTextField.CENTER);
                cell[i][j].setFont(new Font("Arial", Font.BOLD, 25));
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
                } else {
                    cell[i][j].setText("");
                }

                cell[i][j].setBounds((i * 42) + 5, (j * 42) + 5, 40, 40);
                add(cell[i][j]);
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
                        } else if (keyTyped == '0') {
                            cell[finalI][finalJ].setText("");
                            keyEvent.consume();
                        } else {
                            keyEvent.consume();
                        }
                        highlightSameNumbers(cell[finalI][finalJ]);
                    }

                    @Override
                    public void keyPressed(KeyEvent keyEvent) {

                    }

                    @Override
                    public void keyReleased(KeyEvent keyEvent) {

                    }
                });

                cell[i][j].addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        for (int z = 0; z < 9; z++) {
                            cell[finalI][z].setBackground(new Color(0xB1CFE5));
                            cell[z][finalJ].setBackground(new Color(0xB1CFE5));
                        }
                        highlightSameNumbers(cell[finalI][finalJ]);
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        cell[finalI][finalJ].setBorder(null);
                        for (int x = 0; x < 9; x++) {
                            for (int y = 0; y < 9; y++) {
                                if (x > 2 && x < 6) {
                                    if (y > 2 && y < 6) {
                                        cell[x][y].setBackground(new Color(229, 224, 147));
                                    } else {
                                        cell[x][y].setBackground(Color.white);
                                    }
                                } else {
                                    if (y > 2 && y < 6) {
                                        cell[x][y].setBackground(Color.white);
                                    } else {
                                        cell[x][y].setBackground(new Color(229, 224, 147));
                                    }
                                }
                            }
                        }
                        for (int z = 0; z < 9; z++) {
                            for (int y = 0; y < 9; y++) {
                                cell[z][y].setForeground(Color.BLACK);
                            }
                        }
                    }
                });
            }
        }
    }

    private void highlightSameNumbers(JTextField jTextField) {
        for (int z = 0; z < 9; z++) {
            for (int y = 0; y < 9; y++) {
                cell[z][y].setForeground(Color.BLACK);
                cell[z][y].setBorder(null);
            }
        }
        jTextField.setBorder(BorderFactory.createBevelBorder(0));
        String focusedText = jTextField.getText();
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (cell[x][y].getText().equals(focusedText)) {
                    cell[x][y].setForeground(new Color(0x177d9c));
                    cell[x][y].setBorder(BorderFactory.createBevelBorder(0));
                }
            }
        }
    }

    int[][] getSudokuMatrix() {
        int[][] sudoku = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (cell[i][j].getText().equals("")) {
                    sudoku[i][j] = 0;
                } else {
                    sudoku[i][j] = Integer.parseInt(cell[i][j].getText());
                }
            }
            System.out.println();
        }
        return sudoku;
    }

    void markAsInvalid(boolean[] valid) {
        int x;
        int y;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                x = i - i % 3;
                y = j - j % 3;
                if (!valid[i + 9] && !valid[j + 18] && !valid[y / 3 + x]) {
                    cell[i][j].setBorder(BorderFactory.createBevelBorder(0));
                    cell[i][j].setBackground(new Color(0xE57B7E));
                }
            }
        }
    }

    public void setValues(int[][] sudokuMatrix) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cell[i][j].setText(String.valueOf(sudokuMatrix[i][j]));
            }
        }
    }

    public void setHintonFocusedCell(int[][] matrix) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (cell[i][j].isFocusOwner()) {
                    cell[i][j].setText(String.valueOf(matrix[i][j]));
                }
            }
        }
    }
}
