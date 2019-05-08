package Examenes.Parciales;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Skillboard {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] linea = reader.readLine().split(" ");
        int numeroHabilidades = Integer.parseInt(linea[0]);
        int numeroCaminos = Integer.parseInt(linea[1]);
        List<int[]>[] mapa = new List[numeroHabilidades];
        for (int i = 0; i < mapa.length; i++) {
            mapa[i] = new ArrayList<>(numeroHabilidades);
        }
        for (int i = 0; i < numeroCaminos; i++) {
            linea = reader.readLine().split(" ");
            int origen = Integer.parseInt(linea[0]) - 1;
            int destino = Integer.parseInt(linea[1]) - 1;
            int peso = Integer.parseInt(linea[2]);
            mapa[origen].add(new int[]{destino, peso});
            mapa[destino].add(new int[]{origen, peso});
        }
        System.out.println(prim(mapa));
    }


    private static int primNoConexo(List<int[]>[] grafo) {
        boolean[] visitados = new boolean[grafo.length];
        int coste = 0;
        for (int v = 0; v < grafo.length; v++) {
            if (!visitados[v]) {
                PriorityQueue<int[]> cola = new PriorityQueue<>(Comparator.comparing(o -> o[1]));
                int[] fakeArista = new int[]{v, 0};
                cola.add(fakeArista);
                while (!cola.isEmpty()) {
                    int[] mejorArista = cola.poll();
                    int destino = mejorArista[0];
                    int peso = mejorArista[1];
                    if (!visitados[destino]) {
                        visitados[destino] = true;
                        coste += peso;
                        grafo[destino].stream().filter(adyacente -> !visitados[adyacente[0]]).forEach(cola::add);
                    }
                }
            }
        }
        return coste;
    }

    private static int prim(List<int[]>[] grafo) {
        boolean[] visitados = new boolean[grafo.length];
        int coste = 0;
        PriorityQueue<int[]> cola = new PriorityQueue<>(Comparator.comparing(o -> o[1]));
        int[] fakeArista = new int[]{0, 0};
        cola.add(fakeArista);
        while (!cola.isEmpty()) {
            int[] mejorArista = cola.poll();
            int destino = mejorArista[0];
            int peso = mejorArista[1];
            if (!visitados[destino]) {
                visitados[destino] = true;
                coste += peso;
                grafo[destino].stream().filter(adyacente -> !visitados[adyacente[0]]).forEach(cola::add);
            }
        }
        return coste;
    }
}
