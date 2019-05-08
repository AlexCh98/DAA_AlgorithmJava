package Grafos.Juez;

import Grafos.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Escenarios {
    public static void main (String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String [] linea = reader.readLine().split(" ");
        int numeroEscenarios = Integer.parseInt(linea[0]);
        int numeroConexiones = Integer.parseInt(linea[1]);
        List<Integer>[] mapa = new List[numeroEscenarios];
        for (int i = 0; i < mapa.length; i++) {
            mapa[i] = new ArrayList<>(numeroEscenarios);
        }
        for (int conexion = 0; conexion < numeroConexiones; conexion++) {
            linea = reader.readLine().split(" ");
            int esceneario1 = Integer.parseInt(linea[0]);
            int esceneario2 = Integer.parseInt(linea[1]);
            mapa[esceneario1].add(esceneario2);
            mapa[esceneario2].add(esceneario1);
        }
        System.out.println(contarComponentes(mapa));
    }
    private static int contarComponentes(List<Integer>[] grafo){
        int componenetes = 0;
        Set<Integer> nodos_recorridos = new HashSet<>(grafo.length);
        for (int nodo = 0; nodo < grafo.length; nodo++) {
            if (!nodos_recorridos.contains(nodo)) {
                new DFS();
                List<Integer> recorrido = DFS.DFS(grafo, nodo);
                if(recorrido.size() != grafo.length) componenetes++;
                nodos_recorridos.addAll(recorrido);
            }
        }
        return componenetes;
    }
}
/*
6 6
0 1
0 2
1 2
3 4
3 5
4 5

 */
