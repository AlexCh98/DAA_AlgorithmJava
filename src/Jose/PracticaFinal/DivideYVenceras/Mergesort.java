package DivideYVenceras;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Mergesort {

    public static void main(String[] Args) {
        IntStream.range(0, 1000).forEachOrdered(i -> {
            int[] arr = IntStream.range(0, 100).map(j -> (int) (Math.random() * 1000 + 1)).toArray();
            int[] arrOrdenado = Arrays.copyOf(arr, arr.length);
            Arrays.sort(arrOrdenado);
            mergesort(arr);
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] != arrOrdenado[j]) System.out.println("False");
            }
        });
    }

    private static void mergesort(int[] arr) {
        mergesort(arr, 0, arr.length - 1);
    }

    private static void mergesort(int[] arr, int inicio, int fin) {
        if (fin > inicio) {
            int medio = (inicio + fin) / 2;
            mergesort(arr, inicio, medio);
            mergesort(arr, medio + 1, fin);
            merge(arr, inicio, medio, fin);
        }
    }

    private static void merge(int[] arr, int inicio, int medio, int fin) {
        int tamano_izquierda = medio - inicio + 1;
        int tamano_derecha = fin - medio;
        int[] izquierda = new int[tamano_izquierda];
        int[] derecha = new int[tamano_derecha];
        System.arraycopy(arr, inicio, izquierda, 0, tamano_izquierda);
        System.arraycopy(arr, medio + 1, derecha, 0, tamano_derecha);
        int i = 0, j = 0, k = inicio;
        while (i < tamano_izquierda && j < tamano_derecha) {
            if (izquierda[i] <= derecha[j]) {
                arr[k] = izquierda[i];
                i++;
            } else {
                arr[k] = derecha[j];
                j++;
            }
            k++;
        }
        while (i < tamano_izquierda) {
            arr[k] = izquierda[i];
            i++;
            k++;
        }
        while (j < tamano_derecha) {
            arr[k] = derecha[j];
            j++;
            k++;
        }

    }
}
