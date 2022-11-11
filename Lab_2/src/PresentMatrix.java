import java.util.Arrays;
import java.util.Scanner;

public class PresentMatrix {
    public void menu() {
        Scanner keyboard = new Scanner(System.in);
        int choice;

        do {
            // оutput of possible options for user actions
            System.out.println("""

                    1. Створити пусту матрицю
                    2. Створити матрицю заданих розмірів
                    3. Створити ініціалізовану матрицю
                    4. Закінчити роботу
                    """);

            // аssigning the value of parameter a, with a preliminary check for entering the number
            System.out.print("Ваш вибір: ");
            choice = keyboard.nextInt();

            switch(choice) {
                case 1:
                    createEmptyMatrix();
                    break;
                case 2:
                    createMatrixWithSize();
                    break;
                case 3:
                    crateFilledMatrix();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("\nВведене число не відповідає за якусь дію!\n");
            }
        } while(choice != 4);

    }


    public void createEmptyMatrix() {
        Matrix emptyMatrix = new Matrix();
        Scanner keyboard = new Scanner(System.in);
        int choice;

        do {
            // оutput of possible options for user actions
            System.out.println("""

                    1. Надрукувати матрицю
                    2. Отримати hashCode матриці
                    3. Порівняти матрицю з введеною матрицею
                    4. Порівняти матрицю з своїм клоном
                    5. Повернути розмірність матриці
                    6. Повернутися назад
                    """);

            // аssigning the value of parameter a, with a preliminary check for entering the number
            System.out.print("Ваш вибір: ");
            choice = keyboard.nextInt();

            switch(choice) {
                case 1:
                    emptyMatrix.printMatrix();
                    break;
                case 2:
                    System.out.println("hashCode = " + emptyMatrix.hashCode());
                    break;
                case 3:
                    equalsMatrixs(emptyMatrix);
                    break;
                case 4:
                    equalsSameMatrix(emptyMatrix);
                    break;
                case 5:
                    System.out.println("Розмір = " + Arrays.toString(emptyMatrix.getSize()));
                    break;
                case 6:
                    break;
                default:
                    System.out.println("\nВведене число не відповідає за якусь дію!\n");
            }
        } while(choice != 6);

    }


    public void crateFilledMatrix(){
        Scanner keyboard = new Scanner(System.in);
        double[] rowElements;

        System.out.print("Введіть кількість рядків: ");
        int rows = keyboard.nextInt();

        System.out.print("Введіть кількість стовпців: ");
        int cols = keyboard.nextInt();

        double[][] fillMatrix = new double[rows][cols];

        Scanner keyboard1 = new Scanner(System.in);
        System.out.println("Введіть матрицю: ");
        for(int i = 0; i < rows; ++i){
            String userInput = keyboard1.nextLine();
            rowElements = processingRow(userInput, cols);

            int j = 0;
            for(double element: rowElements) {
                fillMatrix[i][j] = element;
                ++j;
            }
        }

        Matrix filledMatrix = new Matrix(fillMatrix);

        menuMatrix(filledMatrix, rows, cols);
    }


    public void createMatrixWithSize() {
        Scanner keyboard = new Scanner(System.in);

        System.out.print("Введіть кількість рядків: ");
        int rows = keyboard.nextInt();

        System.out.print("Введіть кількість стовпців: ");
        int cols = keyboard.nextInt();

        Matrix matrixWithSize = new Matrix(rows, cols);

        menuMatrix(matrixWithSize, rows, cols);
    }


