package Dani.Backtracking;

public class Laberinto {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) {
        int[][] board = new int[][] {
                { 0,  0, -1,  0,  0,  0,  0, -1,  0,  0},
                {-1,  0, -1,  0,  0, -1, -1,  0, -1,  0},
                { 0,  0,  0,  0,  0,  0, -1,  0, -1,  0},
                { 0, -1,  0,  0, -1, -1, -1,  0,  0,  0},
                { 0,  0, -1, -1,  0,  0,  0, -1,  0,  0},
                { 0,  0,  0,  0,  0, -1,  0, -1,  0,  0},
                {-1,  0,  0, -1, -1,  0,  0, -1,  0, -1},
                { 0, -1, -1,  0,  0,  0,  0,  0, -1, -1},
                {-1,  0,  0,  0,  0, -1,  0, -1, -1,  0},
                { 0,  0, -1,  0, -1, -1,  0,  0,  0,  -2}
        };

        laberinto(0, 0, 1, board);
    }

    public static void laberinto(int c, int f, int k, int[][] board){

        if(esSolucion(c, f, board)){
            System.out.println("Encontrao");
            printRecorrido(board);

        }else{
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
        return board[f][c] == -2;
    }

    public static boolean esFactible(int c, int f, int[][] board){
        if(f < board.length && f >= 0 && c < board.length && c >= 0) {
            return (board[f][c] == 0 || board[f][c] == -2);
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
                    System.out.format(ANSI_RED +"|"+board[i][j]+"|" + ANSI_RESET);
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
