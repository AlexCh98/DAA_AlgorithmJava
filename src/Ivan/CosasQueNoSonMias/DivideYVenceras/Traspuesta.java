package Ivan.CosasQueNoSonMias.DivideYVenceras;

public class Traspuesta {

    private static int nextPowerOfTwo(int n) {
        int log2 = (int) Math.ceil(Math.log(n) / Math.log(2));
        return (int) Math.pow(2, log2);
    }

    public int[][] trasponer(int[][] matrix) {

        int n = matrix.length;
        int m = nextPowerOfTwo(n);
        int[][] T;
        if (m == n) {
            T = trasponerRec(matrix);
        } else {
            // Make the matrices bigger so that you can apply the DaC
            // algorithm recursively without having to deal with odd
            // matrix sizes
            int[][] matrixPrep = new int[m][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrixPrep[i][j] = matrix[i][j];
                }
            }

            int[][] TPrep = trasponerRec(matrixPrep);

            T = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    T[i][j] = TPrep[i][j];
                }
            }
        }
        return T;

    }

    private int[][] trasponerRec(int[][] matrix) {

        int n = matrix.length;

        if (n <= 1) {
            return matrix;
        } else {
            int newSize = n / 2;

            int[][] a11 = new int[newSize][newSize];
            int[][] a12 = new int[newSize][newSize];
            int[][] a21 = new int[newSize][newSize];
            int[][] a22 = new int[newSize][newSize];


            // dividing the matrices in 4 sub-matrices:
            squareDivision(matrix, a11, a12, a21, a22);

            // Divide and Conquer multiplication. Calculating c21, c21, c11,
            // c22:
            int[][] t11 = trasponer(a11);
            int[][] t12 = trasponer(a12);

            int[][] t21 = trasponer(a21);
            int[][] t22 = trasponer(a22);

            // Grouping the results obtained in a single matrix:
            int[][] T = squareFusion(t11, t21, t12, t22); //La clave esta en cambiar el orden de t12 y t21

            return T;
        }
    }

    private void squareDivision(int[][] a, int[][] a11, int[][] a12, int[][] a21, int[][] a22) {
        int newSize = a.length / 2;
        // dividing the matrices in 4 sub-matrices:
        for (int i = 0; i < newSize; i++) {
            for (int j = 0; j < newSize; j++) {
                a11[i][j] = a[i][j]; // top left
                a12[i][j] = a[i][j + newSize];// top right
                a21[i][j] = a[i + newSize][j];// bottom left
                a22[i][j] = a[i + newSize][j + newSize];// bottom right
            }
        }
    }

    private int[][] squareFusion(int[][] t11, int[][] t12, int[][] t21, int[][] t22) {
        int n = t11.length;

        // Grouping the results obtained in a single matrix:
        int[][] matrix = new int[n*2][n*2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = t11[i][j];
                matrix[i][j + n] = t12[i][j];
                matrix[i + n][j] = t21[i][j];
                matrix[i + n][j + n] = t22[i][j];
            }
        }
        return matrix;
    }
}
