package Ivan.CosasQueNoSonMias.DivideYVenceras;

/* Java program for Merge Sort */
public class MergeSort {

    private int[] arr;

    public MergeSort(int[] arr){
        this.arr = arr;
    }

    public void mergesort(){
        this.sort(0, arr.length-1);
    }

    // Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]

    private void merge(int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int [n1];
        int R[] = new int [n2];

        /*Copy data to temp arrays*/
        for (int i=0; i<n1; ++i)
            L[i] = this.arr[l + i];
        for (int j=0; j<n2; ++j)
            R[j] = this.arr[m + 1+ j];


        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                this.arr[k] = L[i];
                i++;
            } else {
                this.arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            this.arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            this.arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Ivan.CosasQueNoSonMias.BackTracking.Main function that sorts arr[l..r] using
    // merge()
    private void sort(int l, int r) {
        if (l < r) {
            // Find the middle point
            int m = (l+r)/2;

            // Sort first and second halves
            this.sort(l, m);
            this.sort(m+1, r);

            // Merge the sorted halves
            this.merge(l, m, r);
        }
    }

    /* A utility function to print array of size n */
    public void printArray() {
        int n = this.arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(this.arr[i] + " ");
        System.out.println();
    }
}
