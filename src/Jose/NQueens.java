public class NQueens {
    final static int N = 8;

    public static void main(String[] args) {

    }

    private static boolean SolveNQueensBT(int[][] board, int col) {
        if (isSolution(col)) {
            return true;
        } else {
            for (int i = 0; i < N; i++) {
                if (isFeasible(board, i, col)) {
                    board[i][col] = 1;
                    if(SolveNQueensBT(board, ++col)){
                        return true;
                    }
                    board[i][col] = 0;
                }
            }
        }
        return false;
    }

    private static boolean isFeasible(int[][] board, int row, int col) {
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

    private static boolean isSolution(int col) {
        return col == N;
    }
}
