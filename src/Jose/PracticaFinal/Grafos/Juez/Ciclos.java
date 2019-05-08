package Grafos.Juez;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Ciclos {

    public static void main(String[] Args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] linea = reader.readLine().split(" ");
        int nodos = Integer.parseInt(linea[0]);
        int aristas = Integer.parseInt(linea[1]);
        List<Integer>[] grafo = new List[nodos + 1];
        for (int i = 0; i <= nodos; i++) {
            grafo[i] = new ArrayList<>(nodos);
        }
        for (int i = 0; i < aristas; i++) {
            linea = reader.readLine().split(" ");
            int nodo_1 = Integer.parseInt(linea[0]);
            int nodo_2 = Integer.parseInt(linea[1]);
            grafo[nodo_1].add(nodo_2);
            grafo[nodo_2].add(nodo_1);
        }
        System.out.println(esCiclico(grafo));
    }

    private static boolean esCiclico(List<Integer>[] grafo) {
        for (int i = 0; i < grafo.length; i++) {
            if (esCiclico(grafo, i)) return true;
        }
        return false;
    }

    private static boolean esCiclico(List<Integer>[] grafo, int v) {
        boolean[] visitados = new boolean[grafo.length];
        int[] predecesor = new int[grafo.length];
        Queue<Integer> cola = new LinkedList<>();
        visitados[v] = true;
        cola.add(v);
        while (!cola.isEmpty()) {
            int aux = cola.remove();
            for (int ady : grafo[aux]) {
                if (!visitados[ady]) {
                    visitados[ady] = true;
                    predecesor[ady] = aux;
                    cola.add(ady);
                } else if (predecesor[aux] != ady) {
                    return true;
                }
            }
        }
        return false;
    }
}
