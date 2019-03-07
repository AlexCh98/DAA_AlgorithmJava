package Jose;

import java.util.*;

public class BFS {

    public static void main(String[] args) {
        // write your code here
        int n = 10;
        List<Integer>[] Grafo = new List[n];
        for (int i = 1; i < n; i++) {
            Grafo[i] = new ArrayList<>(n);
        }
        //Rellenar grafo
        List<Integer>[] recorridos = BFS(Grafo);
    }


    private static List<Integer> BFS(List<Integer>[] grafo, int vertice) {
        int n = grafo.length;
        ArrayList<Integer> salida = new ArrayList<>(n);
        boolean [] visitados = new boolean[n];
        Queue<Integer> pila = new LinkedList<>();
        visitados[vertice] = true;
        pila.add(vertice);
        while(!pila.isEmpty()){
            int aux = pila.remove();
            for(Integer ady: grafo[aux]){
                if(!visitados[ady]){
                    visitados[ady] = true;
                    salida.add(ady);
                    pila.add(ady);
                }
            }
        }
        return salida;
    }

    public static List<Integer>[] BFS(List<Integer>[] grafo) {
        List<Integer>[] salida = new List[grafo.length];
        for (int i = 1; i < grafo.length; i++) {
            salida[i] = BFS(grafo, i);
        }
        return salida;
    }

}
