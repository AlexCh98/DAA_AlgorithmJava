import java.util.Arrays;

public class MColoringProblem {
    private static final int V = 30;

    public static void main(String[] Args) {
        /*int[][] grafo = new int[][]{//Grafo no diriguido con 4 nodos representado por matriz de adyacencia
                {0, 1, 1, 1},
                {1, 0, 1, 0},
                {1, 1, 0, 1},
                {1, 0, 1, 0},
        };*/
        int[][] grafo = new int[V][V];
        for (int i = 0; i < V; i++) {
            for (int j = i; j < V; j++) {
                if (i == j) {
                    grafo[i][j] = 0;
                } else {
                    int random_0_1 = (Math.random() < 0.5) ? 0 : 1;
                    grafo[i][j] = random_0_1;
                    grafo[j][i] = random_0_1;
                }
            }
        }
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                System.out.print(grafo[i][j] + "\t");
            }
            System.out.println();
        }
        int[] color = new int[V];
        for (int m = 1; m <= V; m++) {
            if (colorearGrafo(grafo, color, 0, m)) {
                System.out.println("El grafo se puede colorear con  " + m + " colores :" + Arrays.toString(color));
                break;
            }
        }
    }

    private static boolean esBuena(int v, int[][] grafo, int c, int[] color) {
        for (int i = 0; i < V; i++) {
            if (grafo[v][i] == 1 && c == color[i]) return false;
        }
        return true;
    }

    private static boolean colorearGrafo(int[][] grafo, int[] color, int v, int m) {
        if (v == V) return true;
        for (int c = 1; c <= m; c++) {
            if (esBuena(v, grafo, c, color)) {
                color[v] = c;
                if (colorearGrafo(grafo, color, v + 1, m)) return true;
                color[v] = 0;
            }
        }
        return false;
    }
}
