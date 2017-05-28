/**
 * Created by Andre on 27/05/2017.
 */
public class Matrix {

    int rows;
    int columns;
    double[][] matrix;

    Matrix(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        matrix = new double[rows][columns];
    }

    public static double[][] transposeMatrix(double[][] m) {
        double[][] temp = new double[m[0].length][m.length];
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[0].length; j++)
                temp[j][i] = m[i][j];
        return temp;

    }

    public static double[][] multiply(double[][] A, double[][] B) {

        int aRows = A.length;
        int aColumns = A[0].length;
        int bRows = B.length;
        int bColumns = B[0].length;

        double[][] C = initResultMatrix(aRows,aColumns,bRows,bColumns);

        for (int i = 0; i < aRows; i++) { // aRow
            for (int j = 0; j < bColumns; j++) { // bColumn
                for (int k = 0; k < aColumns; k++) { // aColumn
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return C;
    }

    public static double[][] multiply(double[][] A, double[][] B, int activationFunction) {

        int aRows = A.length;
        int aColumns = A[0].length;
        int bRows = B.length;
        int bColumns = B[0].length;

        double[][] C = initResultMatrix(aRows,aColumns,bRows,bColumns);

        for (int i = 0; i < aRows; i++) { // aRow
            for (int j = 0; j < bColumns; j++) { // bColumn
                for (int k = 0; k < aColumns; k++) { // aColumn
                    C[i][j] += A[i][k] * B[k][j];
                }
                switch (activationFunction){
                    case 0 : C[i][j] = ActivationFunction.sigmoid(C[i][j]);
                }
            }
        }

        return C;
    }

    private static double[][] initResultMatrix(int aRows, int aColumns, int bRows, int bColumns){

        if (aColumns != bRows) {
            throw new IllegalArgumentException("A:Rows: " + aColumns + " did not match B:Columns " + bRows + ".");
        }

        double[][] C  = new double[aRows][bColumns];
        for (int i = 0; i < aRows; i++) {
            C[i][bColumns] = 1.0;
        }

        return C;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public double[][] get() {
        return matrix;
    }

    public void set(double[][] matrix) {
        this.matrix = matrix;
    }

    public void setValue(int rowIndex, int columnIndex, double value){
        this.matrix[rowIndex][columnIndex] = value;
    }

    public double getValue(int rowIndex, int columnIndex){
        return this.matrix[rowIndex][columnIndex];
    }
}
