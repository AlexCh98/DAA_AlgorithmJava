package com.company;

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
        int numero_actividades = Integer.parseInt(linea[0]);
        int numero_caminos = Integer.parseInt(linea[1]);
        List<int[]>[] plano = new List[numero_actividades];
        for (int j = 0; j < numero_actividades; j++) {
            plano[j] = new ArrayList<>(numero_actividades);
        }
        for (int i = 0; i < numero_caminos; i++) {
            linea = reader.readLine().split(" ");
            int origen = Integer.parseInt(linea[0]) - 1;
            int destino = Integer.parseInt(linea[1]) - 1;
            int peso = Integer.parseInt(linea[2]);
            plano[origen].add(new int[]{destino, peso});
            plano[destino].add(new int[]{origen, peso});
        }
        System.out.println(prim(plano));
    }


    public static int prim(List<int[]>[] grafo) {
        int coste = 0;
        boolean[] visitados = new boolean[grafo.length];
        visitados[0] = true;
        PriorityQueue<int[]> cola = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        cola.addAll(grafo[0]);
        while (!cola.isEmpty()) {
            int[] mejorArista = cola.poll();
            if (!visitados[mejorArista[0]]) {
                visitados[mejorArista[0]] = true;
                coste += mejorArista[1];
                cola.addAll(grafo[mejorArista[0]]);
            }
        }
        return coste;
    }

}
