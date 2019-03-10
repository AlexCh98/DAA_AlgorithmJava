package Alex;


/* ############ RECORRIDO EN PROFUNDIDAD ##############
 *      DEPTH-FIRST SEARCH
 *   -- Dado un vértice, antes de visitar su hermano se visita a su hijo
 *      (equivalente a un recorrido preorden).
 *   -- De manera recursiva.
 *   -- Se tiene que incluir un conjunto de vértices visitados para
 *      evitar ciclos en la búsqueda
 * */

import java.util.ArrayList;
import java.util.List;

public class DFS {

    public static void main(String[] args) {
        // write your code here
        int n = 10;
        List<Integer>[] grafo = new List[n];
        for (int i = 1; i < n; i++) {
            grafo[i] = new ArrayList<>(n);
        }
        grafo[1].add(2); grafo[2].add(1);
        grafo[1].add(4); grafo[4].add(1);
        grafo[1].add(8); grafo[8].add(1);
        grafo[2].add(3); grafo[3].add(2);
        grafo[2].add(4); grafo[4].add(2);
        grafo[3].add(4); grafo[4].add(3);
        grafo[3].add(5); grafo[5].add(3);
        grafo[4].add(7); grafo[7].add(4);
        grafo[5].add(6); grafo[6].add(5);
        grafo[6].add(7); grafo[7].add(6);
        grafo[7].add(9); grafo[9].add(7);
        grafo[8].add(9); grafo[9].add(8);
        List[] recorridos = DFS_recursivoPrincipal(grafo);
        for (List list : recorridos) {
            System.out.println(list);
        }
    }

    private static List<Integer> DFS_recursivo(List<Integer>[] grafo, int vertice) {
        int n = grafo.length;
        ArrayList<Integer> salida = new ArrayList<>(n);
        boolean[] visitados = new boolean[n];
        DFS_recursivo_aux(grafo, vertice, visitados, salida);
        return salida;

    }

    private static void DFS_recursivo_aux(List<Integer>[] grafo, int vertice, boolean[] visitados, ArrayList<Integer> salida) {
        visitados[vertice] = true;
        salida.add(vertice);
        for (int ady : grafo[vertice]) {
            if (!visitados[ady]) {
                visitados[ady] = true;
                DFS_recursivo_aux(grafo, ady, visitados, salida);
            }
        }
    }

    private static List[] DFS_recursivoPrincipal(List<Integer>[] grafo) {
        List[] salida = new List[grafo.length];
        for (int i = 1; i < grafo.length; i++) {
            salida[i] = DFS_recursivo(grafo, i);
        }
        return salida;
    }
}
