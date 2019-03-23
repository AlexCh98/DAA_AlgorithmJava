package Jose;

import java.util.*;


public class Ciclos {
    public static void main(String[] Args) {
        Scanner reader = new Scanner(System.in);
        int nodos = reader.nextInt();
        int aristas = reader.nextInt();
        List<Integer>[] g = new List[nodos + 1];
        for (int i = 0; i <= nodos; i++) {
            g[i] = new ArrayList<>(nodos);
        }
        for (int j = 0; j < aristas; j++) {
            int nodo_1 = reader.nextInt();
            int nodo_2 = reader.nextInt();
            g[nodo_1].add(nodo_2);
            g[nodo_2].add(nodo_1);
        }
        System.out.print(esCiclico(g));
    }

    private static boolean esCiclico(List<Integer>[] g) {
        for (int i = 1; i < g.length; i++) {
            if (esCiclico(g, i)) {
                return true;
            }
        }
        return false;
    }

    private static boolean esCiclico(List<Integer>[] g, int v) {
        boolean[] visited = new boolean[g.length];
        visited[v] = true;
        Queue<Integer> cola = new LinkedList<>();
        cola.add(v);
        int[] parent = new int[g.length];
        while (!cola.isEmpty()) {
            int aux = cola.remove();
            for (int ady : g[aux]) {
                if (!visited[ady]) {
                    visited[ady] = true;
                    parent[ady] = aux;
                    cola.add(ady);
                } else if (parent[aux] != ady) {
                    return true;
                }
            }
        }
        return false;
    }
}
