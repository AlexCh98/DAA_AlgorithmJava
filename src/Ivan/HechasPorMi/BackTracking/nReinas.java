package Ivan.HechasPorMi.BackTracking;

import java.util.ArrayList;
//TODO que funcione.
public class nReinas {

    static final int TAM = 7;

    public static void main(String[] args) {
        int[][] tablero = new int[TAM][TAM];
        ArrayList<int [][]> soluciones = new ArrayList<>();
        solucionar(tablero,soluciones,0);
        print (soluciones);
    }
    public static void solucionar (int [][] tablero,ArrayList<int [][]> soluciones,int reinaspuestas){
        if (reinaspuestas==TAM){
            int[][] copia = new int [TAM][TAM];
            for (int i = 0; i <TAM ; i++) {
                for (int j = 0; j <TAM; j++) {
                    copia[i][j]=tablero[i][j];
                }
            }
            soluciones.add(copia);
            print(soluciones);
        }
        for (int i = 0; i <TAM ; i++) {
            for (int j = 0; j <TAM; j++) {
                if (puedometer(tablero,i,j)){
                    tablero[i][j]=1;
                    reinaspuestas=reinaspuestas+1;
                    solucionar(tablero,soluciones,reinaspuestas);
                }tablero[i][j]=0;
            }
        }
    }
    public static boolean puedometer (int [][] tablero,int columna,int fila){
        if (puedofila(tablero,fila) && puedocolumna(tablero,columna)&& puedodiagonales(tablero,fila,columna))return true;
        return false;
    }
    public static boolean puedofila (int [][] tablero,int fila){
        for (int i = 0; i < (tablero[0].length ) ; i++) {
            if (tablero[i][fila]==1){return false;}
            }return true;
    }

    public static boolean puedocolumna(int [][] tablero,int columna){
        for (int i = 0; i < (tablero.length) ; i++) {
            if (tablero[columna][i]==1){return false;}
        }return true;
    }
    public static boolean puedodiagonales(int [][] tablero,int columna,int fila){
            if (puedodiagonalArribaIzq(tablero,columna,fila) && puedodiagonalArribaDer(tablero,columna,fila) && puedodiagonalAbajoIzq(tablero,columna,fila) && puedodiagonalAbajoDer(tablero,columna,fila))
            return true;
        return false;
    }
    public static boolean puedodiagonalArribaIzq (int [][] tablero,int columna,int fila){
        for (int i=fila, j=columna; j>=0 && i<TAM; i++, j--)
            if (tablero[i][j] == 1) return false;
        return true;
    }
    public static boolean puedodiagonalArribaDer (int [][] tablero,int columna,int fila){
        for (int i=fila, j=columna; j<TAM && i<TAM; i++, j++)
            if (tablero[i][j] == 1) return false;
        return true;
    }
    public static boolean puedodiagonalAbajoIzq (int [][] tablero,int columna,int fila){
        for (int i=fila, j=columna; i>=0 && j>=0; i--, j--)
            if (tablero[i][j] == 1) return false;
        return true;
    }
    public static boolean puedodiagonalAbajoDer (int [][] tablero,int columna,int fila){
        for (int i=fila, j=columna; i>=0 && j<TAM; i--, j++)
            if (tablero[i][j] == 1) return false;
        return true;
    }
    public static void print (ArrayList<int [][]> soluciones){
        for (int i = 0; i < soluciones.size() ; i++) {
            for (int j = 0; j < TAM; j++) {
                for (int k = 0; k < TAM ; k++) {
                    System.out.print(soluciones.get(i)[j][k]);
                }
                System.out.println();
            }
        }
    }
}
