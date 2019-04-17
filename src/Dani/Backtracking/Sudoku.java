package Dani.Backtracking;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Sudoku {

    private static final int TAM_SUDOKU = 9;

    public static void main(String[] args) throws IOException{

        //Conjuntos para las filas, columnas y cuadrantes (cuando todos estén llenos, sabremos que hay una solución)
        Set<Integer>[] filas = new Set[TAM_SUDOKU];
        Set<Integer>[] columnas = new Set[TAM_SUDOKU];
        Set<Integer>[][] cuadrantes = new Set[3][3];

        int[][] sudoku = new int[TAM_SUDOKU][TAM_SUDOKU]; //Para guardar el sudoku

        //Inicializar los conjuntos
        for(int i=0; i<TAM_SUDOKU; i++){
            filas[i] = new HashSet<>(TAM_SUDOKU);
            columnas[i] = new HashSet<>(TAM_SUDOKU);
            cuadrantes[i/3][i%3] = new HashSet<>(TAM_SUDOKU);
        }

        //FileReader f = new FileReader("entrada");
        Scanner scan = new Scanner(System.in);

        //Rellenamos
        for(int i=0; i<TAM_SUDOKU; i++){
            for(int j=0; j<TAM_SUDOKU; j++){
                int n = scan.nextInt();
                if(n!=0){
                    filas[i].add(n);
                    columnas[j].add(n);
                    cuadrantes[i/3][j/3].add(n);
                }
                sudoku[i][j] = n;
            }
        }

        //Backtracking
        solucionarSudoku(0,0,filas,columnas,cuadrantes,sudoku);
    }

    public static void solucionarSudoku(int fila, int columna, Set<Integer>[] filas,Set<Integer>[] columnas ,Set<Integer>[][] cuadrantes, int[][] sudoku){
        if(esSolucion(filas)){
            imprimirSudoku(sudoku);

        }else{
            //Si la casilla en la que estamos ya está rellenada (viene dada desde el principio)
            if(sudoku[fila][columna] != 0){

                //Pasamos a la siguiente directamente
                if(columna == TAM_SUDOKU-1){
                    solucionarSudoku(fila+1, 0, filas, columnas, cuadrantes, sudoku);
                }else{
                    solucionarSudoku(fila, columna+1, filas, columnas, cuadrantes, sudoku);
                }

                //La casilla está vacía
            }else{
                //Probamos a rellenar esta casilla
                for(int i=1; i<=TAM_SUDOKU; i++){
                    //Miramos si el número que queremos meter es factible
                    if(esFactible(i, filas[fila], columnas[columna], cuadrantes[fila/3][columna/3])){

                        //Cambiamos el valor de la casilla y añadimos a las colecciones el valor
                        sudoku[fila][columna] = i;
                        filas[fila].add(i);
                        columnas[columna].add(i);
                        cuadrantes[fila/3][columna/3].add(i);

                        //Siguiente casilla
                        if(columna == TAM_SUDOKU-1){
                            solucionarSudoku(fila+1, 0, filas, columnas, cuadrantes, sudoku);
                        }else{
                            solucionarSudoku(fila, columna+1, filas, columnas, cuadrantes, sudoku);
                        }

                        //Quitamos el valor
                        filas[fila].remove(i);
                        columnas[columna].remove(i);
                        cuadrantes[fila/3][columna/3].remove(i);
                    }
                }

                //Restauramos el valor
                sudoku[fila][columna] = 0;
            }
        }

    }

    public static boolean esFactible(int n, Set<Integer> fila,Set<Integer> columna,Set<Integer> cuadrante){
        return (!fila.contains(n) && !columna.contains(n) && !cuadrante.contains(n));
    }

    public static boolean esSolucion(Set<Integer>[] filas){
        boolean relleno = true;
        for(int i=0; i<TAM_SUDOKU; i++){
            if(filas[i].size() < TAM_SUDOKU){
                return false;
            }
        }
        return relleno;
    }

    public static void imprimirSudoku(int[][] sudoku){
        for(int i=0; i<TAM_SUDOKU; i++){
            for (int j = 0; j <TAM_SUDOKU ; j++) {
                System.out.print(sudoku[i][j]+" ");
            }
            System.out.println();
        }
    }
}
