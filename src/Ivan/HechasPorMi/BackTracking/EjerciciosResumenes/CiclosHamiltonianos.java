package Ivan.HechasPorMi.BackTracking.EjerciciosResumenes;

import java.util.Arrays;

public class CiclosHamiltonianos {
    public static int V=5;
    public static void main(String[] args) {
        int[][] grafo1 = {
                {0, 1, 0, 1, 0},
                {1, 0, 1, 1, 1},
                {0, 1, 0, 0, 1},
                {1, 1, 0, 0, 1},
                {0, 1, 1, 1, 0},
        };
        int [] camino=new int [V];
        Arrays.fill(camino, -1);
        camino[0]=0;
        hamiltoniano(grafo1,camino,1);
        for (int i = 0; i < camino.length; i++) {
            System.out.println(camino[i]);
        }
    }

    private static boolean hamiltoniano(int[][] grafo1,int[] camino,int posicion) {
        if (posicion==V){
            if (grafo1[camino[posicion-1]][0]==1){
                return true;
            }
        }
        for (int vertice = 0; vertice < V; vertice++) {
            if(valido(posicion,vertice,camino,grafo1)){
                camino[posicion]=vertice;
                if (hamiltoniano(grafo1,camino,posicion+1))return true;
                camino[posicion]=-1;
            }
        }return false;
    }

    private static boolean valido(int posicion, int vertice, int[] camino, int[][] grafo1) {
        if (grafo1[camino[posicion-1]][vertice]==0){
            return false;
        }
        for (int i = 0; i < posicion ; i++) {
            if (camino[i]==vertice){return false;}
        }
        return true;
    }
}
