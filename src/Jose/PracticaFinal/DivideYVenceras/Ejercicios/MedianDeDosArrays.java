package DivideYVenceras.Ejercicios;

import java.util.Arrays;

public class MedianDeDosArrays {
    public static void main(String[] args) {
        // 1, 3, 4, 5, 6, 7, 10 -> 5
        int[] v1 = {1, 5, 6, 7};
        int[] v2 = {3, 4, 10};
        System.out.println(median(v1, v2));
    }

    private static int median(int[] arr1, int[] arr2) {
        return median(arr1, 0, arr1.length - 1, arr2, 0, arr2.length - 1);
    }

    private static int median(int[] arr1, int inicio1, int fin1, int[] arr2, int inicio2, int fin2) {
        int tamano1 = fin1 - inicio1;
        int tamano2 = fin2 - inicio2;
        if (tamano1 == 1 && tamano2 == 1) {
            return Math.max(Math.max(arr1[inicio1], arr2[inicio2]), Math.min(arr1[fin1], arr2[fin2]));
        }
        int medio1 = tamano1 / 2;
        int medio2 = tamano2 / 2;
        int mediana1 = arr1[medio1];
        int mediana2 = arr2[medio2];
        if (mediana1 == mediana2) return mediana1;
        if (mediana1 > mediana2) return median(arr1, inicio1, medio1, arr2, medio2, fin2);
        return median(arr1, medio1, fin1, arr2, inicio2, medio2);
    }
}
