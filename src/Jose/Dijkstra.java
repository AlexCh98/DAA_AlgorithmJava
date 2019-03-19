package Jose;

import java.util.*;

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

        for(int i = 0; i < n + 1; i++){
            for(int j = 0; j < n +1 ; j++){
                if(grafo[i][j] == 0){
                    grafo[i][j] = Integer.MAX_VALUE/2;
                }
            }
        }
        for (int i = 1; i < n + 1; i++) {
            System.out.println(Arrays.toString(Arrays.copyOfRange(grafo[i], 1, grafo.length)));
        }
        List<int[]> solucion = dijstra(grafo);
        int  i = 1;
        for(int[] distancias : solucion){
            System.out.println("Vector distancias del nodo "+i + " : " + Arrays.toString(Arrays.copyOfRange(distancias, 1, distancias.length)));
            i++;
        }
    }

    public static List<int[]> dijstra(int[][] grafo) {
        List<int[]> salida = new ArrayList<>(grafo.length - 1);
        for (int i = 1; i <= grafo.length - 1; i++) {
            //System.out.println("nodo" + i);
            salida.add(dijstra(grafo, i));
        }
        return salida;
    }

    private static int[] dijstra(int[][] grafo, int nodo) {
        int[] distancias = new int[grafo.length];
        Set<Integer> no_seleccionados = new HashSet<>(grafo.length-1);
        for (int i = 1; i <= grafo.length - 1; i++) {
            no_seleccionados.add(i);
            distancias[i] = grafo[nodo][i];
        }
        distancias[nodo] = 0;
        no_seleccionados.remove(nodo);
        for(int i = 0; i < grafo.length-2; i++){
            int mejor_nodo = mejorNodo(no_seleccionados, distancias);
            //System.out.println("mejor_nodo = " + mejor_nodo + " distancias " + Arrays.toString(distancias));
            //System.out.println("no_seleccionados = " + no_seleccionados);
            no_seleccionados.remove(mejor_nodo);
            for(int nodo_no_seleccionado: no_seleccionados){
                //System.out.print("Nodo no seleccionado " + nodo_no_seleccionado);
                //System.out.print(" Distancia[nodo_no_seleccionado] " + distancias[nodo_no_seleccionado]);
                //System.out.println(" Distancias[mejor_nodo] + grafo[mejor_nodo][nodo_no_seleccionado] " + (distancias[mejor_nodo] + grafo[mejor_nodo][nodo_no_seleccionado]));
                distancias[nodo_no_seleccionado] = Math.min(distancias[nodo_no_seleccionado],
                        distancias[mejor_nodo] + grafo[mejor_nodo][nodo_no_seleccionado]);
            }
        }
        return distancias;
    }

    private static int mejorNodo(Set<Integer> no_seleccionados, int[] distancias) {
        //System.out.println("DENTRO DE MEJOR NODO");
        int mejor_nodo = 0;
        int mejor_distancia = Integer.MAX_VALUE;
        for(int nodo: no_seleccionados){
            if(distancias[nodo] < mejor_distancia){
                mejor_nodo = nodo;
                mejor_distancia = distancias[nodo];
            }
            //System.out.println("Mejor nodo " + mejor_nodo +" Mejor distancia " + mejor_distancia);
        }
        //System.out.println("SALGO DE MEJOR NODO");
        return mejor_nodo;
    }
}
