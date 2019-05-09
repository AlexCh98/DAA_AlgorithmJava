package Ivan.MierdaPaBorrar;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS2 {
    public static void main(String[] args) {
        int vertices=5;
        List[] grafo =new List[vertices];
        for (int i = 0; i < vertices; i++) {
            grafo[i]=new ArrayList<Integer>(vertices);
        }
        List[] camino =new List[vertices];
        busquedaAnchura(grafo);
    }

    private static List[] busquedaAnchura(List[] grafo) {
        List[] solucion =new List[grafo.length];
        for (int i = 0; i < grafo.length; i++) {
            solucion[i]=busquedaAnchura(grafo,i);
        }
        return solucion;
    }
    private static List<Integer> busquedaAnchura (List<Integer>[] grafo,int vertice) {
        int tam =grafo.length;
        boolean[] visitados = new boolean[tam];
        Queue<Integer> colanodos =new LinkedList<>();
        ArrayList<Integer> solucion = new ArrayList<>(tam);
        colanodos.add(vertice);
        visitados[vertice]=true;
        while (!colanodos.isEmpty()){
            int aux = colanodos.remove();
            for (Integer adyacente:grafo[aux]){
                visitados[adyacente]=true;
                colanodos.add(adyacente);
                solucion.add(adyacente);
            }
        }
       return solucion;
    }
}
