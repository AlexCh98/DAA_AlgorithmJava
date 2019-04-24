package Dani;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LAB {

    /*public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";*/

    public static int mejor = 0;

    public static void main(String[] args) throws IOException {

        //FileReader f = new FileReader("entrada.txt");
        Scanner scan = new Scanner(System.in);

        int dimension = scan.nextInt();
        int[][] board = new int[dimension][dimension];

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                board[i][j] = scan.nextInt();
            }
        }

        int mejor = 0;
        laberinto(0, 0, 1, board);
        if (mejor > 0) {
            System.out.println(mejor);
        }else{
            System.out.println("SIN SOLUCION");
        }
    }

    public static void laberinto(int c, int f, int k, int[][] board){

        if(esSolucion(c, f, board) && (k<mejor || mejor == 0)){
            System.out.println("Encontrao");
            printRecorrido(board);
            mejor = k;
        }else if(mejor == 0 || k<mejor){
            System.out.println("Mejor: "+mejor);
            board[f][c] = k; //Guardamos el número de paso en la casilla actual
            if(esFactible(c+1, f, board)){//Vox
                System.out.println("Vox");
                laberinto(c+1, f, k+1, board);

            }
            if(esFactible(c-1, f, board)) {//Podemos
                System.out.println("Podemos");
                laberinto(c - 1, f, k + 1, board);

            }
            if(esFactible(c, f+1, board)) {//Fritzl
                System.out.println("Fritzl");
                laberinto(c, f+1, k + 1, board);

            }
            if(esFactible(c, f-1, board)) {//España
                System.out.println("España");
                laberinto(c, f-1, k + 1, board);
            }
            System.out.println("Atrásss");
            board[f][c] = 0;
        }
    }

    public static boolean esSolucion(int c, int f, int[][] board){
        return (f == board.length-1 && c == board.length-1);
    }

    public static boolean esFactible(int c, int f, int[][] board){
        if(f < board.length && f >= 0 && c < board.length && c >= 0) {
            return (board[f][c] == 0);
        }else{
            return false;
        }
    }

    public static void printRecorrido(int[][] board) {
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
                    System.out.format("|"+board[i][j]+"|");
                }
            }
            System.out.println();
            for (int j = 0; j < board[i].length; j++) {
                System.out.print("----");
            }
            System.out.println();
        }
    }
}
