package Examenes.Parciales;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Escenarios {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] linea = reader.readLine().split(" ");
        int numeroEscenarios = Integer.parseInt(linea[0]);
        int numeroConexiones = Integer.parseInt(linea[1]);
        List<Integer>[] mapa = new List[numeroEscenarios];
        for (int i = 0; i < mapa.length; i++) {
            mapa[i] = new ArrayList<>(numeroEscenarios);
        }
        for (int i = 0; i < numeroConexiones; i++) {
            linea = reader.readLine().split(" ");
            int escenario_u = Integer.parseInt(linea[0]) - 1;
            int escenario_v = Integer.parseInt(linea[1]) - 1;
            mapa[escenario_u].add(escenario_v);
            mapa[escenario_v].add(escenario_u);
        }
        System.out.println(numeroComponenetes(mapa));
    }

    private static int numeroComponenetes(List<Integer>[] grafo) {
        int numeroComponetes = 0;
        boolean[] visitados = new boolean[grafo.length];
        for (int vertice = 0; vertice < grafo.length; vertice++) {
            if (!visitados[vertice]) {
                visitados[vertice] = true;
                numeroComponetes++;
                Queue<Integer> cola = new LinkedList<>();
                cola.add(vertice);
                while (!cola.isEmpty()) {
                    int aux = cola.poll();
                    for (int adyacente : grafo[aux]) {
                        if (!visitados[adyacente]) {
                            cola.add(adyacente);
                            visitados[adyacente] = true;
                        }
                    }
                }
            }
        }
        return numeroComponetes;
    }
}
/*
6 6
1 2
1 3
2 3
4 5
4 6
5 6

 */