package VoracesGrafos.Juez;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class CalleCara {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int ciudades;
        while ((ciudades = Integer.parseInt(reader.readLine())) != 0) {
            int conexiones = Integer.parseInt(reader.readLine());
            List<int[]>[] mapa = new List[ciudades];
            for (int i = 0; i < mapa.length; i++) {
                mapa[i] = new ArrayList<>(ciudades);
            }
            for (int i = 0; i < conexiones; i++) {
                String[] linea = reader.readLine().split(" ");
                int origen = Integer.parseInt(linea[0]);
                int destino = Integer.parseInt(linea[1]);
                int peso = Integer.parseInt(linea[2]);
                mapa[origen].add(new int[]{destino, peso});
                mapa[destino].add(new int[]{origen, peso});

            }
            primNoConexo(mapa);
            System.out.println("---");
        }
    }


    private static void primNoConexo(List<int[]>[] grafo) {
        int islas = 0, carreteras = 0, coste = 0;
        boolean[] visitados = new boolean[grafo.length];
        for (int v = 0; v < grafo.length; v++) {
            if (!visitados[v]) {
                islas++;
                visitados[v] = true;
                PriorityQueue<int[]> cola = new PriorityQueue<>((o1, o2) -> -Integer.compare(o1[1], o2[1]));
                grafo[v].stream().filter(adyacente -> !visitados[adyacente[0]]).forEach(cola::add);
                while (!cola.isEmpty()) {
                    int[] arista = cola.poll();
                    int destino = arista[0];
                    int peso = arista[1];
                    if (!visitados[destino]) {
                        visitados[destino] = true;
                        carreteras++;
                        coste += peso;
                        grafo[destino].stream().filter(adyacente -> !visitados[adyacente[0]]).forEach(cola::add);
                    }
                }
            }
        }
        System.out.println(islas + " " + carreteras + " " + coste);
    }
}
/*
4
6
2 0 28
0 1 35
1 2 89
0 3 72
1 2 16
0 1 43
3
1
2 0 748
0

 */
