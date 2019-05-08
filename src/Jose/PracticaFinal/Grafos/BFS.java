package Grafos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {

    public static void main(String[] Args) {
        int n = 10;
        List<Integer>[] g = new List[n];
        for (int i = 1; i < n; i++) {
            g[i] = new ArrayList<>(n);
        }
        g[1].add(2);g[2].add(1);g[1].add(4);g[4].add(1);g[1].add(8);g[8].add(1);
        g[2].add(3);g[3].add(2);g[2].add(4);g[4].add(2);
        g[3].add(4);g[4].add(3);g[3].add(5);g[5].add(3);
        g[4].add(7);g[7].add(4);
        g[5].add(6);g[6].add(5);
        g[6].add(7);g[7].add(6);
        g[7].add(9);g[9].add(7);
        g[8].add(9);g[9].add(8);
        List<Integer>[] recorridos = BFS(g);
        for (List<Integer> recorrido : recorridos) {
            System.out.println(recorrido);
        }
    }

    private static List<Integer>[] BFS(List<Integer>[] grafo) {
        List<Integer>[] recorridos = new List[grafo.length];
        for (int i = 1; i < grafo.length; i++) {
            recorridos[i] = BFS(grafo, i);
        }
        return recorridos;
    }

    private static List<Integer> BFS(List<Integer>[] grafo, int v) {
        List<Integer> recorrido = new ArrayList<>(grafo.length);
        recorrido.add(v);
        boolean[] visitados = new boolean[grafo.length];
        visitados[v] = true;
        Queue<Integer> cola = new LinkedList<>();
        cola.add(v);
        while (!cola.isEmpty()) {
            int aux = cola.poll();
            for (int ady : grafo[aux]) {
                if (!visitados[ady]) {
                    recorrido.add(ady);
                    visitados[ady] = true;
                    cola.add(ady);
                }
            }
        }
        return recorrido;
    }

}
