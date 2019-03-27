package poo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

            public static void main(String[] args) {
                // write your code
                int i, v, a, s;
                List<Integer>[] g;

                try {
                    InputStreamReader in = new InputStreamReader(System.in);
                    BufferedReader input = new BufferedReader(in);

                    String linea = input.readLine();
                    String[] campos = linea.split(" ");

                    v = Integer.parseInt(campos[0]);
                    a = Integer.parseInt(campos[1]);
                    s = Integer.parseInt(campos[2]);

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

                    List<Integer> bfs = breadthFirstSearch(g,s-1);
                    for(int x: bfs){
                        System.out.print(x+1);
                        System.out.print(" ");
                    }
                }catch(IOException ex){

                }
            }

    public static List<Integer> breadthFirstSearch(List<Integer>[] g, int v) {
        int n = g.length;
        List<Integer> traversal = new ArrayList<>(n);
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        visited[v] = true;
        traversal.add(v);
        q.add(v);
        while (!q.isEmpty()){
            int aux = q.remove(); //Coge el primero y lo quita.
            for (int adj : g[aux]){
                if (!visited[adj]){
                    visited[adj] = true;
                    traversal.add(adj);
                    q.add(adj);
                }
            }
        }
        return traversal;
    }
}

