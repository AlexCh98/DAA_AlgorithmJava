package Alex.Backtracking;

public class ProblemaLasNReinas {
    //CODIGO QUE NO HA COPIADO
   /* boolean NQueenProbleem(int[][] board, int col){
        if(col==N){
            return true;
        }else{
            for(int i = 0;i < N;i++){
                if(esFactible(board,i,col)){
                    board[i][col]=1;
                    NQueenProbleem(board,col++);
                }
            }
        }
        //COMPLETAR
    }*/
    final static int N = 8;

        public static void main(String[] args) {
            int[][] tablero = new int[8][8];
            for (int i = 0;i < 8;i++){
                for(int j = 0; j<8;j++){
                    tablero[i][j]=0;
                }
            }

            System.out.println(NQueenProblem(tablero,0));

        }

        private static boolean NQueenProblem(int[][] board, int col) {
            if (col == N) {
                return true;
            } else {
                for (int i = 0; i < N; i++) {
                    if (esFactible(board, i, col)) {
                        board[i][col] = 1;
                        if(NQueenProblem(board, col+1)){
                            return true;
                        }
                        board[i][col] = 0;
                    }
                }
            }
            return false;
        }

        private static boolean esFactible(int[][] board, int row, int col) {
            int i, j;

            for (i = 0; i < col; i++)
                if (board[row][i] == 1)
                    return false;

            for (i=row, j=col; i>=0 && j>=0; i--, j--)
                if (board[i][j] == 1)
                    return false;

            for (i=row, j=col; j>=0 && i<N; i++, j--)
                if (board[i][j] == 1)
                    return false;

            return true;
        }



}
