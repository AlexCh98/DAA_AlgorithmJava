package VoracesGrafos;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {
    public static int[] dijkstra(int inicio, List<int[]>[] grafo){
        boolean [] visitados = new boolean[grafo.length];
        int [] padre = new int[grafo.length];
        Arrays.fill(padre, -1);
        int [] distancias = new int[grafo.length];
        Arrays.fill(distancias,Integer.MAX_VALUE);
        int [] aristafalsa = new int[]{-1, inicio, 0};
        PriorityQueue<int[]> cola = new PriorityQueue<>(Comparator.comparing(o -> o[2]));
        cola.add(aristafalsa);
        while (!cola.isEmpty()) {
            int [] arista = cola.poll();
            int origen = arista[0];
            int destino = arista[1];
            int peso = arista[2];
            if(!visitados[destino]){
                visitados[destino] = true;
                distancias[destino] = peso;
                padre[destino] = origen;
                for(int [] adyacente: grafo[destino]){
                    if(peso + adyacente[2] < distancias[adyacente[1]]){
                        cola.add(adyacente);
                        distancias[adyacente[1]] = peso + adyacente[2];
                    }
                }
            }
        }
        System.out.println(Arrays.toString(distancias));
        System.out.println(Arrays.toString(padre));
        return padre;
    }
}
