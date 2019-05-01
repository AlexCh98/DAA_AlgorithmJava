package Alex.EjerciciosProblemasClase.ClasePracticaVoracesGrafos_BackTracking.Repetidos;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Laberinto {

    public static void main(String[] args){
        Scanner scaner = new Scanner(System.in);
        int tam =scaner.nextInt();
        long[][] laberinto = new long[tam][tam];
        //Rellenamos el tablero
        for (int i = 0; i < tam ; i++) {
            for (int j = 0; j < tam ; j++) {
                laberinto[i][j]= scaner.nextInt();
            }
        }
        TreeSet<Integer> sol = new TreeSet<>();
        ArrayList<long [][]> soluciones=new ArrayList<>();

        laberinto(laberinto,0,0,1,sol);
        resolverlaberinto(tam,laberinto,0,0,1,soluciones);
       /* if(soluciones.isEmpty()){
            System.out.println("SIN SOLUCION");
        }else{
            System.out.println(soluciones.first());
        }*/
        if (soluciones.size()==0){
            System.out.println("SIN SOLUCION");
        }else{
            long contador=(soluciones.get(0)[tam-1][tam-1]);
            for (int i = 1; i <soluciones.size(); i++) {
                if((soluciones.get(i))[tam-1][tam-1]<=contador){
                    contador =(soluciones.get(i))[tam-1][tam-1];
                }
            }
            System.out.println(contador);
        }
    }


    public static void laberinto(long[][] tablero, int fila, int columna, int k, TreeSet arraySol){
        tablero[fila][columna] = k;
        if(fila == tablero.length-1 && columna == tablero.length-1){
            arraySol.add(k);
        }else{
            if(esValida(tablero,fila+1,columna)){
                laberinto(tablero,fila+1,columna,k+1,arraySol);
            }
            if(esValida(tablero,fila-1,columna)){
                laberinto(tablero,fila-1,columna,k+1,arraySol);
            }
            if(esValida(tablero,fila,columna+1)){
                laberinto(tablero,fila,columna+1,k+1,arraySol);
            }
            if(esValida(tablero,fila,columna-1)){
                laberinto(tablero,fila,columna-1,k+1,arraySol);
            }

        }
        tablero[fila][columna] = 0;
    }
    public static boolean terminado (long [][] tablero,int r, int c){
        if (r == tablero.length - 1 && c == tablero.length - 1)return true;
        return false;
    }
    private static boolean esValida(long[][] tablero, int fila, int columna) {
        if(fila >=0 && fila < tablero.length && columna >= 0 && columna < tablero.length){
            return true;
        }else {
            return false;
        }
    }
    public static void resolverlaberinto (int tam ,long [][] tablero, int r, int c, int k, ArrayList<long [][]> soluciones) {
        tablero[r][c] = k;
        if (terminado(tablero, r, c)) {//Es solucion
            long [][] copia=new long[tam][tam];
            for (int i = 0; i < tam; i++) {
                for (int j = 0; j < tam; j++) {
                    copia[i][j] = tablero[i][j];
                }
            }
            soluciones.add(copia);
        } else {//Tenemos que seguir moviendonos
            if (esValida(tablero, r - 1, c)) {
                resolverlaberinto(tam,tablero, r - 1, c, k+1,soluciones);
                //return true;//Movemos izq
            }
            if (esValida(tablero, r + 1, c)) {
                resolverlaberinto(tam,tablero, r + 1, c, k+1,soluciones);
                //return true;//Movemos der
            }
            if (esValida(tablero, r, c - 1)) {
                resolverlaberinto(tam,tablero, r, c - 1, k+1,soluciones);
                //return true;//Movemos abajo
            }
            if (esValida(tablero, r, c + 1)) {
                resolverlaberinto(tam,tablero, r, c + 1, k+1,soluciones);
                //return true;//Movemos arriba
            }
        }
        tablero[r][c] = 0;
    }


}