    public void menuMatrix(Matrix matrix, int rows, int cols){
        Scanner keyboard = new Scanner(System.in);
        int choice;
        Matrix copyMatrix2 = new Matrix();
        Matrix imCopyMatrix2 = new ImmutableMatrix();

        do {
            // оutput of possible options for user actions
            System.out.println("""

                    1.  Надрукувати матрицю
                    2.  Отримати hashCode матриці
                    3.  Порівняти матрицю з введеною матрицею
                    4.  Перетворити на одиничну матрицю
                    5.  Повернути розмірність матриці
                    6.  Повернути елемент
                    7.  Повернути рядок
                    8.  Повернути стовпчик
                    9.  Змінити елемент
                    10. Змінити рядок
                    11. Змінити стовпчик
                    12. Заповнити матрицю
                    13. Повернути транспоновану матрицю
                    14. Копіювати матрицю
                    15. Порівняння з клоном
                    16. Надрукувати клон
                    17. Копіювати незмінну матрицю
                    18. Порівняти з незмінною матрицею
                    19. Надрукувати незмінний клон
                    20. Повернутися назад
                    """);

            // аssigning the value of parameter a, with a preliminary check for entering the number
            System.out.print("Ваш вибір: ");
            choice = keyboard.nextInt();

            switch(choice) {
                case 1:
                    matrix.printMatrix();
                    break;
                case 2:
                    System.out.println("hashCode = " + matrix.hashCode());
                    break;
                case 3:
                    equalsMatrixs(matrix);
                    break;
                case 4:
                    if(rows == cols){
                        Matrix.createSingleMatrix(rows, matrix.matrixGetThis());
                    } else{
                        System.out.println("Матриця не є квадратною, приведення неможливе!");
                    }

                    break;
                case 5:
                    System.out.println("Розмір = " + Arrays.toString(matrix.getSize()));
                    break;
                case 6:
                    getElement(matrix);
                    break;
                case 7:
                    getRow(matrix);
                    break;
                case 8:
                    getCol(matrix);
                    break;
                case 9:
                    changeElement(matrix);
                    break;
                case 10:
                    changeRow(matrix, cols);
                    break;
                case 11:
                    changeCol(matrix, rows);
                    break;
                case 12:
                    matrix.matrixFilling();
                    break;
                case 13:
                    getTransposedMatrix(matrix);
                    break;
                case 14:
                    copyMatrix2 = new Matrix(matrix);
                    break;
                case 15:
                    copyEquals(matrix, copyMatrix2);
                    break;
                case 16:
                    copyMatrix2.printMatrix();
                    break;
                case 17:
                    imCopyMatrix2 = new ImmutableMatrix(matrix);
                    break;
                case 18:
                    copyEquals(matrix, imCopyMatrix2);
                    break;
                case 19:
                    imCopyMatrix2.printMatrix();
                    break;
                case 20:
                    break;
                default:
                    System.out.println("\nВведене число не відповідає за якусь дію!\n");
            }
        } while(choice != 20);
    }


    public void copyEquals(Matrix mainMatrix, Matrix copyMatrix2){

        System.out.println("Початкова матриця.");
        mainMatrix.printMatrix();

        System.out.println("Клон матриці.");
        copyMatrix2.printMatrix();

        if(mainMatrix.equals(copyMatrix2)){
            System.out.print("\nМатриці рівні.");
        } else{
            System.out.print("\nМатриці не рівні.");
        }
    }



    public void equalsMatrixs(Matrix mainMatrix){
        Scanner keyboard = new Scanner(System.in);

        System.out.print("\nВведіть кількість рядків: ");
        int rows = keyboard.nextInt();

        System.out.print("\nВведіть кількість стовпців: ");
        int cols = keyboard.nextInt();

        Matrix matrixForEquals = new Matrix(rows, cols);

        if(rows != 0 & cols != 0) matrixForEquals.matrixFilling();

        if(mainMatrix.equals(matrixForEquals)){
            System.out.print("\nМатриці рівні.");
        } else{
            System.out.print("\nМатриці не рівні.");
        }
    }


