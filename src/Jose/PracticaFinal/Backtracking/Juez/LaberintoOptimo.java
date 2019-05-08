package Backtracking.Juez;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LaberintoOptimo {

    private static int mejorNumeroMovimientos = Integer.MAX_VALUE;

    public static void main(String[] Args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int tamano_laberinto = Integer.parseInt(reader.readLine());
        int[][] laberinto = new int[tamano_laberinto][tamano_laberinto];
        for (int i = 0; i < tamano_laberinto; i++) {
            String[] linea = reader.readLine().split(" ");
            for (int j = 0; j < tamano_laberinto; j++) {
                laberinto[i][j] = Integer.parseInt(linea[j]);
            }
        }
        resolverlaberinto(laberinto, 0, 0, 1);
        System.out.println(mejorNumeroMovimientos != Integer.MAX_VALUE ? mejorNumeroMovimientos : "SIN SOLUCION");
    }

    private static void resolverlaberinto(int[][] laberinto, int fila, int columna, int paso) {
        laberinto[fila][columna] = paso;
        if (fila == laberinto.length - 1 && columna == laberinto.length - 1) {
            if (laberinto[fila][columna] < mejorNumeroMovimientos) {
                mejorNumeroMovimientos = laberinto[fila][columna];
            }
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
        }
        laberinto[fila][columna] = 0;


    }

    private static boolean esValido(int[][] laberinto, int fila, int columna) {
        return (fila >= 0 && fila < laberinto.length) &&
                (columna >= 0 && columna < laberinto[0].length) &&
                laberinto[fila][columna] == 0;
    }
}
/*
10
0 -1 0 -1 0 0 -1 0 -1 0
0 -1 0 0 -1 0 0 0 -1 0
0 0 -1 0 0 -1 -1 0 -1 0
0 0 0 0 0 0 0 0 0 -1
-1 0 0 0 0 0 0 0 -1 0
0 0 -1 0 0 -1 0 0 0 -1
0 0 0 -1 0 0 -1 -1 0 0
0 0 -1 0 0 -1 0 0 0 -1
0 -1 0 -1 0 0 0 0 0 0
-1 0 0 0 0 0 0 0 0 0

 */
