package Backtracking.Juez;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Sudoku {

    public static void main(String[] args) throws IOException {
        Set<Integer>[] filas = new Set[9];
        Set<Integer>[] columnas = new Set[9];
        Set<Integer>[][] cuadrantes = new Set[3][3];

        int[][] sudoku = new int[9][9];

        for (int i = 0; i < 9; i++) {
            filas[i] = new HashSet<>(9);
            columnas[i] = new HashSet<>(9);
            cuadrantes[i / 3][i % 3] = new HashSet<>(9);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            String[] linea = reader.readLine().split(" ");

            for (int j = 0; j < 9; j++) {
                int n = Integer.parseInt(linea[j]);
                if (n != 0) {
                    filas[i].add(n);
                    columnas[j].add(n);
                    cuadrantes[i / 3][j / 3].add(n);
                }
                sudoku[i][j] = n;
            }
        }

        if (rellenarSudoku(sudoku, filas, columnas, cuadrantes)) {
            for (int i = 0; i < sudoku.length; i++) {
                for (int j = 0; j < sudoku[i].length; j++) {
                    System.out.print(sudoku[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    private static boolean rellenarSudoku(int[][] sudoku, Set<Integer>[] filas, Set<Integer>[] columnas, Set<Integer>[][] cajas) {
        int fila = -1, columna = -1;
        boolean vacio = false;
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku[i].length; j++) {
                if (sudoku[i][j] == 0) {
                    fila = i;
                    columna = j;
                    vacio = true;
                    break;
                }
                if (vacio) break;
            }
        }
        if (!vacio) return true;
        for (int num = 1; num <= 9; num++) {
            if (puedoMeter(num, fila, columna, filas, columnas, cajas)) {
                sudoku[fila][columna] = num;
                filas[fila].add(num);
                columnas[columna].add(num);
                cajas[fila / 3][columna / 3].add(num);
                if (rellenarSudoku(sudoku, filas, columnas, cajas)) return true;
                cajas[fila/3][columna/3].remove(num);
                columnas[columna].remove(num);
                filas[fila].remove(num);
                sudoku[fila][columna] = 0;
            }
        }
        return false;
    }

    private static boolean puedoMeter(int num, int fila, int columna, Set<Integer>[] filas, Set<Integer>[] columnas, Set<Integer>[][] cajas) {
        return !filas[fila].contains(num) && !columnas[columna].contains(num) && !cajas[fila / 3][columna / 3].contains(num);
    }
}
