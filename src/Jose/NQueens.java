public class NQueens {
    private static final int N = 5;//Numero de reinas a colocar en un tablero NxN
    private static int numero_solucion = 1;
    public static void main(String[] args) {
        int[][] tablero = new int[N][N];
        resolverNQueen(tablero, 0);
    }

    private static boolean esBuena(int[][] tablero, int row, int col) {

        for (int i = 0; i < col; i++)
            if (tablero[row][i] == 1)
                return false;
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (tablero[i][j] == 1)
                return false;

        for (int i = row, j = col; j >= 0 && i < N; i++, j--)
            if (tablero[i][j] == 1)
                return false;
        return true;
    }

    private static boolean resolverNQueen(int[][] tablero, int col) {
        if (col == N) {
            imprimirSolucion(tablero);
            return true;
        }
        boolean resultado = false;
        for (int i = 0; i < N; i++) {
            if (esBuena(tablero, i, col)) {
                tablero[i][col] = 1;
                resultado = resolverNQueen(tablero, col + 1);
                tablero[i][col] = 0;
            }
        }
        return resultado;
    }

    private static void imprimirSolucion(int[][] tablero) {
        System.out.println("Solucion numero: " + (numero_solucion++));
        for (int[] fila : tablero) {
            for (int casilla : fila) {
                System.out.print(((casilla == 0)?"*":casilla) + "\t");
            }
            System.out.println();
        }
    }
}
