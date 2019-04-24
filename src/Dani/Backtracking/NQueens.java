package Dani.Backtracking;

public class NQueens {

    private final static int N = 8;


    public static void main(String[] Args) {
        int[][] tablero = new int[N][N];

        if (resolver(tablero, 0)) {
            System.out.println("Hola");
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print(" " + tablero[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    private static boolean resolver(int[][] tablero, int col) {
        if (esSolucion(col)) {
            System.out.println("Encontrao");
            return true;
        } else {
            for (int fila = 0; fila < N; fila++) {
                if (factible(tablero, fila, col)) {
                    tablero[fila][col] = 1;
                    System.out.println("Meto reina en: [" + fila + ", "  + col + "]");
                    if (resolver(tablero, col+1)) {
                        return true;
                    }
                    tablero[fila][col] = 0;
                }
            }
        }
        return false;
    }

    private static boolean factible(int[][] tablero, int fila, int columna) {
        for (int i = 0; i < columna; i++) {
            if (tablero[fila][i] == 1) return false;
        }
        for (int i = fila, j = columna; i >= 0 && j >= 0; i--, j--) {
            if (tablero[i][j] == 1) return false;
        }
        for (int i = fila, j = columna; i < N && j >= 0; i++, j--) {
            if (tablero[i][j] == 1) return false;
        }
        return true;
    }


    private static boolean esSolucion(int col) {
        return col >= N;
    }
}
