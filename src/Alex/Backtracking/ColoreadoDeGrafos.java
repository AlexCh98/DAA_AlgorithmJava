package Alex.Backtracking;

import java.util.ArrayList;
import java.util.List;

public class ColoreadoDeGrafos {
    public static void printSolution(int[] solution) {
        for (int i = 0; i < solution.length; i++) {
            System.out.print("["+solution[i]+"]");
        }
        System.out.println();
    }

    public static boolean isFeasible(List<Integer>[] graph, int k, int[] solution) {
        for (int adj : graph[k]) {
            if (solution[adj] == solution[k]) {
                return false;
            }
        }
        return true;
    }

    public static void color(List<Integer>[] graph, int k, int m, int[] solution) {
        solution[k] = 0;
        while (solution[k] != m) { //Mientras no se hayan comprobado los 3 colores
            solution[k]++;  //Pinto de un color
            if (isFeasible(graph, k, solution)) {
                if (k < solution.length-1) {    //Si no se ha llegado al final
                    color(graph, k+1, m, solution);
                } else {
                    printSolution(solution);
                }
            }
        }
    }

    public static void main(String[] args) {
        List<Integer>[] grafo = new List[4];
        for (int i = 0; i < grafo.length; i++) {
            grafo[i] = new ArrayList<>();
        }
        grafo[0].add(1); grafo[1].add(0);
        grafo[0].add(2); grafo[2].add(0);
        grafo[0].add(3); grafo[3].add(0);
        grafo[2].add(3); grafo[3].add(2);
        //Grafo, nodo origen, numeroColores, arrayColores
        color(grafo, 0, 3, new int[4]);
    }
}
