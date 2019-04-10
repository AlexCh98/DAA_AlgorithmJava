package Ivan.CosasQueNoSonMias.DivideYVenceras;

public class QuickSort {

    private int[] vector;

    public QuickSort(int[] arr) {
        this.vector = arr;
    }

    public void quicksort() {
        this.quicksortrec(0, vector.length - 1);
    }

    private void quicksortrec(int izquierda, int derecha) {
        int pivote = vector[izquierda];
        int i = izquierda;
        int j = derecha;
        int auxIntercambio;
        while (i < j) {
            while (vector[i] <= pivote && i < j) {
                i++;
            }
            while (vector[j] > pivote) {
                j--;
            }
            if (i < j) {
                auxIntercambio = vector[i];
                vector[i] = vector[j];
                vector[j] = auxIntercambio;
            }
        }
        vector[izquierda] = vector[j];
        vector[j] = pivote;
        if (izquierda < j - 1) {
            quicksortrec(izquierda, j - 1);
        }
        if (j + 1 < derecha) {
            quicksortrec(j + 1, derecha);
        }
    }

    public void printArray() {
        int n = this.vector.length;
        for (int i = 0; i < n; ++i)
            System.out.print(this.vector[i] + " ");
        System.out.println();
    }
}