    public void equalsSameMatrix(Matrix mainMatrix){
        Matrix matrixClone = mainMatrix.cloneMatrix();

        System.out.println("Початкова матриця.");
        mainMatrix.printMatrix();

        System.out.println("Клон матриці.");
        matrixClone.printMatrix();

        if(mainMatrix.equals(matrixClone)){
            System.out.print("\nМатриці рівні.");
        } else{
            System.out.print("\nМатриці не рівні.");
        }
    }


    public void getElement(Matrix mainMatrix){
        Scanner keyboard = new Scanner(System.in);

        System.out.print("\nВведіть номер рядка: ");
        int row = keyboard.nextInt();

        System.out.print("\nВведіть номер стовпця: ");
        int col = keyboard.nextInt();

        System.out.print("\nШуканий елемент = " + mainMatrix.getElement(row, col));
    }


    public void getRow(Matrix mainMatrix){
        Scanner keyboard = new Scanner(System.in);

        System.out.print("\nВведіть номер рядка: ");
        int row = keyboard.nextInt();

        System.out.println("Шуканий рядок: ");
        for(double element: mainMatrix.getRow(row)) {
            System.out.print(element + " ");
        }
    }


    public void getCol(Matrix mainMatrix){
        Scanner keyboard = new Scanner(System.in);

        System.out.print("\nВведіть номер стовпця: ");
        int col = keyboard.nextInt();

        System.out.println("Шуканий стовпець: ");
        for(double[] element: mainMatrix.getCol(col)) {
            System.out.println(element[0]);
        }
    }


    public void changeElement(Matrix mainMatrix){
        Scanner keyboard = new Scanner(System.in);

        System.out.print("\nВведіть номер рядка: ");
        int row = keyboard.nextInt();

        System.out.print("\nВведіть номер стовпця: ");
        int col = keyboard.nextInt();

        System.out.print("\nВведіть новий елемент: ");
        Scanner keyboard1 = new Scanner(System.in);
        String strElement = keyboard1.nextLine();
        double newElement = Double.parseDouble(strElement);

        mainMatrix.changeElement(row, col, newElement);
    }



    public void changeRow(Matrix mainMatrix, int numberOfElement){
        Scanner keyboard = new Scanner(System.in);

        System.out.print("\nВведіть номер рядка: ");
        int row = keyboard.nextInt();

        System.out.print("Введіть заповнений рядок матриці: ");
        Scanner keyboard1 = new Scanner(System.in);
        String inputRows = keyboard1.nextLine();

        mainMatrix.changeRow(row, processingRow(inputRows, numberOfElement));
    }


    public void changeCol(Matrix mainMatrix, int numberOfElement){
        Scanner keyboard = new Scanner(System.in);

        System.out.print("\nВведіть номер стовпця: ");
        int col = keyboard.nextInt();

        System.out.print("Введіть заповнений стовпець матриці(по рядку): ");
        Scanner keyboard1 = new Scanner(System.in);
        String inputRows = keyboard1.nextLine();

        mainMatrix.changeCol(col, processingRow(inputRows, numberOfElement));
    }


    public double[] processingRow(String userInput, int numberOfElement) {
        String[] unprocessingMasive = userInput.split(" ");
        double[] processingMasive = new double[numberOfElement];

        if(unprocessingMasive.length != numberOfElement) {
            System.out.println("Введений рядок містить невірну кількість елементів!");
            System.exit(1);
        }

        for(int i = 0; i < numberOfElement; i++) {
            try {
                processingMasive[i] = Double.parseDouble(unprocessingMasive[i]);
            } catch(Exception e) {
                System.out.println("Введений рядок містить не тільки дійсні числа");
                System.exit(1);
            }
        }
        return processingMasive;
    }


    public void getTransposedMatrix(Matrix mainMatrix){
        double[][] transposedMatrix = mainMatrix.transposeMatrix();

        for(int i = 0; i < transposedMatrix.length; i++) {
            for(int j = 0; j < transposedMatrix[0].length; j++) {
                System.out.print(transposedMatrix[i][j] + " ");
            }
            System.out.println("");
        }
    }

}

