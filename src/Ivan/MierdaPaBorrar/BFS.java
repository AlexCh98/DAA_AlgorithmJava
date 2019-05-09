package Ivan.MierdaPaBorrar;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {
    public static void main(String[] args) {
        int num_vertices=5;
        List<Integer>[] grafo = new List[num_vertices+1];
        for (int i = 0; i <= num_vertices; i++) {
            grafo[i]=new ArrayList<>(num_vertices);
        }
        List<Integer>[] recorridos = funcionBFS(grafo);
    }

    private static List<Integer>[] funcionBFS(List[] grafo) {
        List<Integer>[] solucion=new List[grafo.length];
        for (int v = 0; v < grafo.length; v++) {
             solucion[v]=funcionBFS(grafo,v);
        }
        return solucion;
    }
    private static List<Integer> funcionBFS (List<Integer>[] grafo,int vertice){
        ArrayList<Integer> solucion=new ArrayList<>(grafo.length);
        boolean[] visitados =new boolean[grafo.length];
        Queue<Integer> cola_nodos =new LinkedList<>();
        visitados[vertice]=true;
        cola_nodos.add(vertice);
        solucion.add(vertice);
        while(!cola_nodos.isEmpty()){
            int aux =cola_nodos.remove();
            for (Integer adj: grafo[aux]) {
                if (!visitados[adj]){
                    visitados[adj]=true;
                    solucion.add(adj);
                    cola_nodos.add(adj);
                }
            }
        }
        return solucion;
    }

}
