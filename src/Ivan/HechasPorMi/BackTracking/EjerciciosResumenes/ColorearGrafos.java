package Ivan.HechasPorMi.BackTracking.EjerciciosResumenes;

import java.util.Arrays;

public class ColorearGrafos {
    public static int V=5;
    public static void main(String[] args) {
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

    private static boolean colorearGrafo(int[][] grafo, int[] color, int vertice, int numero_colores) {
        if (vertice==V){
            return true;
        }
        for (int i = 0; i < numero_colores; i++) {
            if(buena(vertice,grafo,i,color)){
                color[vertice]=i;
                if (colorearGrafo(grafo,color,vertice+1,numero_colores)){
                    return true;
                }
            }color[vertice]=0;
        }return false;
    }

    private static boolean buena(int vertice, int[][] grafo, int i, int[] color) {
        for (int j = 0; j < V; j++) {
            if ((grafo[vertice][j]==1)&&(i==color[j])){
                return false;
            }
        }
        return true;
    }
}
