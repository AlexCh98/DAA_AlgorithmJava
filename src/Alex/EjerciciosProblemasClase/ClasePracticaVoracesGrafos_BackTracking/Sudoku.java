package Alex.EjerciciosProblemasClase.ClasePracticaVoracesGrafos_BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Sudoku {
    private static int sol = 0;
    public static void main(String[] args){
        int[][] tablero = new int[9][9];
        Scanner lector = new Scanner(System.in);

        for(int i = 0; i < 9; i++){
            String[] linea = lector.nextLine().split(" ");
            for(int j = 0; j < 9; j++){
                tablero[i][j] = Integer.parseInt(linea[j]);
            }
        }
        //imprimirSudoku(tablero);
        resolverSudoku(tablero,9);
        //System.out.println("NUMERO SOL:"+sol);
        imprimirSudoku(tablero);
        //System.out.println("Bien resuelto: " + (sudoku_correcto(tablero,9) ? "SI" : "NO"));
    }

    /*public static boolean perteneceAFila(int[][] sudoku, int fila, int numero){
        for(int i=0;i<9;i++){
            if(sudoku[fila][i]==numero)
                return true;
        }
        return false;
    }
    public static boolean perteneceAColumna(int[][] sudoku, int columna, int numero){
        for(int i=0;i<9;i++){
            if(sudoku[columna][i]==numero)
                return true;
        }
        return false;
    }
    public static boolean perteneceACuadrado(int[][] sudoku, int fila, int columna, int numero){
        int f = fila - fila%3;
        int c = columna - columna&3;
        System.out.println("Fila Cuadrado Inicial: "+f);
        System.out.println("Columna Cuadrado Inicial: "+c);
        for(int i=f;i<f+3;i++){
            for (int j=c;j<c+3;j++){
                if(sudoku[i][j]==numero)
                    return true;
            }
        }
        return false;
    }*/
    public static boolean puedofila(int[][] sudoku, int n, int fila) {
        for (int i = 0; i < 9; i++)
            if (sudoku[fila][i] == n) {
                return false;
            }
        return true;

    }

    public static boolean puedocolumna(int[][] sudoku, int n, int columna){
        for (int i = 0; i < 9; i++)
            if (sudoku[i][columna] == n) {
                return false;
            }
        return true;
    }

    public static boolean puedocuadrado(int[][] sudoku, int n, int fila, int columna){
        int iniciofila = fila - fila % 3;
        int iniciocolumna = columna - columna % 3;
        for (int i = iniciofila; i < iniciofila + 3; i++){
            for (int j = iniciocolumna; j < iniciocolumna + 3; j++){
                if (sudoku[i][j] == n){
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean puedometer(int[][] sudoku, int n, int fila, int columna){
        if ((puedofila(sudoku,n,fila)) && (puedocolumna(sudoku,n,columna)) && (puedocuadrado(sudoku,n,fila,columna))){
            return true;
        }return false;
    }

    private static boolean resolverSudoku(int[][] sudoku, int n){
        int fila = -1, columna = -1;
        boolean vacio = false;
        for(int  i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(sudoku[i][j] == 0){
                    fila = i;
                    columna = j;
                    vacio = true;
                    break;
                }
            }
            if(vacio) break;
        }
        if(!vacio) return true;
        for(int num = 1; num <= n; num++){
            if(puedometer(sudoku,num,fila, columna)){
                sudoku[fila][columna] = num;
                if(resolverSudoku(sudoku, n)){
                    return true;
                }
                sudoku[fila][columna] = 0;
            }
        }
        return false;
    }
    /*private static boolean puedoMeterNumero(int fila, int columna, int[][] tablero, int valor) {
        return (!perteneceAFila(tablero,fila,valor) && !perteneceAColumna(tablero,columna,valor) && !perteneceACuadrado(tablero,fila,columna,valor));
    }*/

    public static void imprimirSudoku(int[][] board){
        for (int r = 0; r < 9; r++) {
            for (int d = 0; d < 9; d++) {
                System.out.print(board[r][d]);
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }
    /*private static void imprimirSudoku(int[][] sudoku) {//Imprimir sudoku formateado
        for (int i = 0; i < 9; i++) {
            if (i % Math.round(Math.sqrt(9)) == 0 && i != 0) {
                for (int j = 0; j < 9 * 3 + (Math.round(Math.sqrt(9)) - 1); j++) {
                    System.out.print("-");
                }
                System.out.println();
            }
            for (int j = 0; j < 9; j++) {
                if (j % Math.round(Math.sqrt(9)) == 0 && j != 0) System.out.print("|");
                System.out.print(" " + (sudoku[i][j] == 0 ? "*": sudoku[i][j]) + " ");
            }
            System.out.println();
        }
    }*/

}
