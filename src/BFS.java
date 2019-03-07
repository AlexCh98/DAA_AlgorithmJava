import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BFS {

    public static void main(String[] args) {
        // write your code here
        int n = 10;
        List<Integer>[] Grafo = new List[n];
        for (int i = 1; i < n; i++) {
            Grafo[i] = new ArrayList<>(n);
        }
    }

    public static List<Integer> DFS(List<Integer>[] grafo, int vertice) {
        int n = grafo.length;
        ArrayList<Integer> salida = new ArrayList<>(n);
        boolean [] visitados = new boolean[n];
        Stack<Integer> cola = new Stack<>();
        visitados[vertice] = true;
        cola.push(vertice);
        while(!cola.isEmpty()){
            int aux = cola.pop();
            for(Integer ady: grafo[aux]){
                if(!visitados[vertice]){
                    visitados[vertice] = true;
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
