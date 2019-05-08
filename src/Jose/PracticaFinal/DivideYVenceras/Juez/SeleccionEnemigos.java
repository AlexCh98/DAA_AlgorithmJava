package DivideYVenceras.Juez;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SeleccionEnemigos {
    public static void main (String [] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.readLine();
        int[] niveles = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int numero_casos = Integer.parseInt(reader.readLine());
        for (int i = 0; i < numero_casos; i++) {
            int [] minmax = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.out.println(busquedaBinaria(niveles, minmax[0]) + " " + busquedaBinaria(niveles, minmax[1]));
        }
    }
    public static int busquedaBinaria(int[] arr, int element) {
        int inicio = 0, fin = arr.length - 1;
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            if (arr[medio] == element) return medio;
            if (arr[medio] > element) fin = medio - 1;
            else inicio = medio + 1;
        }
        return -1;
    }
}
