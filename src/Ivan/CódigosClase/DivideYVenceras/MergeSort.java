package Ivan.CódigosClase.DivideYVenceras;

import java.util.Arrays;

public class MergeSort {


    public static void main(String[] asdf) {
        int[] v = {3, 1, 6, 3, 8, 5, 9, 6, 2, 4};

        int[] sorted = mergeSort(v);
        Arrays.sort(v);
        System.out.println(Arrays.toString(v) + ", length=" + v.length);
        System.out.println(Arrays.toString(sorted) + ", length=" + sorted.length);
    }


    private static int[] mergeSort(int[] v) {
        return auxMergeSort(v, 0, v.length); //[0, length)
    }

    private static int[] auxMergeSort(int[] v, int start, int end) {
        if (end - start < 2)
            return Arrays.copyOfRange(v, start, end);
        int mid = (start + end) / 2;
        //dividir
        int[] v_low = auxMergeSort(v, start, mid); //[start,mid)
        int[] v_high = auxMergeSort(v, mid, end); //[mid, end)
        //combinar
        int[] v_sorted = merge(v_low, v_high);
        return v_sorted;
    }


    private static int[] merge(int[] v_low, int[] v_high) {
        int[] v_mix = new int[v_low.length + v_high.length];
        int i = 0, j = 0, k = 0; //low index, high index, merged index

        //copy smaller first
        while (i < v_low.length && j < v_high.length) {
            if (v_low[i] <= v_high[j]) {
                v_mix[k] = v_low[i];
                i++;
            } else {
                v_mix[k] = v_high[j];
                j++;
            }
            k++;
        }

        //copy remaining from v_low (if any)
        while (i < v_low.length) {
            v_mix[k] = v_low[i];
            i++;
            k++;
        }

        //copy remaining from v_high (if any)
        while (j < v_high.length) {
            v_mix[k] = v_high[j];
            j++;
            k++;
        }
        return v_mix;
    }
}
