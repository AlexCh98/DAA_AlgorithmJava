package VoracesGrafos;

import java.util.*;

public class Prim {

    public static void main(String[] args) {
        List<int[]>[] grafo = new List[8];
        for (int i = 0; i < grafo.length; i++) {
            grafo[i] = new ArrayList<>();
        }
        grafo[1].add(new int[]{1, 3, 1});
        grafo[1].add(new int[]{1, 4, 2});
        grafo[1].add(new int[]{1, 7, 6});
        grafo[2].add(new int[]{2, 5, 2});
        grafo[2].add(new int[]{2, 6, 7});
        grafo[2].add(new int[]{2, 7, 7});
        grafo[3].add(new int[]{3, 1, 1});
        grafo[3].add(new int[]{3, 4, 3});
        grafo[3].add(new int[]{3, 7, 5});
        grafo[4].add(new int[]{4, 1, 2});
        grafo[4].add(new int[]{4, 3, 3});
        grafo[4].add(new int[]{4, 5, 1});
        grafo[4].add(new int[]{4, 6, 9});
        grafo[5].add(new int[]{5, 2, 2});
        grafo[5].add(new int[]{5, 4, 1});
        grafo[5].add(new int[]{5, 7, 8});
        grafo[6].add(new int[]{6, 2, 4});
        grafo[6].add(new int[]{6, 4, 9});
        grafo[7].add(new int[]{7, 1, 6});
        grafo[7].add(new int[]{7, 2, 7});
        grafo[7].add(new int[]{7, 3, 5});
        grafo[7].add(new int[]{7, 5, 8});
        /*IntStream.range(0, grafo.length).forEachOrdered(i -> {
            System.out.print(i + "->");
            grafo[i].stream().map(adyacentes -> Arrays.toString(adyacentes) + "\t").forEach(System.out::print);
            System.out.println();
        })*/
        ;
        Set<int[]> MST = prim(1, grafo);
        MST.stream().map(arista -> Arrays.toString(arista) + "\t").forEach(System.out::print);

    }

    private static Set<int[]> prim(int v, List<int[]>[] grafo) {
        Set<int[]> solucion = new HashSet<>();
        boolean[] visitados = new boolean[grafo.length];
        int[] aristafalsa = new int[]{-1, v, 0};
        PriorityQueue<int[]> cola = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        cola.add(aristafalsa);
        while (!cola.isEmpty()) {
            int[] arista = cola.poll();
            System.out.print("arista = " + Arrays.toString(arista) + "\t");
            int destino = arista[1];
            System.out.println("destino = " + destino);
            if (!visitados[destino]) {
                visitados[destino] = true;
                solucion.add(arista);
                for (int[] adyacente : grafo[destino]) {
                    if (!visitados[adyacente[1]]) {
                        cola.add(adyacente);
                    }
                }

            }
        }
        return solucion;
    }
}
