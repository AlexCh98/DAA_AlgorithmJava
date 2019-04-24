package Ivan.HechasPorMi.BackTracking;

import java.util.ArrayList;
import java.util.Scanner;

public class SemiSudoku {
    public static void main(String[] args) {
        int[][] sudoku = new int[9][9];
        ArrayList<int [][]> soluciones = new ArrayList<>();
        Scanner scaner = new Scanner(System.in);
        //Rellenamos el sudoku
        for (int i = 0; i < 9 ; i++) {
            for (int j = 0; j < 9 ; j++) {
                sudoku[i][j]= scaner.nextInt();
            }
        }
        solucionar(sudoku,soluciones);
        if(soluciones.size() == 0){
            System.out.println("imposible");
        }else if(soluciones.size() == 1){
            print(soluciones.get(0));
        }else{
            System.out.println("casi sudoku");
        }
    }

    public static boolean lleno(int[][] sudoku) {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if (sudoku[i][j] == 0) {
                return false;
            }
        return true;
    }

    public static boolean solucionar(int[][] sudoku,ArrayList<int [][]> soluciones) {
        if (soluciones.size()<=1) {
            if (lleno(sudoku)) {
                int[][] copia = new int[9][9];
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        copia[i][j] = sudoku[i][j];
                    }
                }
                soluciones.add(copia);
            } else {
                int fila = -1, columna = -1;
                boolean vacio = false;
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (sudoku[i][j] == 0) {
                            fila = i;
                            columna = j;
                            vacio = true;
                            break;
                        }
                    }
                    if (vacio) break;
                }
                if (!vacio) return true;
                for (int num = 1; num <= 9; num++) {
                    if (puedometer(sudoku, num, fila, columna)) {
                        sudoku[fila][columna] = num;
                        if (solucionar(sudoku, soluciones)) return true;
                        sudoku[fila][columna] = 0;
                    }
                }

            }
        }return false;
    }

    public static boolean puedometer(int[][] sudoku, int n, int fila, int columna){
        if ((puedofila(sudoku,n,fila)) && (puedocolumna(sudoku,n,columna)) && (puedocuadrado(sudoku,n,fila,columna))){
            return true;
        }return false;
    }

    public static void print(int[][] board){
        for (int r = 0; r < 9; r++) {
            for (int d = 0; d < 9; d++) {
                System.out.print(board[r][d]);
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }
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
}


