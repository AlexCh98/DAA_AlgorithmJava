package Alex.EjerciciosProblemasClase.ClasePracticaVoracesGrafos_BackTracking.Repetidos;


import java.util.ArrayList;
import java.util.Scanner;


public class CasiSudoku {
    public static final int TAM = 9;

    public static void main(String[] args){
        int[][] tablero = new int[9][9];
        Scanner lector = new Scanner(System.in);

        for (int i = 0; i < 9; i++) {
            String[] linea = lector.nextLine().split(" ");
            for (int j = 0; j < 9; j++) {
                tablero[i][j] = Integer.parseInt(linea[j]);
            }
        }
        ArrayList<int[][]> arraySol = new ArrayList<>();
        casiSudoku(tablero,arraySol);
        //System.out.println("PATATA: "+arraySol.size());
        if(arraySol.size() == 0){
            System.out.println("imposible");
        }else if(arraySol.size() ==1){
            imprimirSudoku(arraySol.get(0));
        }else{
            System.out.println("casi sudoku");
        }
    }

    private static void imprimirSudoku(int[][] board) {
        for (int r = 0; r < 9; r++) {
            for (int d = 0; d < 9; d++) {
                System.out.print(board[r][d]);
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }

    public static boolean lleno(int[][] tablero){
        for(int i = 0; i< TAM; i++){
            for(int j = 0;j < TAM; j++){
                if(tablero[i][j]==0){
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean CasiSudoku(int[][] tablero, ArrayList arraySol){
        if(arraySol.size()<2) {
            if (lleno(tablero)) {
                int[][] copia = new int[TAM][TAM];
                for (int i = 0; i < TAM; i++) {
                    for (int j = 0; j < TAM; j++) {
                        copia[i][j] = tablero[i][j];
                    }
                }
                arraySol.add(copia);
            } else {
                int fila = -1;
                int columna = -1;
                boolean vacio = false;
                for (int i = 0; i < TAM; i++) {
                    for (int j = 0; j < TAM; j++) {
                        if(tablero[i][j] == 0){
                            vacio = true;
                            fila = i;
                            columna = j;
                            break;
                        }
                    }
                    if(vacio) break;
                }
                if(!vacio) return true;
                for(int num = 1; num <=9; num ++){
                    if(puedoMeter(tablero,fila,columna,num)){
                        tablero[fila][columna] = num;
                        if(CasiSudoku(tablero,arraySol))
                            return true;
                        tablero[fila][columna] = 0;
                    }
                }
            }

        }
        return false;
    }

    //NO FUNCIONA
    public static void casiSudoku(int[][] tablero, ArrayList arraySol){
        if(arraySol.size()<2) {
            if (lleno(tablero)) {
                int[][] copia = new int[TAM][TAM];
                for (int i = 0; i < TAM; i++) {
                    for (int j = 0; j < TAM; j++) {
                        copia[i][j] = tablero[i][j];
                    }
                }
                arraySol.add(copia);
            } else {
                for (int i = 0; i < TAM; i++) {
                    for (int j = 0; j < TAM; j++) {
                        if(tablero[i][j] == 0){
                            for(int num = 1; num <=9; num ++){
                                if(puedoMeter(tablero,i,j,num)){
                                    tablero[i][j] = num;
                                    CasiSudoku(tablero,arraySol);

                                }
                            }
                            tablero[i][i] = 0;
                        }
                    }
                }
            }

        }
    }
    private static boolean puedoMeter(int[][] tablero,int fila, int columna, int num) {
        if(puedoFila(tablero,fila,num) && puedoColumna(tablero,columna,num) && puedoCuadrado(tablero,fila,columna,num)){
            return true;
        }else{
            return false;
        }
    }

    private static boolean puedoFila(int[][] tablero, int fila, int num) {
        for(int j = 0; j < TAM; j++){
            if(tablero[fila][j] == num){
                return false;
            }
        }
        return true;
    }
    private static boolean puedoColumna(int[][] tablero, int columna, int num) {
        for(int j = 0; j < TAM; j++){
            if(tablero[j][columna] == num){
                return false;
            }
        }
        return true;
    }
    private static boolean puedoCuadrado(int[][] tablero, int fila, int columna, int num) {
        int f = fila - fila % 3;
        int c = columna - columna % 3;
        for(int i = f; i < f + 3; i++){
            for(int j = c; j < c + 3;j++){
                if(tablero[i][j] == num){
                    return false;
                }
            }
        }
        return true;
    }
}
