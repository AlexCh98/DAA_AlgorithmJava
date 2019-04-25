package Ivan.HechasPorMi.BackTracking;

import java.util.ArrayList;
import java.util.Scanner;

public class SudokuV2 {
    private static int TAM=9;

    public static void main(String[] args) {
        int [][] sudoku= new int[TAM][TAM];
        Scanner scaner = new Scanner(System.in);
        ArrayList<int[][]> soluciones = new ArrayList<>();
        for (int i = 0; i <TAM ; i++) {
            for (int j = 0; j <TAM; j++) {
                sudoku[i][j]=scaner.nextInt();//Guardo en las x filas en las y columnas
            }
        }
        resolver_sudoku(sudoku,soluciones);
        for (int i = 0; i < soluciones.size(); i++) {
            print(soluciones.get(i));
        }
    }
    public static boolean resolver_sudoku (int [][] tablero, ArrayList<int[][]> listaSoluciones) {
        if (listaSoluciones.size()<3){
        if (lleno(tablero)) {//Miramos si hemos terminado para añadir la solucion
            int[][] copia = new int[TAM][TAM];
            for (int i = 0; i < TAM; i++) {
                for (int j = 0; j < TAM; j++) {
                    copia[i][j] = tablero[i][j];
                }
            }
            listaSoluciones.add(copia);
        } else {
            int fila = -1;
            int columna = -1;
            boolean hueco = false;
            for (int i = 0; i < TAM; i++) {
                for (int j = 0; j < TAM; j++) {
                    if (tablero[i][j] == 0) {
                        fila = i;
                        columna = j;
                        hueco = true;
                        break;
                    }
                }
                if (hueco) break;
            }
            if (!hueco) return true;
            for (int num = 1; num <= TAM; num++) {
                if (puedo_meter(tablero, fila, columna, num)) {
                    tablero[fila][columna] = num;
                    if (resolver_sudoku(tablero,listaSoluciones)) return true;
                    tablero[fila][columna] = 0;
                    }
                }
            }
        }return false;
    }

    public static void print (int [][] tablero){
        for (int i = 0; i < TAM ; i++) {
            for (int j = 0; j <TAM ; j++) {
                System.out.print(tablero[i][j]);
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }
    public static boolean puedo_meter(int [][] tablero,int fila, int columna,int numero){
        if(puedo_fila(tablero,fila,numero)&&puedo_columna(tablero,columna,numero)&&puedo_sector(tablero,fila,columna,numero))return true;
        return false;
    }
    public static boolean puedo_fila (int[][]tablero,int fila,int numero){
        for (int i = 0; i <TAM ; i++) {
            if (tablero[fila][i]==numero){return false;}
        }return true;
    }
    public static boolean puedo_columna (int[][]tablero,int columna,int numero){
        for (int i = 0; i <TAM ; i++) {
            if (tablero[i][columna]==numero){return false;}
        }return true;
    }
    public static boolean puedo_sector(int[][] tablero,int fila, int columna,int numero){
        int iniFila=fila-fila%3;
        int iniColumna=columna-columna%3;
        for (int i = 0; i <iniFila+3 ; i++) {
            for (int j = 0; j <iniColumna+3 ; j++) {
                if (tablero[i][j]==numero){
                    return false;
                }
            }

        }
        return true;
    }
    public static boolean lleno (int[][]tablero){
        for (int i = 0; i < TAM; i++) {
            for (int j = 0; j < TAM; j++){
                if (tablero[i][j] == 0) {
                    return false;
                }

            }
        }return true;
    }

}

