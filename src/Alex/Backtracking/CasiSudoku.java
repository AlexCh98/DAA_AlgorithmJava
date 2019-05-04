package Alex.Backtracking;

import java.util.ArrayList;
import java.util.Scanner;

public class CasiSudoku {

    public static void main(String[] args) {
        int[][] tablero = new int[9][9];
        Scanner lector = new Scanner(System.in);

        for (int i = 0; i < 9; i++) {
            String[] linea = lector.nextLine().split(" ");
            for (int j = 0; j < 9; j++) {
                tablero[i][j] = Integer.parseInt(linea[j]);
            }
        }
        ArrayList<int[][]> soluciones = new ArrayList<>();
        resolverSudoku(tablero, 9, soluciones);
        if (soluciones.size() == 0) {
            System.out.println("imposible");
        } else if (soluciones.size() == 1) {
            imprimirSudoku(soluciones.get(0));
        } else {
            System.out.println(soluciones.size());
            for(int i = 0;i < soluciones.size();i++){
                imprimirSudoku(soluciones.get(i));
            }
            System.out.println("casi sudoku");
        }

    }




    public static boolean puedofila(int[][] sudoku, int n, int fila) {
        for (int i = 0; i < 9; i++)
            if (sudoku[fila][i] == n) {
                return false;
            }
        return true;

    }

    public static boolean puedocolumna(int[][] sudoku, int n, int columna) {
        for (int i = 0; i < 9; i++)
            if (sudoku[i][columna] == n) {
                return false;
            }
        return true;
    }

    public static boolean puedocuadrado(int[][] sudoku, int n, int fila, int columna) {
        int iniciofila = fila - fila % 3;
        int iniciocolumna = columna - columna % 3;
        for (int i = iniciofila; i < iniciofila + 3; i++) {
            for (int j = iniciocolumna; j < iniciocolumna + 3; j++) {
                if (sudoku[i][j] == n) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean puedometer(int[][] sudoku, int n, int fila, int columna) {
        if ((puedofila(sudoku, n, fila)) && (puedocolumna(sudoku, n, columna)) && (puedocuadrado(sudoku, n, fila, columna))) {
            return true;
        }
        return false;
    }

    public static boolean lleno(int[][] sudoku) {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if (sudoku[i][j] == 0) {
                    return false;
                }
        return true;
    }

    private static boolean resolverSudoku(int[][] sudoku, int n, ArrayList<int[][]> soluciones) {
        if (soluciones.size() <= 1) {
            if (lleno(sudoku)) {
                int[][] copia = new int[n][n];
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        copia[i][j] = sudoku[i][j];
                    }
                }
                soluciones.add(copia);
            } else {
                int fila = -1, columna = -1;
                boolean vacio = false;
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
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
                for (int num = 1; num <= n; num++) {
                    if (puedometer(sudoku, num, fila, columna)) {
                        sudoku[fila][columna] = num;
                        if (resolverSudoku(sudoku, n, soluciones)) {
                            return true;
                        }
                        sudoku[fila][columna] = 0;
                    }
                }
            }
        }
        return false;
    }




    public static void imprimirSudoku(int[][] board){
        for (int r = 0; r < 9; r++) {
            for (int d = 0; d < 9; d++) {
                System.out.print(board[r][d]);
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }


}
