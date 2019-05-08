package Backtracking;

import java.util.Arrays;

public class CiclosHamiltonianos {

    public static void main(String [] args){
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

    public static void  cicloHamiltoniano(int [][] grafo) {
        int [] camino = new int[grafo.length];
        boolean[] visitados = new boolean[grafo.length];
        Arrays.fill(camino, -1);
        camino[0] = 0;
        if (cicloHamiltoniano(grafo, camino, 1, visitados)) {
            imprimirCamino(camino);
        } else {
            System.out.println("El grafo no tiene ciclo hamiltoniano");
        }
    }

    private static void imprimirCamino(int[] camino) {
        for (int i = 0; i < camino.length; i++) {
            System.out.print(camino[i] + "\t");
        }
        System.out.println(camino[0] + "\t");
    }

    private static boolean cicloHamiltoniano(int[][] grafo, int[] camino, int pos, boolean[] vistados){
        if(pos == grafo.length){
            return grafo[camino[0]][camino[pos - 1]] == 1;
        }else{
            for(int vertice = 1; vertice < grafo.length; vertice++){
                if(esValidos(grafo, vertice,camino, pos, vistados)){
                    camino[pos] = vertice;
                    vistados[vertice] = true;
                    if(cicloHamiltoniano(grafo, camino, pos + 1, vistados)){
                        return true;
                    }
                    vistados[vertice] = false;
                    camino[pos] = -1;
                }
            }
            return false;
        }
    }

    private static boolean esValidos(int[][] grafo, int vertice, int[] camino, int pos, boolean[] vistados) {
        return grafo[camino[pos -1]][vertice] == 1 && !vistados[vertice];
    }
}
