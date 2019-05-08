package Examenes.Parciales;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SeleccionEnemigos {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numeroJugadores = Integer.parseInt(reader.readLine());
        int [] nivel = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int numeroConsultas = Integer.parseInt(reader.readLine());
        for (int i = 0; i < numeroConsultas; i++) {
            int[] minmax = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.out.println(busquedaBinaria(nivel, minmax[0])+  " " + busquedaBinaria(nivel, minmax[1]));
        }
    }

    private static int busquedaBinaria(int[] arr, int element) {
        int inicio = 0, fin = arr.length - 1;
        if (element < arr[inicio] || element > arr[fin]) return -1;
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            if (arr[medio] == element) return medio;
            if (element > arr[medio]) inicio = medio + 1;
            else fin = medio - 1;
        }
        return -1;
    }
}
