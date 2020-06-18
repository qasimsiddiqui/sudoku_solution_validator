public class SolveSudoku {

    private int[][] sudoku;

    SolveSudoku(int[][] matrix) {
        sudoku = matrix;
    }

    public boolean isSolvable(){
        return Solve();
    }

    public boolean Solve() {
        // If all cells are taken, solution is found
        if(!emptyCellExists())
            return true;

        // go through the board and get empty cells
        int row = 0, col = 0;
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku[i].length; j++) {
                if (sudoku[i][j] == 0) {
                    // break when empty cell is found
                    row = i;
                    col = j;
                    break;
                }
            }
        }

        // if empty cell is found
        for(int n = 1; n <= 9; n++) {
            // if cell meets the requirements
            if (possibleCell(row, col, n)) {
                // try a new number
                sudoku[row][col] = n;

                if(Solve())
                    return true;

                // if above condition returns false
                sudoku[row][col] = 0;
            }
        }

        // recursive
        return false;
    }

    public int[][] getSolvedSudoku(){
        return sudoku;
    }

    // Print board
    public void Print() {
        System.out.println("\n___________________");
        for(int i = 0; i < sudoku.length; i++) {
            for(int j = 0; j < sudoku[i].length; j++) {
                System.out.print(sudoku[i][j] + " ");

                if((j+1)%3 == 0)
                    System.out.print(" ");
            }

            if((i+1)%3 == 0)
                System.out.print("\n___________________");

            System.out.println();
        }
    }
    // private functions

    //check if cell is in row
    private boolean isInRow(int row, int number) {
        for (int i = 0; i < sudoku[row].length; i++) {
            if (sudoku[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    // check if cell is in column
    private boolean isInColumn(int col, int number) {
        for (int i = 0; i < sudoku.length; i++) {
            if (sudoku[i][col] == number) {
                return true;
            }
        }
        return false;
    }

    // check if cell if in a 3x3 box
    private boolean isInBox(int row, int col, int number) {
        // find which 3x3 box the (row,col) is at

        // x and y represent the starting indexes
        // 0-2 3-5 6-8 indexes
        int x = row - row%3;
        int y = col - col%3;

        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                if(sudoku[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    // Check if cell meets the requirements
    private boolean possibleCell(int row, int col, int number) {
        return !isInRow(row, number) && !isInColumn(col, number) && !isInBox(row, col, number);
    }

    // Check if there are any empty cells
    private boolean emptyCellExists() {
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku[i].length; j++) {
                // if empty cell found
                if(sudoku[i][j] == 0)
                    return true;
            }
        }
        return false;
    }
}
