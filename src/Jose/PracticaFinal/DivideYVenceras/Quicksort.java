package DivideYVenceras;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Quicksort {
    public static void main(String[] args){
        IntStream.range(0, 1000).forEachOrdered(i -> {
            int[] arr = IntStream.range(0, 100).map(j -> (int) (Math.random() * 1000 + 1)).toArray();
            int[] arrOrdenado = Arrays.copyOf(arr, arr.length);
            Arrays.sort(arrOrdenado);
            quicksort(arr);
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] != arrOrdenado[j]) System.out.println("False");
            }
        });
    }
    public static void quicksort(int[] arr) {
        quicksort(arr, 0, arr.length - 1);
    }

    private static void quicksort(int[] arr, int inicio, int fin) {
        if(fin > inicio){
            int indice_pivote = pivote(arr, inicio, fin);
            quicksort(arr, inicio, indice_pivote);
            quicksort(arr, indice_pivote + 1, fin);
        }
    }

    private static int pivote(int[] arr, int inicio, int fin) {
        int pivote = arr[inicio];
        int  i = inicio -1 , j = fin +1;
        while(true){
            do{
                i++;
            }while(arr[i] < pivote);
            do{
                j--;
            }while (arr[j] >pivote);
            if(i >= j) return j;
            cambiar(arr,i ,j);
        }
    }

    private static void cambiar(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
