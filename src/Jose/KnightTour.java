package Jose;

import java.util.Arrays;

public class KnightTour {

    private static final int[] MOVIMIENTOSX = {2, 1, -1, -2, -2, -1, 1, 2};
    private static final int[] MOVIMIENTOSY = {1, 2, 2, 1, -1, -2, -2, -1};
    private static final int TAMANO_TABLERO = 8;
    private static final int MOVIMIENTOS_CABALLO = 8;

    public static void main(String[] args) {
        int[][] tablero = new int[TAMANO_TABLERO][TAMANO_TABLERO];
        for (int i = 0; i < TAMANO_TABLERO; i++) {
            Arrays.fill(tablero[i], -1);
        }
        tablero[0][0] = 0;
        imprimirTablero(tablero);
        knightTour(tablero, 1, 0, 0);
        imprimirTablero(tablero);
    }

    static boolean esBuena(int x, int y, int tablero[][]) {
        return (x >= 0 && x < TAMANO_TABLERO && y >= 0 &&
                y < TAMANO_TABLERO && tablero[x][y] == -1);
    }

    private static void imprimirTablero(int[][] tablero) {
        for (int[] ints : tablero) {
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }
    }

    private static boolean knightTour(int[][] tablero, int movimiento, int x, int y) {
        int i, nextX, nextY;
        if (movimiento == TAMANO_TABLERO * TAMANO_TABLERO) {
            return true;
        }
        for (i = 0; i < MOVIMIENTOS_CABALLO; i++) {
            nextX = x + MOVIMIENTOSX[i];
            nextY = y + MOVIMIENTOSY[i];
            if (esBuena(nextX, nextY, tablero)) {
                tablero[nextX][nextY] = movimiento;
                if (knightTour(tablero, movimiento + 1, nextX, nextY)) {
                    return true;
                } else {
                    tablero[nextX][nextY] = -1;
                }
            }
        }
        return false;
    }
}
