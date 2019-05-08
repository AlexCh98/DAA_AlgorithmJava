package Backtracking;

public class NReinas {

    private static final int N = 4;

    public static void main(String[] args) {
        int[][] tablero = new int[N][N];
        rellenarTablero(tablero, 0);
    }

    private static void imprimirTablero(int[][] tablero) {
        for (int[] fila : tablero) {
            for (int valor : fila) {
                if (valor == 0) System.out.print(" * ");
                else System.out.print(" R ");
            }
            System.out.println();
        }
    }

    private static boolean rellenarTablero(int[][] tablero, int col) {
        if (col >= N) {
            imprimirTablero(tablero);
            System.out.println();
            return true;
        } else {
            boolean resultado = false;
            for (int fila = 0; fila < N; fila++) {
                if (esFactible(tablero, fila, col)) {
                    tablero[fila][col] = 1;
                    resultado = rellenarTablero(tablero, col + 1);
                    //if (rellenarTablero(tablero, col + 1)) return true;
                    tablero[fila][col] = 0;
                }
            }
            //return false;
            return resultado;
        }
    }

    private static boolean esFactible(int[][] tablero, int fila, int col) {
        for (int i = col; i >= 0; i--) {
            if (tablero[fila][i] == 1) return false;
        }
        for (int i = fila, j = col; i >= 0 && j >= 0; i--, j--) {
            if (tablero[i][j] == 1) return false;
        }
        for (int i = fila, j = col; i < N && j >= 0; i++, j--) {
            if (tablero[i][j] == 1) return false;
        }
        return true;
    }
}
