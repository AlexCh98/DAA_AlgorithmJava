package Examenes.Finales;

public class NReinasOptimo {
    private static final int MAXPROM = 0;
    private static final int MAXMIN = 1;
    private static final int MINMAX = 2;
    private static double mejorValor;

    public static void main(String[] args) {
        int[][] tablero = new int[][]{
                {1, 5, 9, 13},
                {2, 6, 9, 14},
                {3, 7, 11, 15},
                {4, 8, 12, 16}
        };
        int[][] solucion = new int[tablero.length][tablero.length];
        mejorValor = Integer.MIN_VALUE;
        calcularOptimo(solucion, 0, MAXPROM, tablero);
        System.out.println(mejorValor);
        mejorValor = Integer.MIN_VALUE;
        calcularOptimo(solucion, 0, MAXMIN, tablero);
        System.out.println(mejorValor);
        mejorValor = Integer.MAX_VALUE;
        calcularOptimo(solucion, 0, MINMAX, tablero);
        System.out.println(mejorValor);
        mejorValor = 0;
    }

    private static void calcularOptimo(int[][] solucion, int col, int problema, int[][] tablero) {
        if (col >= solucion.length) {
            mejorValor = mejor(calcularsolucion(solucion, problema, tablero), mejorValor, problema);
        } else {
            for (int fila = 0; fila < solucion.length; fila++) {
                if (esValido(fila, col, solucion)) {
                    solucion[fila][col] = 1;
                    calcularOptimo(solucion, col + 1, problema, tablero);
                    solucion[fila][col] = 0;
                }
            }
        }
    }

    private static boolean esValido(int fila, int col, int[][] solucion) {
        for (int j = col; j >= 0; j--) {
            if (solucion[fila][j] == 1) return false;
        }
        for (int i = fila, j = col; i >= 0 && j >= 0; i--, j--) {
            if (solucion[i][j] == 1) return false;
        }
        for (int i = fila, j = col; i < solucion.length && j >= 0; i++, j--) {
            if (solucion[i][j] == 1) return false;
        }
        return true;
    }

    private static double mejor(double calcularsolucion, double mejorValor, int problema) {
        if (problema == MAXPROM || problema == MAXMIN) return Math.max(calcularsolucion, mejorValor);
        return Math.min(calcularsolucion, mejorValor);
    }

    private static double calcularsolucion(int[][] solucion, int problema, int[][] tablero) {
        double maxprom = 0;
        double maximo = Integer.MIN_VALUE;
        double minimo = Integer.MAX_VALUE;
        for (int i = 0; i < solucion.length; i++) {
            for (int j = 0; j < solucion[i].length; j++) {
                if (solucion[i][j] == 1) {
                    maxprom += tablero[i][j];
                    if (tablero[i][j] > maximo) maximo = tablero[i][j];
                    if (tablero[i][j] < minimo) minimo = tablero[i][j];
                }
            }
        }
        switch (problema) {
            case MAXPROM:
                return maxprom / tablero.length;
            case MAXMIN:
                return minimo;
            case MINMAX:
                return maximo;
        }
        return 0;
    }

}
