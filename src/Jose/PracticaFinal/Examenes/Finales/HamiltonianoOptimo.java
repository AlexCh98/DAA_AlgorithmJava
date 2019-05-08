package Examenes.Finales;

import java.util.Arrays;

public class HamiltonianoOptimo {
    private static int costeMinimo = Integer.MAX_VALUE;
    private static int costeMaximo = Integer.MIN_VALUE;

    public static void main(String[] args) {
        int[][] grafo = new int[5][5];
        for (int i = 0; i < grafo.length; i++) {
            for (int j = 0; j < grafo[i].length; j++) {
                if (Math.random() < 0.7) grafo[i][j] = (int) (Math.random() * 10) + 1;
            }
        }
        ciclosHamiltonianos(grafo);
        if (costeMinimo != Integer.MAX_VALUE) {
            System.out.println("costeMinimo = " + costeMinimo);
            System.out.println("costeMaximo = " + costeMaximo);
        } else {
            System.out.println(-1);
        }
    }

    private static void ciclosHamiltonianos(int[][] grafo) {
        int[] camino = new int[grafo.length];
        Arrays.fill(camino, -1);
        camino[0] = 0;
        boolean[] visitados = new boolean[grafo.length];
        ciclosHamiltonianos(grafo, camino, 1, visitados, 0);
    }

    private static void ciclosHamiltonianos(int[][] grafo, int[] camino, int pos, boolean[] visitados, int costeActual) {
        if (pos >= grafo.length) {
            if (grafo[camino[0]][camino[pos - 1]] > 0) {
                System.out.println(Arrays.toString(camino));
                System.out.println(costeActual);
                costeMinimo = Math.min(costeActual, costeMinimo);
                costeMaximo = Math.max(costeActual, costeMaximo);
            }

        } else {
            for (int v = 1; v < grafo.length; v++) {
                if (esValido(grafo, v, pos, camino, visitados)) {
                    visitados[v] = true;
                    costeActual += grafo[camino[pos - 1]][v];
                    camino[pos] = v;
                    ciclosHamiltonianos(grafo, camino, pos + 1, visitados, costeActual);
                    camino[pos] = -1;
                    costeActual -= grafo[camino[pos - 1]][v];
                    visitados[v] = false;
                }
            }
        }
    }

    private static boolean esValido(int[][] grafo, int v, int pos, int[] camino, boolean[] visitados) {
        return !visitados[v] && grafo[camino[pos - 1]][v] > 0;
    }
}
