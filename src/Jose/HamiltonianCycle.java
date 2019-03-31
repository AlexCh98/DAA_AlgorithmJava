import java.util.Arrays;

public class HamiltonianCycle {
    private static int V = 5;

    public static void main(String[] Args) {

        int[][] grafo1 = {
                {0, 1, 0, 1, 0},
                {1, 0, 1, 1, 1},
                {0, 1, 0, 0, 1},
                {1, 1, 0, 0, 1},
                {0, 1, 1, 1, 0},
        };

        int[][] grafo2 = {{0, 1, 0, 1, 0},
                {1, 0, 1, 1, 1},
                {0, 1, 0, 0, 1},
                {1, 1, 0, 0, 0},
                {0, 1, 1, 0, 0},
        };
        cicloHamiltoniano(grafo1);//Ciclo 0 1 2 4 3 0
        cicloHamiltoniano(grafo2);//No tiene ciclo por que quito la arista (3, 4)
    }

    private static boolean esBueno(int v, int[][] grafo, int[] camino, int pos) {
        if (grafo[camino[pos - 1]][v] == 0) return false;
        for (int i = 0; i < pos; i++) {
            if (camino[i] == v) return false;
        }
        return true;
    }

    private static void cicloHamiltoniano(int [][] grafo){
        int[] camino = new int[V];
        Arrays.fill(camino, -1);
        camino[0] = 0;//Empezamos en 0 por que si hay ciclo hamiltoniano nos da igual;
        if(cicloHamiltoniano(grafo, camino, 1)){
            imprimirCamino(camino);
        }else{
            System.out.println("El grafo no tiene ciclo hamiltoniano");
        }
    }

    private static boolean cicloHamiltoniano(int[][] grafo, int[] camino, int pos) {
        if (pos == V) {
            return grafo[camino[pos - 1]][camino[0]] == 1;
        }
        for (int v = 1  ; v < V; v++) {
            if (esBueno(v, grafo, camino, pos)) {
                camino[pos] = v;
                if (cicloHamiltoniano(grafo, camino, pos + 1)) return true;
                camino[pos] = -1;
            }
        }
        return false;
    }

    private static void imprimirCamino(int[] camino) {
        for (int i = 0; i < V; i++) {
            System.out.print(camino[i] + "\t");
        }
        System.out.println(camino[0] + "\t");
    }
}
