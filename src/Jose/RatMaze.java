

public class RatMaze {
    private static final int TAMANO_LABERINTO_X = 100;
    private static final int TAMANO_LABERINTO_Y = 100;

    public static void main(String[] args) {
        boolean solucionB = false;
        do {
            int[][] laberinto = new int[TAMANO_LABERINTO_X][TAMANO_LABERINTO_Y];
            crearLaberinto(laberinto);
        /*int[][] laberinto = {
//               0  1  2  3  4  5  6
                {1, 0, 0, 0, 0, 0, 0},//0
                {1, 0, 0, 1, 0, 0, 0},//1
                {1, 1, 0, 1, 0, 0, 0},//2
                {0, 1, 0, 0, 0, 0, 0},//3
                {1, 1, 0, 0, 0, 0, 0},//4
                {1, 0, 0, 0, 0, 0, 0},//5
                {1, 1, 1, 1, 1, 1, 1} //6
        };//Laberinto hardcoreado
        */
            int[][] solucion = new int[TAMANO_LABERINTO_X][TAMANO_LABERINTO_Y];
            //System.out.println("Laberinto");
            //imprimirArray(laberinto);
            if (ratMaze(solucion, 0, 0, laberinto)) {//Esto implica empezar en el (0, 0)
                System.out.println("Laberinto");
                imprimirArray(laberinto);
                System.out.println("Solucion");
                imprimirArray(solucion);
                solucionB = true;
            } else {//Solo sabe resolverlo si se puede salir sin tener que volver para atras
                System.out.println("Laberinto sin solucion");
            }
        } while (!solucionB);//Este while esta para probar laberintos hasta dar con uno con solucion
    }

    private static void crearLaberinto(int[][] laberinto) {
        for (int i = 0; i < TAMANO_LABERINTO_X; i++) {
            for (int j = 0; j < TAMANO_LABERINTO_Y; j++) {
                if ((i == 0 && j == 0) || (i == TAMANO_LABERINTO_X - 1 && j == TAMANO_LABERINTO_Y - 1)) {
                    laberinto[i][j] = 1;
                } else {
                    laberinto[i][j] = (Math.random() < 0.6) ? 1 : 0;//Mayor proporcion de 1 que de ceros
                }
            }
        }
    }

    private static void imprimirArray(int[][] arr) {
        for (int[] ints : arr) {
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }
    }


    private static boolean esBuena(int x, int y, int[][] laberinto, int[][] solucion) {
        if (x < 0 || x >= TAMANO_LABERINTO_X || y < 0 || y >= TAMANO_LABERINTO_Y) return false;
        else return laberinto[x][y] == 1 && solucion[x][y] == 0;
    }

    private static boolean ratMaze(int[][] solucion, int x, int y, int[][] laberinto) {
        if (x == TAMANO_LABERINTO_X - 1 && y == TAMANO_LABERINTO_Y - 1) {
            solucion[x][y] = 1;
            return true;
        }
        if (esBuena(x, y, laberinto, solucion)) {
            solucion[x][y] = 1;
            if (ratMaze(solucion, x + 1, y, laberinto)) {//Abajo
                return true;
            }
            if (ratMaze(solucion, x, y + 1, laberinto)) {//Derecha
                return true;
            }
            if (ratMaze(solucion, x - 1, y, laberinto)) {//Arriba
                return true;
            }
            if (ratMaze(solucion, x, y - 1, laberinto)) {//Izuierda
                return true;
            }
            solucion[x][y] = 0;
        }
        return false;
    }


}
