package Ivan.HechasPorMi.Examenes.EJ2015;

import java.util.ArrayList;

public class nTorres {
    public static void main(String[] args) {
        int tam=3;
        int[][] tablero= new int[tam][tam];
        ArrayList<int[][]> soluciones = new ArrayList<>();
        int torres=tam;
        resolver(tablero,soluciones,torres);
        System.out.println("Existen: "+soluciones.size()+" soluciones");
    }

    private static boolean resolver(int[][] tablero, ArrayList<int[][]> soluciones,int torres) {
        if (torres==0){
            int[][] copia =new int[tablero.length][tablero.length];
            for (int i = 0; i < tablero.length; i++) {
                for (int j = 0; j < tablero.length; j++) {
                    copia[i][j]=tablero[i][j];
                }
            }
            soluciones.add(copia);
        }else{
            for (int fila = 0; fila < tablero.length; fila++) {
                for (int colum = 0; colum < tablero.length; colum++) {
                    if(puedometer(tablero,fila,colum)){
                    tablero[fila][colum]=1;
                    resolver(tablero,soluciones,torres-1);
                    }
                    tablero[fila][colum]=0;
                }
            }
        }
        return false;
    }

    private static boolean puedometer(int[][] tablero, int fila, int columna) {
        if ((puedocolumna(tablero,columna))&&(puedofila(tablero,fila))){
            return true;
        }
        return false;
    }

    private static boolean puedofila(int[][] tablero, int fila) {
        for (int i = 0; i <tablero.length ; i++) {
            if (tablero[i][fila]==1){
                return false;
            }
        }return true;
    }

    private static boolean puedocolumna(int[][] tablero, int columna) {
        for (int i = 0; i <tablero.length ; i++) {
            if (tablero[columna][i]==1){
                return false;
            }
        }return true;
    }

}
