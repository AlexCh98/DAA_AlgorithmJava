package poo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int i, v, a, s;
        List<Integer>[] g;

        try {
            InputStreamReader in = new InputStreamReader(System.in);
            BufferedReader input = new BufferedReader(in);

            String linea = input.readLine();
            String[] campos = linea.split(" ");

            v = Integer.parseInt(campos[0]);
            a = Integer.parseInt(campos[1]);

            g = new List[v];

            for(i=0; i<v; i++){
                g[i] = new ArrayList<>();
            }

            for(i=0; i<a; i++){
                linea = input.readLine();
                campos = linea.split(" ");
                g[Integer.parseInt(campos[0])-1].add(Integer.parseInt(campos[1])-1);
                g[Integer.parseInt(campos[1])-1].add(Integer.parseInt(campos[0])-1);
            }

            boolean resultado = depthFirstSearch(g);
            if (resultado){
                System.out.print("true");
            }else{
                System.out.print("false");
            }

        }catch(IOException ex){

        }
    }

    public static boolean depthFirstSearch(List<Integer>[] g){ //Array de listas (grafo como lista de adyacencia)
        int n = g.length;
        boolean siciclo = false;
        int v = 0;
        while((!siciclo) && (v<n)){
            siciclo = depthFirstSearch(g, v, v);
            v++;
        }

        return siciclo;
    }

    public static boolean depthFirstSearch(List<Integer>[] g, int v, int nodo){
        int profundidad = 0;
        boolean siciclo = false;
        boolean[] visitados = new boolean[g.length];
        siciclo = recursiveDFS(g, v, visitados, profundidad, siciclo, nodo);
        return siciclo;
    }

    public static boolean recursiveDFS(List<Integer>[] g, int v, boolean[] visited, int profundidad, boolean siciclo, int nodo){
        visited[v] = true;
        boolean encontrado = false;
        if((profundidad > 1) && g[v].contains(nodo)){
            encontrado = true;
            return encontrado;
        }
        for (int adj : g[v]){
            if (!visited[adj]){
                encontrado = recursiveDFS(g, adj, visited, profundidad+1 , siciclo, nodo);
            }
        }
        return encontrado;
    }

}
