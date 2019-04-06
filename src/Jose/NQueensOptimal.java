public class NQueensOptimal {
    private static final int N = 8;//Numero de reinas a colocar en un tablero NxN
    private static long numero_solucion = 1;
    private static int maxima_puntuacion = Integer.MIN_VALUE;
    private static long tablero_maximo = 0;
    private static int[][] puntuacion = new int[N][N];
    private static final String RED = "\033[31m";
    private static final String RESET = "\u001B[0m";

    public static void main(String[] args) {
        int[][] tablero = new int[N][N];
        crearPuntuacion();
        System.out.println();
        imprimirArray(puntuacion);
        resolverNQueen(tablero, 0);
        System.out.println("La mejor solucion es: " + tablero_maximo);
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
            int evaluarSolucion = evaluarSolucion(tablero, puntuacion);
            System.out.println("evaluarSolucion(tablero) = " + evaluarSolucion);
            if (maxima_puntuacion <= evaluarSolucion) {
                maxima_puntuacion = evaluarSolucion;
                tablero_maximo = numero_solucion;
                imprimirSolucion(tablero, true);
            } else {
                imprimirSolucion(tablero, false);
            }
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

    private static void imprimirSolucion(int[][] tablero, boolean maximo) {
        String color = maximo ? RED : "";
        System.out.println(color + "Solucion numero: " + numero_solucion++ + RESET);
        for (int[] fila : tablero) {
            for (int casilla : fila) {
                System.out.print(color + ((casilla == 0) ? "*" : casilla) + "\t" + RESET);
            }
            System.out.println();
        }
    }

    private static int evaluarSolucion(int[][] tablero, int[][] puntuaciones) {
        int suma = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                suma += (tablero[i][j] == 1) ? puntuaciones[i][j] : 0;
            }
        }
        return suma;
    }

    private static void crearPuntuacion() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                puntuacion[i][j] = (int) (Math.random() * 10) + 1;
            }
        }
    }

    private static void imprimirArray(int[][] arr) {
        for (int[] ints : arr) {
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }
    }
}

