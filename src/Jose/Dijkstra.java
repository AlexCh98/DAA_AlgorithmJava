package Jose;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Dijkstra {
    public static void main(String[] Args) {
        int n = 5;
        int[][] grafo = new int[n + 1][n + 1];
        Scanner reader = new Scanner(System.in);
        for (int i = 0; i < 7; i++) {
            String[] linea = reader.nextLine().split(" ");
            int origen = Integer.parseInt(linea[0]);
            int destino = Integer.parseInt(linea[1]);
            int peso = Integer.parseInt(linea[2]);
            System.out.println("origen = " + origen);
            System.out.println("destino = " + destino);
            System.out.println("peso = " + peso);
            grafo[origen][destino] = peso;
        }
        for (int i = 1; i < n + 1; i++) {
            System.out.println(Arrays.toString(Arrays.copyOfRange(grafo[i], 1, grafo.length)));
        }
    }

    public static List<int[]> dijstra(int[][] grafo) {
        List<int[]> salida = new ArrayList<>(grafo.length - 1);
        for (int i = 1; i <= grafo.length - 1; i++) {
            System.out.println("nodo" + i);
            salida.add(dijstra(grafo, i));
        }
        return salida;
    }

    private static int[] dijstra(int[][] grafo, int nodo) {
        int[] distancias = new int[grafo.length - 1];
        boolean[] seleccionados = new boolean[grafo.length - 1];
        for (int i = 1; i <= grafo.length - 1; i++) {
            distancias[i] = Integer.MAX_VALUE;
            seleccionados[i] = false;
        }
        distancias[nodo] = 0;
        seleccionados[nodo] = true;
        return distancias;
    }
}
