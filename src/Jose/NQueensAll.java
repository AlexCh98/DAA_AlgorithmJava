public class NQueensAll {
    private static final int N = 4;//Numero de reinas a colocar en un tablero NxN
    private static long numero_solucion = 1;
    private static StringBuilder salida = new StringBuilder();
    public static void main(String[] args) {
        int[][] tablero = new int[N][N];
        resolverNQueen(tablero, 0);
        System.out.println(salida);
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
        salida.append("Solucion numero: ").append(numero_solucion++);
        salida.append("\n");
        //System.out.println("Solucion numero: " + (numero_solucion++));
        for (int[] fila : tablero) {
            for (int casilla : fila) {
                salida.append((casilla == 0) ? "*" : casilla).append("\t");
            }
            salida.append("\n");
        }
    }
}
