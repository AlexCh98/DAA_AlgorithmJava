package Jose;

import java.util.Arrays;

public class Quicksort {
    public static void main(String[] Args) {
        int[] arr = new int[]{1,2 , 4, 5, 6, 8, 4, 9};//new int[]{1, 3, 2, 8, 5, 4, 6, 7, 9};
        quicksort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    private static void quicksort(int[] arr, int principio, int fin) {
        if (principio < fin) {
            int inidice_pivote = pivote(arr, principio, fin);
            quicksort(arr, principio, inidice_pivote);
            quicksort(arr, inidice_pivote + 1, fin);
        }
    }

    private static int pivote(int[] arr, int principio, int fin) {
        int pivote = arr[principio];
        int i = principio - 1, j = fin + 1;
        while (true) {
            do {
                i++;
            } while (arr[i] < pivote);
            do {
                j--;
            } while (arr[j] > pivote);
            if (i >= j) return j;
            cambiar(arr, i, j);
        }

    }

    private static void cambiar(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}
