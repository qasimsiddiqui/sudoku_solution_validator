public class Validate{

    private static final int nThreads = 27;
    private static int[][] sudoku;
    private static boolean[] valid;

    public static class checkRow implements Runnable{
        int row, column;

        public checkRow(int row, int column) {
            this.row = row;
            this.column = column;
        }

        @Override
        public void run() {
            if(column != 0 || row > 8){
                return;
            }

            boolean[] validRow = new boolean[9];

            for(int i=0; i<9; i++){
                int num = sudoku[row][i];
                if (num<1 || num>9 || validRow[num-1]){
                    return;
                }
                else if(!validRow[num-1]){
                    validRow[num-1] = true;
                }
            }
            valid[row + 9] = true;
        }
    }

    // runnable object that checks for column validity
    public static class checkColumn implements Runnable {

        int row, column;

        checkColumn(int row, int column) {
            this.row = row;
            this.column = column;
        }

        @Override
        public void run() {
            if (row != 0 || column > 8) {
                return;
            }
            boolean[] validColumn = new boolean[9];
            for (int i = 0; i < 9; i++) {
                int num = sudoku[i][column];
                if (num < 1 || num > 9 || validColumn[num - 1]) {
                    return;
                } else if (!validColumn[num - 1]) {
                    validColumn[num - 1] = true;
                }
            }
            // execute if col subsection is valid
            valid[column + 18] = true;
        }
    }

    // runnable object that checks for 3x3 grid validity
    public static class check3x3 implements Runnable {

        int row, column;

        check3x3(int row, int column) {
            this.row = row;
            this.column = column;
        }

        @Override
        public void run() {
            // verify parameters
            if (column % 3 != 0 || column > 6 || row > 6 || row % 3 != 0) {
                return;
            }
            boolean[] valid3x3 = new boolean[9];

            for (int i = row; i < row + 3; i++) {
                for (int j = column; j < column + 3; j++) {

                    int num = sudoku[i][j];
                    if (num < 1 || num > 9 || valid3x3[num - 1]) {
                        return;
                    } else {
                        valid3x3[num - 1] = true;
                    }
                }
            }
            // execute if 3x3 is valid
            valid[column / 3 + row] = true;
        }
    }

    public void printSudoku(){
        System.out.println();

        // convert validity from boolean values to string values
        String[] validStr = new String[valid.length];
        for (int i = 0; i < valid.length; i++) {
            if (valid[i]) {
                validStr[i] = "valid";
            } else {
                validStr[i] = "invalid";
            }
        }

        String[] subgrids = {"123", "456", "789"};

        int index2 = 0;

        // prints thread information for each 3x3 grid
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                System.out.println("Thread " + (index2 + 1) + ", Subgrid R"+subgrids[i]+"xC" + subgrids[j] + ", " + validStr[index2]);
                index2++;
            }
        }

        // prints thread information for each row
        for (int i = 0; i < 9; i++) {
            System.out.println("Thread " + (index2 + 1) + ", Row " + (i + 1) + ", " + validStr[index2]);
            index2++;
        }

        // prints thread information for each column
        for (int i = 0; i < 9; i++) {
            System.out.println("Thread " + (index2 + 1) + ", Column " + (i + 1) + ", " + validStr[index2]);
            index2++;
        }

        System.out.println();

        isSudokuValid();
    }

    // sudoku solution is invalid if there are any 0s in the valid array
    public boolean isSudokuValid(){
        for (boolean validity : valid) {
            if (!validity) {
                System.out.println("Solution is invalid!");
                return false;
            }
        }
        System.out.println("Solution is valid!");
        return true;
    }

    public Validate(int[][] sudokuMatrix) {
        sudoku = sudokuMatrix;
        valid = new boolean[nThreads];

        Thread[] threads = new Thread[nThreads];
        int index = 0;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && i % 3 == 0) {
                    threads[index++] = new Thread(new check3x3(i, j));
                }
                if (j == 0) {
                    threads[index++] = new Thread(new checkRow(i, j));
                }
                if (i == 0) {
                    threads[index++] = new Thread(new checkColumn(i, j));
                }
            }
        }

        // start threads
        for (Thread thread : threads) {
            thread.start();
        }

        // wait for threads to finish
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    boolean[] getValid(){
        return valid;
    }
}

