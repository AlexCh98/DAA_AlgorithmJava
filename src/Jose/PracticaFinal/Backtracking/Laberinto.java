package Backtracking;

public class Laberinto {

    public static void main(String[] args) {
        int[][] laberinto = {
//               0  1  2  3  4  5  6
                {0, -1, -1, -1, -1, -1, -1}, //0
                {0, -1, -1, 0, -1, -1, -1},  //1
                {0, 0, -1, 0, -1, -1, -1},   //2
                {-1, 0, -1, -1, -1, -1, -1}, //3
                {0, 0, -1, -1, -1, -1, -1},  //4
                {0, -1, -1, -1, -1, -1, -1}, //5
                {0, 0, 0, 0, 0, 0, 0} //6
        };
        resolverlaberinto(laberinto, 0, 0, 1);

    }

    private static void resolverlaberinto(int laberinto[][], int fila, int columna, int paso) {
        laberinto[fila][columna] = paso;
        if (fila == laberinto.length - 1 && columna == laberinto.length - 1) {
            imprimirSolucion(laberinto);
        } else {
            if (esValido(laberinto, fila + 1, columna)) {
                resolverlaberinto(laberinto, fila + 1, columna, paso + 1);
            }
            if (esValido(laberinto, fila, columna + 1)) {
                resolverlaberinto(laberinto, fila, columna + 1, paso + 1);
            }
            if (esValido(laberinto, fila - 1, columna)) {
                resolverlaberinto(laberinto, fila - 1, columna, paso + 1);
            }
            if (esValido(laberinto, fila, columna - 1)) {
                resolverlaberinto(laberinto, fila, columna - 1, paso + 1);
            }
            laberinto[fila][columna] = 0;
        }

    }

    private static void imprimirSolucion(int[][] laberinto) {
        for (int[] fila : laberinto) {
            for (int valor : fila) {
                System.out.print("\t" + valor + "\t");
            }
            System.out.println();
        }

    }

    private static boolean esValido(int[][] laberinto, int fila, int columna) {
        return (fila >= 0 && fila < laberinto.length) &&
                (columna >= 0 && columna < laberinto[0].length) &&
                laberinto[fila][columna] == 0;
    }
}
