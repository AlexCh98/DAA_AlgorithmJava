package Jose;

import java.util.Arrays;
import java.util.Scanner;

public class Mergesort {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        int longitud = Integer.parseInt(reader.nextLine());
        String[] line = reader.nextLine().split(" ");
        int[] arr = new int[longitud];
        for (int i = 0; i < longitud; i++) {
            arr[i] = Integer.parseInt(line[i]);
        }
        sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    private static void sort(int[] arr, int l, int r) {
        if (l < r) {
            int m = (r + l) / 2;
            sort(arr, l, m);
            sort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int tamano_l = m - l + 1;
        int tamano_r = r - m;
        int[] L = new int[tamano_l];
        int[] R = new int[tamano_r];
        System.arraycopy(arr, l, L, 0, tamano_l);
        System.arraycopy(arr, m + 1, R, 0, tamano_r);
        int i = 0, j = 0, k = l;
        while (i < tamano_l && j < tamano_r) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < tamano_l) {
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < tamano_r) {
            arr[k] = R[j];
            j++;
            k++;
        }

    }
}
