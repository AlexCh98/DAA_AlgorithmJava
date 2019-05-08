package Examenes.Parciales;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Examenes {


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String [] linea = reader.readLine().split(" ");
        int numeroEstudiantes = Integer.parseInt(linea[0]);
        int numeroConexiones = Integer.parseInt(linea[1]);
        int numeroExamenes = Integer.parseInt(linea[2]);
        int [][] clase = new int[numeroEstudiantes][numeroEstudiantes];
        for (int i = 0; i < numeroConexiones; i++) {
            linea = reader.readLine().split(" ");
            int origen = Integer.parseInt(linea[0]);
            int destino = Integer.parseInt(linea[1]);
            clase[origen][destino] = 1;
            clase[destino][origen] = 1;
        }
        int [] colores = new int[numeroEstudiantes];
        System.out.println(colorearGrafo(clase, 0, colores, numeroExamenes) ? "OK" : "NO HAY SUFICIENTE");
    }

    private static boolean colorearGrafo(int[][] grafo, int vertice, int[] colores, int numeroColores) {
        if (vertice == grafo.length) {
            return true;
        } else {
            for (int color = 1; color <= numeroColores; color++) {
                if (esValido(grafo, vertice, colores, color)) {
                    colores[vertice] = color;
                    if (colorearGrafo(grafo, vertice + 1, colores, numeroColores))return true;
                    colores[vertice] = 0;
                }
            }
            return false;
        }
    }

    private static boolean esValido(int[][] grafo, int vertice, int[] colores, int color) {
        for (int i = 0; i < grafo.length; i++) {
            if(grafo[vertice][i] == 1 && colores[i] == color) return false;
        }
        return true;
    }
}
