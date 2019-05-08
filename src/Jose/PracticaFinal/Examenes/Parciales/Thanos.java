package Examenes.Parciales;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Thanos {

    private static int numeroCaminos = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String [] linea = reader.readLine().split(" ");
        int numeroPlanetas = Integer.parseInt(linea[0]);
        int numeroConexiones = Integer.parseInt(linea[1]);
        int [][] mapa = new int[numeroPlanetas][numeroPlanetas];
        for (int i = 0; i < numeroConexiones; i++) {
            linea = reader.readLine().split(" ");
            int origen = Integer.parseInt(linea[0]);
            int destino = Integer.parseInt(linea[1]);
            mapa[origen][destino] = 1;
            mapa[destino][origen] = 1;
        }

        ciclosHamiltoniano(mapa);
        System.out.println(numeroCaminos);
    }

    private static boolean ciclosHamiltoniano(int[][] grafo) {
        boolean[] visitados = new boolean[grafo.length];
        int[] camino = new int[grafo.length];
        Arrays.fill(camino, -1);
        camino[0] = 0;
        return ciclosHamiltoniano(grafo, 1, camino, visitados);
    }

    private static boolean ciclosHamiltoniano(int[][] grafo, int pos, int[] camino, boolean[] visitados) {
        if (pos == grafo.length) {
            if (grafo[camino[0]][camino[pos - 1]] == 1) {
                numeroCaminos++;
                return true;
            }
        }
        boolean resultado = false;
        for (int v = 1; v < grafo.length; v++) {
            if (esValido(grafo, v, camino, pos, visitados)) {
                visitados[v] = true;
                camino[pos] = v;
                resultado = ciclosHamiltoniano(grafo, pos + 1, camino, visitados);
                camino[pos] = -1;
                visitados[v] = false;
            }
        }
        return resultado;
    }

    private static boolean esValido(int[][] grafo, int v, int[] camino, int pos, boolean[] visitados) {
        return !visitados[v] && grafo[camino[pos - 1]][v] == 1;
    }
}
