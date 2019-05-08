package Examenes.Finales;

import java.util.*;

public class DistanciaK{
    private static final int N = 9;

    public static void main(String[] args) {
        List[] grafo = new List[N];
        for (int i = 0; i < N; i++) {
            grafo[i] = new ArrayList();
        }
        grafo[1].add(2);
        grafo[2].add(1);
        grafo[1].add(3);
        grafo[3].add(1);
        grafo[1].add(6);
        grafo[6].add(1);
        grafo[1].add(5);
        grafo[5].add(1);
        grafo[2].add(4);
        grafo[4].add(2);
        grafo[2].add(3);
        grafo[3].add(2);
        grafo[6].add(7);
        grafo[7].add(6);
        grafo[7].add(5);
        grafo[5].add(7);
        grafo[5].add(8);
        grafo[8].add(5);
        int nodoInicial = 2;
        int[] niveles = BFS(grafo, nodoInicial);
        int k = 2;
        System.out.println(Arrays.toString(niveles));
        for (int i = 0; i < grafo.length; i++) {
            if (niveles[i] < k) {// cambia este if para los distintos apartados
                System.out.println(i);
            }
        }
    }

    private static int[] BFS(List<Integer>[] grafo, int nodoInicial) {
        boolean[] visited = new boolean[grafo.length];
        int[] niveles = new int[grafo.length];
        Queue<Integer> cola = new LinkedList<>();
        niveles[nodoInicial] = 0;
        visited[nodoInicial] = true;
        cola.add(nodoInicial);
        while (!cola.isEmpty()) {
            int nodo = cola.remove();
            for (int ady : grafo[nodo]) {
                if (!visited[ady]) {
                    visited[ady] = true;
                    niveles[ady] = niveles[nodo] + 1;
                    cola.add(ady);
                }
            }
        }
        return niveles;
    }
}