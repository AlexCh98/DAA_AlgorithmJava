package Ivan.HechasPorMi.BackTracking;

public class Sudoku {
    public static void main(String[] args) {
        int[][] sudoku = new int[][]{
                {3, 0, 6, 5, 0, 8, 4, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0}};
        solucionar(sudoku);
        System.out.println("Sudoku relleno: ");
        imprimirSudoku(sudoku);
    }

    public static boolean lleno(int[][] sudoku) {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if (sudoku[i][j] == 0) {
                return false;
            }
        return true;
    }

    public static boolean solucionar(int[][] sudoku) {
        int fila = -1, columna = -1;
        boolean vacio = false;
        for(int  i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
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
        for(int num = 1; num <= 9; num++){
            if(puedometer(sudoku,num, fila, columna)){
                sudoku[fila][columna] = num;
                if(solucionar(sudoku)) return true;
                sudoku[fila][columna] = 0;
            }
        }
        return false;
    }
    public static boolean puedometer(int[][] sudoku, int n, int fila, int columna){
        if ((puedofila(sudoku,n,fila)) && (puedocolumna(sudoku,n,columna)) && (puedocuadrado(sudoku,n,fila,columna))){
            return true;
        }return false;
    }

    public static void imprimirSudoku(int[][] sudoku) {
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


