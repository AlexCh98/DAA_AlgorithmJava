package Alex.AlgoritmosGrafos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* ############ RECORRIDO EN ANCHURA ##############
 *      BREATH-FIRST SEARCH
 *   -- Visita todos los vértices a una distancia k antes de
 *      descubrir el primer vértice a la distancia k+1.
 *   -- De manera iterativa.
 *   -- Se tiene que incluir una cola con los vértices visitados para evitar ciclos y
 *      establecer el orden en la búsqueda
 *
 * */
public class BFS {

    public static void main(String[] args) {
        int n = 10;
        List[] g = new List[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>(n);
        }

        g[1].add(2); g[2].add(1);
        g[1].add(4); g[4].add(1);
        g[1].add(8); g[8].add(1);
        g[2].add(3); g[3].add(2);
        g[2].add(4); g[4].add(2);
        g[3].add(4); g[4].add(3);
        g[3].add(5); g[5].add(3);
        g[4].add(7); g[7].add(4);
        g[5].add(6); g[6].add(5);
        g[6].add(7); g[7].add(6);
        g[7].add(9); g[9].add(7);
        g[8].add(9); g[9].add(8);
        List[] recorridos = BFS(g);
        for(List<Integer> list : recorridos){
            System.out.println(list);
        }
    }

    private static List<Integer> BFS(List<Integer>[] grafo, int vertice) {
        int n = grafo.length;
        ArrayList<Integer> salida = new ArrayList<>(n);
        boolean [] visitados = new boolean[n];
        Queue<Integer> cola = new LinkedList<>();
        visitados[vertice] = true;
        salida.add(vertice);
        cola.add(vertice);
        while(!cola.isEmpty()){
            int aux = cola.remove();
            for(Integer ady: grafo[aux]){
                if(!visitados[ady]){
                    visitados[ady] = true;
                    salida.add(ady);
                    cola.add(ady);
                }
            }
        }
        return salida;
    }

    public static List[] BFS(List<Integer>[] grafo) {
        List[] salida = new List[grafo.length];
        for (int i = 0; i < grafo.length; i++) {
            salida[i] = BFS(grafo, i);
        }
        return salida;
    }
}

