package Ivan;

public class Laberinto {
    public static void laberinto (int [][] tablero, int r, int c,int k) {
        tablero[r][c] = k;
        if (r == tablero.length - 1 && c == tablero.length - 1) {
            //imprime soluciones
        } else {
            if (esvalida(tablero, r - 1, c)) {
                laberinto(tablero, r - 1, c, k++);
            }
            if (esvalida(tablero, r + 1, c)) {
                laberinto(tablero, r + 1, c, k++);
            }
            if (esvalida(tablero, r, c - 1)) {
                laberinto(tablero, r, c - 1, k++);
            }
            if (esvalida(tablero, r, c + 1)) {
                laberinto(tablero, r, c + 1, k++);
            }
        }
    }


    public static boolean esvalida (int [][] tablero,int r,int c){
        if (r > 0 && r<tablero.length && c>0 && c<tablero.length ){//Si no es cuadrado compruebo con tablero[0].lenght
            return (tablero[r][c] == 0);
         }else {return false;}

    }

}
