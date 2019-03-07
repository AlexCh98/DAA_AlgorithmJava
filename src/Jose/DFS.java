package Jose;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DFS {

    public static void main(String[] args) {
        // write your code here
        int n = 10;
        List<Integer>[] g = new List[n];
        for (int i = 1; i < n; i++) {
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
        List<Integer>[] recorridos = DFS(g);
        for(List<Integer> list : recorridos){
            System.out.println(list);
        }
    }


    private static List<Integer> DFS(List<Integer>[] grafo, int vertice) {
        int n = grafo.length;
        ArrayList<Integer> salida = new ArrayList<>(n);
        boolean [] visitados = new boolean[n];
        Stack<Integer> cola = new Stack<>();
        visitados[vertice] = true;
        cola.push(vertice);
        while(!cola.isEmpty()){
            int aux = cola.pop();
            for(Integer ady: grafo[aux]){
                if(!visitados[ady]){
                    visitados[ady] = true;
                    salida.add(ady);
                    cola.push(ady);
                }
            }
        }
        return salida;
    }

    public static List<Integer>[] DFS(List<Integer>[] grafo) {
        List<Integer>[] salida = new List[grafo.length];
        for (int i = 1; i < grafo.length; i++) {
            salida[i] = DFS(grafo, i);
        }
        return salida;
    }

}
