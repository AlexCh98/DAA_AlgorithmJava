package Jose;

import java.util.Arrays;
import java.util.Random;

public class Sudoku {
    public static void main(String [] args){
        int n = 9;
        int numero_pistas = 23;
        int [][] sudoku = inicializarTablero(9, numero_pistas);
        System.out.println("Antes de rellenar: ");
        imprimirSudoku(sudoku, n);
        resolverSudoku(sudoku, n);
        System.out.println("Sudoku relleno: ");
        imprimirSudoku(sudoku, n);
        System.out.println("Bien resuelto: " + (sudoku_correcto(sudoku, n) ? "SI" : "NO"));
    }

    private static boolean resolverSudoku(int [][]  sudoku, int n){
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
            if(puedoMeterNumero(num, fila, columna, n, sudoku)){
                sudoku[fila][columna] = num;
                if(resolverSudoku(sudoku, n)) return true;
                sudoku[fila][columna] = 0;
            }
        }
        return false;
    }

    private static boolean contenidoEnFila(int numero, int fila, int n, int[][] sudoku) {//Comprueb fila
        for (int i = 0; i < n; i++) {
            if (sudoku[fila][i] == numero) {
                return true;
            }
        }
        return false;
    }

    private static boolean contenidoEnColumna(int numero, int columna, int n, int[][] sudoku) {//Comprurba columna
        for (int i = 0; i < n; i++) {
            if (sudoku[i][columna] == numero) {
                return true;
            }
        }
        return false;
    }

    private static boolean contenidoEnCaja(int numero, int fila, int columna, int n, int[][] sudoku) {//Comprueba caja
        int c = columna - (columna % (int) Math.sqrt(n));
        int f = fila - (fila % (int) Math.sqrt(n));
        for (int i = f; i < f + Math.round(Math.sqrt(n)); i++) {
            for (int j = c; j < c + Math.round(Math.sqrt(n)); j++) {
                if (sudoku[i][j] == numero) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean puedoMeterNumero(int numero, int fila, int columna, int n, int[][] sudoku) {//Comprueba si el numero es valido
        return !(contenidoEnFila(numero, fila, n, sudoku) || contenidoEnColumna(numero, columna, n, sudoku) || contenidoEnCaja(numero, fila, columna, n, sudoku));
    }

    private static int[][] inicializarTablero(int n, int numero_pistas) {//Inicializa un tablero aleatorio con el numero de pistas siguiendo las reglas
        int posiciones_rellenadas = 0;
        Random random = new Random(System.currentTimeMillis());
        int[][] sudoku = new int[n][n];
        do {
            int fila, columna;
            do {
                fila = random.nextInt(n);
                columna = random.nextInt(n);
            }while(sudoku[fila][columna] != 0);
            boolean asignado = false;
            while (!asignado) {
                int numero = random.nextInt(n) + 1;
                if ((asignado = puedoMeterNumero(numero, fila, columna, n, sudoku))) {
                    sudoku[fila][columna] = numero;
                }
            }
            posiciones_rellenadas++;
        } while (posiciones_rellenadas < numero_pistas);
        return sudoku;
    }

    private static void imprimirSudoku(int[][] sudoku, int n) {//Imprimir sudoku formateado
        for (int i = 0; i < n; i++) {
            if (i % Math.round(Math.sqrt(n)) == 0 && i != 0) {
                for (int j = 0; j < n * 3 + (Math.round(Math.sqrt(n)) - 1); j++) {
                    System.out.print("-");
                }
                System.out.println();
            }
            for (int j = 0; j < n; j++) {
                if (j % Math.round(Math.sqrt(n)) == 0 && j != 0) System.out.print("|");
                System.out.print(" " + (sudoku[i][j] == 0 ? "*": sudoku[i][j]) + " ");
            }
            System.out.println();
        }
    }

    private static boolean sudoku_correcto(int[][] sudoku, int n) {//Comprobar si el sudoku esta bien resuelto
        boolean resultado = true;
        int i = 0;
        while (resultado && i < n) {
            int[] fila = new int[n];
            int[] columna = new int[n];
            int[] region = new int[n];
            for (int j = 0; j < n; j++) {
                fila[j] = sudoku[i][j];
                columna[j] = sudoku[j][i];
                region[j] = sudoku[(i / 3) * 3 + j / 3][i * 3 % 9 + j % 3];
            }
            resultado = vector_correcto(fila, n) && vector_correcto(columna, n) && vector_correcto(region, n);

            i++;
        }
        return resultado;
    }

    private static boolean vector_correcto(int[] vector, int n) {//Comrueba si un numero esta repetido en el vector
        boolean resultado = true;
        int i = 1;
        Arrays.sort(vector);
        while (resultado && i < n) {
            resultado = vector[i - 1] != vector[i];
            i++;
        }
        return resultado;
    }

}
