import java.util.Scanner;

public final class ImmutableMatrix extends Matrix {

    final private int rows;
    final private int cols;
    final private double[][] matrix;

    public ImmutableMatrix() {
        super();
        this.rows = 0;
        this.cols = 0;
        this.matrix = new double[0][0];
    }

    public ImmutableMatrix(int rows, int cols) {
        super(rows, cols);
        this.rows = rows;
        this.cols = cols;
        this.matrix = new double[rows][cols];
    }


    public ImmutableMatrix(double[][] matrix1) {
        super(matrix1);
        this.rows = matrix1.length;
        this.cols = matrix1[0].length;
        this.matrix = new double[rows][cols];
        for(int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.matrix[i][j] = matrix1[i][j];
            }
        }
    }


    public ImmutableMatrix(Matrix initialMatrix){
        super(initialMatrix);
        this.rows = initialMatrix.rowsGet();
        this.cols = initialMatrix.colsGet();
        this.matrix = new double[rows][cols];
        for(int i = 0; i < this.rows; i++) {
            for(int j = 0; j < this.cols; j++) {
                this.matrix[i][j] = initialMatrix.matrixGet()[i][j];
            }
        }
    }


    @Override
    public double[][] matrixGetThis(){
        double[][] someMatrix = new double[rows][cols];
        for(int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                someMatrix[i][j] = this.matrix[i][j];
            }
        }
        return someMatrix;
    }


    @Override
    public ImmutableMatrix matrixFilling() {
        Scanner keyboard = new Scanner(System.in);
        double[] rowElements;
        double[][] newMatrix = new double[this.rows][this.cols];


        if(this.rows == 0 || this.cols == 0) {
            System.out.println("Пуста матриця не може бути заповненою!");
            System.exit(1);
        }

        System.out.print("Введіть матрицю: ");
        for(int i = 0; i < this.rows; ++i){
            String userInput = keyboard.nextLine();
            rowElements = processingRow(userInput);

            int j = 0;
            for(double element: rowElements) {
                newMatrix[i][j] = element;
                ++j;
            }
        }

        return new ImmutableMatrix(newMatrix);
    }


    private double[] processingRow(String userInput) {
        String[] unprocessingMasive = userInput.split(" ");
        double[] processingMasive = new double[this.cols];

        if(unprocessingMasive.length != this.cols) {
            System.out.println("Введений рядок містить невірну кількість елементів!");
            System.exit(1);
        }

        for(int i = 0; i < this.cols; i++) {
            try {
                processingMasive[i] = Double.parseDouble(unprocessingMasive[i]);
            } catch(Exception e) {
                System.out.println("Введений рядок містить не тільки дійсні числа");
                System.exit(1);
            }
        }
        return processingMasive;
    }


    @Override
    public ImmutableMatrix changeElement(int row, int col, double newElement) {
        double[][] changedMatrix = this.matrix.clone();

        changedMatrix[row - 1][col - 1] = newElement;
        return new ImmutableMatrix(changedMatrix);
    }


    @Override
    public ImmutableMatrix changeRow(int row, double[] newRow) {
        double[][] changedMatrix = this.matrix.clone();
        int j = 0;

        for(double newElement: newRow) {
            changedMatrix[row][j] = newElement;
            j++;
        }
        return new ImmutableMatrix(changedMatrix);
    }


    @Override
    public ImmutableMatrix changeCol(int col, double[] newCol) {
        double[][] changedMatrix = this.matrix.clone();
        int i = 0;

        for(double newElement: newCol) {
            changedMatrix[i][col] = newElement;
            i++;
        }
        return new ImmutableMatrix(changedMatrix);
    }

}