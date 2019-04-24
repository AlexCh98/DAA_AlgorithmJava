package Alex.EjerciciosProblemasClase.ClasePracticaVoracesGrafos_BackTracking;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class LaberintoMaxCorto {
    private static int sol;
    public static void main(String[] args){
        Scanner lector = new Scanner(System.in);
        int tamano = Integer.parseInt(lector.nextLine());
        int[][] tablero = new int[tamano][tamano];
        for(int i = 0; i<tamano;i++){
            String[] linea = lector.nextLine().split(" ");
            for(int j = 0; j<tamano; j++){
                tablero[i][j] = Integer.parseInt(linea[j]);
            }
        }
        TreeSet<Integer> arraySol = new TreeSet<>();

        salida(tablero,0,1,0, arraySol);
        if(arraySol.isEmpty()){
            System.out.println("SIN SOLUCION");
        }else{
            System.out.println(arraySol.first());
        }
    }

    /*0 LIBRE
      -1 PARED
      k RECORRIDO*/
    /* Si devuelve arraySol con algún elemento sera true (hay solucion)
    *  Si devuelve arraySol vacio es false (no hay salida)
    *  */
    private static int salida(int[][] tablero, int fila,int k, int columna, TreeSet<Integer> arraySol){
        if(fila == tablero.length-1 &&  columna == tablero.length-1) {
            //System.out.println(k);
            //System.out.println(k);
            arraySol.add(k);
            return k;
        }
        if(fila < tablero.length && fila >= 0 && columna < tablero.length && columna >= 0) {
            if (tablero[fila][columna] == -1 || tablero[fila][columna] == 8) {
                return -2;
            }
            int masCorto = -2;
            tablero[fila][columna] = 8;
            int camino = salida(tablero, fila + 1, k + 1, columna,arraySol);
            if (camino != -2 && camino < masCorto) {
                masCorto = camino;
            }
            camino = salida(tablero, fila - 1, k + 1, columna,arraySol);
            if (camino != -2 && camino < masCorto) {
                masCorto = camino;
            }
            camino = salida(tablero, fila, k + 1, columna + 1,arraySol);
            if (camino != -2 && camino < masCorto) {
                masCorto = camino;
            }
            camino = salida(tablero, fila, k + 1, columna - 1,arraySol);
            if (camino != -2 && camino < masCorto) {
                masCorto = camino;
            }
            tablero[fila][columna] = 0;
            if (masCorto == -2) return -2;
            return masCorto + 1;
        }else{
            return -2;
        }
    }



}
