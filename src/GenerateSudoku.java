import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class GenerateSudoku {

    private int[][] sudoku = new int[9][9];
    public static int EASY = 28;
    public static int NORMAL = 44;
    public static int HARD = 58;

    public GenerateSudoku() {
        Integer[] arr = new Integer[9];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        Collections.shuffle(Arrays.asList(arr));
        for (int i = 0; i < 9; i++) {
            sudoku[0][i] = arr[i];
        }
        SolveSudoku solveSudoku = new SolveSudoku(sudoku);
        if (solveSudoku.isSolvable()) {
            sudoku = solveSudoku.getSolvedSudoku();
        } else {
            System.out.println("INSOLVABLE SUDOKU");
        }
    }

    public int[][] createGame(int numberOfEmptyCells) {
        eraseCells(numberOfEmptyCells);
        return sudoku;
    }

    private void eraseCells(int numberOfEmptyCells) {
        Random random = new Random();
        for (int i = 0; i < numberOfEmptyCells; i++) {
            int randomRow = random.nextInt(9);
            int randomColumn = random.nextInt(9);

            if (sudoku[randomRow][randomColumn] != 0) {
                sudoku[randomRow][randomColumn] = 0;
            } else {
                i--;
            }
        }
    }
}