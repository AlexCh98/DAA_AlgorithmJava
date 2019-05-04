package Alex.AlgoritmosDyV;

import java.util.Arrays;

public class Quicksort {

    /* Ordenar Arrays
    *  ordenación rápida, utiliza un pivote
    *  complejidad de O(log n) pero cuanto más ordenado peor, puede llegar hasta O(n^2)
    */
    public static void main(String[] Args) {
        int[] arr = //new int[]{1,2,4, 5, 8, 6, 4, 9};
        new int[]{1, 3, 2, 8, 5, 4, 6, 7, 9};
        quicksort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    private static void quicksort(int[] array, int principio, int fin){
        if(principio < fin){
            int indicePivote = pivote(array,principio,fin);
            quicksort(array,principio,indicePivote);
            quicksort(array,indicePivote+1,fin);
        }
    }

    private static int pivote(int[] array, int begin, int end) {
        int pivote = array[begin];
        int origen = begin -1;
        int fin = end + 1;
        while (true){
            do{
                origen++;
            }while (array[origen] < pivote);
            do{
                fin--;
            }while (array[fin] > pivote);
            if(origen >= fin){
                return fin;
            }else{
                intercambiar(array,origen,fin);
            }
        }
    }

    private static void intercambiar(int[] array, int origen, int fin) {
        int temp = array[origen];
        array[origen] = array[fin];
        array[fin] = temp;
    }
}
