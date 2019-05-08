package Backtracking;


public class ColoreadoGrafo {
    private static boolean colorearGrafo(int[][] grafo, int[] colores, int v, int m){
        if(v == grafo.length){
            return true;
        }else{
            for(int color = 1; color <= m; color++){
                if(esFactible(grafo, v, colores, color)){
                    colores[v] = color;
                    if(colorearGrafo(grafo, colores, v + 1, m))return true;
                    colores[v] = 0;
                }
            }
        }
        return false;
    }

    private static boolean esFactible(int[][] grafo, int v, int[] colores, int color) {
        for (int i = 0; i < grafo.length; i++) {
            if(grafo[i][v] == 1 && colores[i] == color) return false;
        }
        return true;
    }
}
