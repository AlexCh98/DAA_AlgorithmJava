package DivideYVenceras.Juez;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class GOW {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numero_niveles = Integer.parseInt(reader.readLine());
        int[] niveles = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int numero_casos = Integer.parseInt(reader.readLine());
        String[] linea = reader.readLine().split(" ");
        for (int i = 0; i < numero_casos; i++) {
            int nivel_jugador = Integer.parseInt(linea[i]);
            int indice = busquedaBinaria(niveles, nivel_jugador);
            //System.out.println("nivel_jugador = " + nivel_jugador);
            //System.out.println("indice = " + indice);
            if (indice == -1) {
                System.out.println("X" + " " + niveles[0]);
            } else if (indice == -2) {
                System.out.println(niveles[numero_niveles - 1] + " " + "X");
            } else if (niveles[indice] == nivel_jugador) {
                if (indice == 0) {
                    System.out.println("X" + " " + niveles[1]);
                } else if (indice == numero_niveles - 1) {
                    System.out.println(niveles[numero_niveles - 2] + " " + "X");
                } else {
                    System.out.println(niveles[indice - 1] + " " + niveles[indice + 1]);
                }
            } else {
                System.out.println(niveles[indice] + " " + niveles[indice + 1]);
            }
        }
    }

    private static int busquedaBinaria(int[] arr, int element) {
        int inicio = 0, fin = arr.length - 1;
        if (element < arr[inicio]) return -1;
        if (element > arr[fin]) return -2;
        while (inicio <= fin) {
            int medio = (fin + inicio) / 2;
            if (arr[medio] == element) return medio;
            if (arr[medio] > element) fin = medio - 1;
            else inicio = medio + 1;
        }
        return fin;
    }
}
/*
10
11 22 30 35 49 54 68 74 75 97
8
9 11 16 35 52 76 97 100

 */
