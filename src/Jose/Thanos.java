package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Thanos {
    private static int numero_caminos = 0;

    public static void main(String[] Args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] linea = reader.readLine().split(" ");
        int numero_planetas = Integer.parseInt(linea[0]);
        int numero_conexiones = Integer.parseInt(linea[1]);
        int[][] universo = new int[numero_planetas][numero_planetas];
        for (int i = 0; i < numero_conexiones; i++) {
            linea = reader.readLine().split(" ");
            int planeta_u = Integer.parseInt(linea[0]);
            int planeta_v = Integer.parseInt(linea[1]);
            universo[planeta_u][planeta_v] = 1;
            universo[planeta_v][planeta_u] = 1;
        }
        int[] camino = new int[numero_planetas];
        Arrays.fill(camino, -1);
        camino[0] = 0;
        ciclo(universo, camino, 1);
        System.out.println(numero_caminos);

    }

    private static boolean ciclo(int[][] grafo, int[] camino, int pos) {
        if (pos == grafo.length) {
            if (grafo[camino[pos - 1]][0] == 1) {
                numero_caminos++;
                return true;
            }
        }
        boolean resultado = false;
        for (int v = 1; v < grafo.length; v++) {
            if (esValido(pos, v, camino, grafo)) {
                camino[pos] = v;
                resultado = ciclo(grafo, camino, pos + 1);
                camino[pos] = -1;
            }
        }
        return resultado;
    }

    private static boolean esValido(int pos, int v, int[] camino, int[][] grafo) {
        if (grafo[camino[pos - 1]][v] == 0) return false;
        for (int i = 0; i < pos; i++) {
            if (camino[i] == v) return false;
        }
        return true;
    }
}
