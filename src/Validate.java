import java.util.concurrent.Executors;

public class Validate{

    private static int[][] sudoku;
    private static boolean valid;
    private static boolean[] validRow;
    private static boolean[] validColumn;
    private static boolean[] valid3x3Grid;

    /**
     * This class validates the sudoku passed by considering the elements range and uniqueness
     * of every row, column and sub-squares
     * @param sudokuMatrix the sudoku matrix that is to be validated
     */
    public Validate(int[][] sudokuMatrix) {
        sudoku = sudokuMatrix;

        int rowCount = sudokuMatrix.length;

        //check for empty sudoku or an unequal rows and columns count
        if(sudoku == null || rowCount != sudoku[0].length){
            valid = false;
        }
        else {
            //check that the elements of sudoku are between 0-9
            if(!validateSudokuElements(rowCount)){
                valid = false;
            }

            //check that no elements are repeated in each row, column and sub-square
            if(!validateSudokuRules(rowCount)){
                valid = false;
            }
        }
    }

    /**
     * This method returns true if the sudoku matrix is valid
     * @return boolean
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * This method checks to make sure that all elements of sudoku
     * are in range of 0-9
     * @param count
     */
    private boolean validateSudokuElements (int count){
        for(int i=0; i<count; i++){
            for(int j=0; j<count; j++){
                if(sudoku[i][j] < 1 || sudoku[i][j] > 9){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean validateSudokuRules(int count){

        Thread[] rowThreads = new Thread[count];
        Thread[] columnThreads = new Thread[count];
        Thread[] subSquareThreads = new Thread[count];

        //create a thread for every row to check validity
        for (int i=0; i<count; i++){
            int finalI = i;
            rowThreads[i] = new Thread(
             //       new RowThread(sudoku, i)
                    new Runnable() {
                        @Override
                        public void run() {
                            try {
                                int rowCount = sudoku.length;
                                int sum = 0;

                                for (int j = 0; j < rowCount; j++) {
                                    sum += sudoku[finalI][j];
                                }

                                if (sum == 45) {
                                    System.out.println(" Runnable = Valid");
                                    validRow[finalI] = true;
                                }
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    });
            System.out.println("Thread name = " +rowThreads[i].getName() + "  ID = " + rowThreads[i].getId());
        }

//        for (int i=0; i<count; i++){
//            rowThreads[i] = new Thread(new RowThread(i));
//        }
//
//        for (int i=0; i<count; i++){
//            rowThreads[i] = new Thread(new RowThread(i));
//        }

        for (int i=0; i<count; i++){
            rowThreads[i].start();
        }

        for(int i=0; i<count; i++){
            try{
                rowThreads[i].join();
                System.out.println("Row " +i + " :" + (valid ? " True" :" False"));
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        for(int i=0; i<count; i++){
            if(validRow[i] == false){
                System.out.println("validRow["+i+"]=" + validRow[i]);
                return false;
            }
            else {
                return true;
            }
        }
        return false;
    }


    class RowThread implements Runnable{

        int[][] sudokuMatrix;
        int row;

        RowThread(int[][] s,int r){
            sudokuMatrix = s;
            row = r;
            validRow[row] = false;
        }

        @Override
        public void run() {
            int rowCount = sudokuMatrix.length;
            int sum = 0;

            for(int j=0; j<rowCount; j++){
                sum += sudokuMatrix[row][j];
            }

            if(sum == 45){
                validRow[row] = true;
            }
        }
    }
}

