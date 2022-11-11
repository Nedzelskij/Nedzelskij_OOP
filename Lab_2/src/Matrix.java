import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.lang.System;


public class Matrix {

    private int rows;
    private int cols;
    private double[][] matrix;


    public Matrix() {
        this.rows = 0;
        this.cols = 0;
        this.matrix = new double[0][0];
    }

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.matrix = new double[rows][cols];
    }


    public Matrix(double[][] matrix1) {
        this.rows = matrix1.length;
        this.cols = matrix1[0].length;
        this.matrix = new double[rows][cols];
        for(int i = 0; i < this.rows; i++) {
            for(int j = 0; j < this.cols; j++) {
                this.matrix[i][j] = matrix1[i][j];
            }
        }
    }


    public Matrix(Matrix initialMatrix){
        this.rows = initialMatrix.rows;
        this.cols =  initialMatrix.cols;
        this.matrix = new double[rows][cols];
        for(int i = 0; i < this.rows; i++) {
            for(int j = 0; j < this.cols; j++) {
                this.matrix[i][j] = initialMatrix.matrix[i][j];
            }
        }
    }


    public int rowsGet(){
        return rows;
    }


    public int colsGet(){
        return cols;
    }


    public double[][] matrixGet(){
        double[][] matrixClone = new double[rows][cols];
        for(int i = 0; i < this.rows; i++) {
            for(int j = 0; j < this.cols; j++) {
                matrixClone[i][j] = this.matrix[i][j];
            }
        }
        return matrixClone;
    }


    public double[][] matrixGetThis(){
        return this.matrix;
    }

    public Matrix matrixFilling() {
        Scanner keyboard = new Scanner(System.in);
        double[] rowElements;

        if(this.rows == 0 || this.cols == 0) {
            System.out.println("Пуста матриця не може бути заповненою!");
            return new Matrix();
        }

        System.out.println("Введіть матрицю: ");
        for(int i = 0; i < this.rows; ++i){
            String userInput = keyboard.nextLine();
            rowElements = processingRow(userInput);

            int j = 0;
            for(double element: rowElements) {
                this.matrix[i][j] = element;
                ++j;
            }
        }
        return new Matrix(this.matrix);
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


    public double getElement(int row, int col) {
        return this.matrix[row - 1][col - 1];
    }


    public double[] getRow(int row) {
        return this.matrix[row - 1];
    }


    public double[][] getCol(int col) {
        double[][] colOfMatrix = new double[rows][1];

        for(int i = 0; i < rows; i++) {
            colOfMatrix[i][0] = this.matrix[i][col - 1];
        }

        return colOfMatrix;
    }


    public int[] getSize() {
        return new int[] {this.rows, this.cols};
    }


    public static Matrix createSingleMatrix(int size, double[][] matrix) {

        if(determinantOfMatrix(matrix, size) == 0){
            System.out.println("\nВизначник матриці дорівнює нулю, привести до одиничного вигляду неможливо(методом Жордана-Гауса)\n");
            return new ImmutableMatrix(size, size);
        }

        for(int quantity = 0; quantity < size; quantity++){

            for(int j = 0; j < size; j++){
                matrix[quantity][j] = matrix[quantity][j]/matrix[quantity][quantity];
            }

            for(int i = 0; i < size; i++) {
                for(int j = 0; j < size; j++) {
                    if(i != quantity || j != quantity) {
                        matrix[i][j] = matrix[i][j] - (matrix[quantity][j] * matrix[i][quantity]) ;
                    }

                }
            }

            for(int i = 0; i < size; i++){
                if(i != quantity) {
                    matrix[i][quantity] = 0;
                }
            }
        }
        return new ImmutableMatrix(matrix);
    }


    private static void getCofactor(double[][] matrix, double[][] temporaryMatrix, int selectedCol, int n) {
        int i = 0, j = 0, selectedRow = 0;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {

                if (row != selectedRow && col != selectedCol) {
                    temporaryMatrix[i][j++] = matrix[row][col];
                    if (j == n - 1) {
                        j = 0;
                        i++;
                    }
                }

            }
        }
    }


    private static double determinantOfMatrix(double[][] matrix, int n) {
        double determinant = 0;
        int sign = 1;

        if (n == 1) return matrix[0][0];

        double[][] temporaryMatrix = new double[n][n];

        for (int selectedCol = 0; selectedCol < n; selectedCol++) {
            getCofactor(matrix, temporaryMatrix, selectedCol, n);
            determinant += sign * matrix[0][selectedCol] * determinantOfMatrix(temporaryMatrix, n - 1);

            sign = -sign;
        }

        return determinant;
    }


    public double[][] transposeMatrix() {
        double[][] transposedMatrix = new double[this.cols][this.rows];

        for(int i = 0; i < this.rows; i++) {
            for(int j = 0; j < this.cols; j++) {
                transposedMatrix[j][i] = this.matrix[i][j];
            }
        }
        return transposedMatrix;
    }


    @Override
    public boolean equals(Object someObject) {
        if (this == someObject) return true;
        if (someObject == null || getClass() != someObject.getClass()) return false;

        Matrix someMatrix = (Matrix) someObject;

        return this.rows == someMatrix.rows && this.cols == someMatrix.cols && arrayEqual(this.matrix, someMatrix.matrix);
    }


    @Override
    public int hashCode() {
        return 31 * Arrays.deepHashCode(matrix);
    }


    private boolean arrayEqual(final double[][] arr1, final double[][] arr2) {

        if (arr1 == null) return (arr2 == null);

        if (arr2 == null) return false;

        if (arr1.length != arr2.length) return false;

        for (int i = 0; i < arr1.length; i++) {
            if (!Arrays.equals(arr1[i], arr2[i])) return false;
        }
        return true;
    }


    public void printMatrix() {
        if(this.rows == 0 || this.cols == 0){
            System.out.println(Arrays.toString(this.matrix));
            return;
        }

        for(int i = 0; i < this.rows; i++) {
            for(int j = 0; j < this.cols; j++) {
                System.out.print(this.matrix[i][j]);
                System.out.print(" ");
            }
            System.out.println("");
        }
    }


    public Matrix cloneMatrix(){
        if(this.rows == 0 || this.cols == 0){
            return new Matrix();
        }else {
            return new Matrix(this.matrix);
        }
    }


    public Matrix changeElement(int row, int col, double newElement) {
        this.matrix[row - 1][col - 1] = newElement;
        return new Matrix(this.matrix);
    }


    public Matrix changeRow(int row, double[] newRow) {
        int j = 0;
        for(double newElement: newRow) {
            this.matrix[row - 1][j] = newElement;
            j++;
        }
        return new Matrix(this.matrix);
    }


    public Matrix changeCol(int col, double[] newCol) {
        int i = 0;
        for(double newElement: newCol) {
            this.matrix[i][col - 1] = newElement;
            i++;
        }
        return new Matrix(this.matrix);
    }


}