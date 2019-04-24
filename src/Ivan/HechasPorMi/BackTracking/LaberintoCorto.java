package Ivan.HechasPorMi.BackTracking;

import java.util.ArrayList;
import java.util.Scanner;

public class LaberintoCorto {

    public static void main(String[] args) {
        Scanner scaner = new Scanner(System.in);
        int tam =scaner.nextInt();
        long[][] laberinto = new long[tam][tam];
        //Rellenamos el tablero
        for (int i = 0; i < tam ; i++) {
            for (int j = 0; j < tam ; j++) {
                laberinto[i][j]= scaner.nextInt();
            }
        }

            /*int[][] laberinto = new int[][] {
                    { 0,  0, -1,  0,  0,  0,  0,  0,  0,  0},
                    {-1,  0, -1,  -1,  0, -1, -1,  0, -1,  0},
                    { 0,  0,  0,  0,  0,  0, -1,  0, -1,  0},
                    { 0, -1,  0,  0, -1, -1, -1,  0,  0,  0},
                    { 0,  0, -1, -1,  0,  0,  0, -1,  0,  0},
                    { 0,  0,  0,  0,  0, -1,  0, -1,  0,  0},
                    {-1,  0,  0, -1, -1,  0,  0, -1,  0,  0},
                    { 0, -1, -1,  0,  0,  0,  0,  0, -1,  0},
                    {-1,  0,  0,  0,  0, -1,  0, -1, -1,  0},
                    { 0,  0, -1,  0, -1, -1,  0,  0,  0,  0}
            };*/
        ArrayList<long [][]> soluciones=new ArrayList<>();
        resolverlaberinto(tam,laberinto,0,0,1,soluciones);
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
        //for (int i = 0; i <soluciones.size(); i++) {
            //printLabyrinth(soluciones.get(i));
        //}
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
        if (esvalida(tablero, r - 1, c)) {
            resolverlaberinto(tam,tablero, r - 1, c, k+1,soluciones);
            //return true;//Movemos izq
        }
        if (esvalida(tablero, r + 1, c)) {
            resolverlaberinto(tam,tablero, r + 1, c, k+1,soluciones);
            //return true;//Movemos der
        }
        if (esvalida(tablero, r, c - 1)) {
            resolverlaberinto(tam,tablero, r, c - 1, k+1,soluciones);
            //return true;//Movemos abajo
        }
        if (esvalida(tablero, r, c + 1)) {
            resolverlaberinto(tam,tablero, r, c + 1, k+1,soluciones);
            //return true;//Movemos arriba
        }
    }tablero[r][c] = 0;
}


    public static boolean esvalida (long [][] tablero,int r,int c){
        if (r >= 0 && r<tablero.length && c>=0 && c<tablero.length ){//Si no es cuadrado compruebo con tablero[0].lenght
            return (tablero[r][c] == 0);
        }else {return false;}
    }
    public static boolean terminado (long [][] tablero,int r, int c){
        if (r == tablero.length - 1 && c == tablero.length - 1)return true;
        return false;
    }
    /*public static void printLabyrinth(int[][] board) {
        for (int j = 0; j < board.length; j++) {
            System.out.print("----");
        }
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    System.out.print("|  |");
                } else if (board[i][j] < 0) {
                    System.out.print("|XX|");
                } else {
                    System.out.format("|%2d|", board[i][j]);
                }
            }
            System.out.println();
            for (int j = 0; j < board[i].length; j++) {
                System.out.print("----");
            }
            System.out.println();
        }
    }*/

}

