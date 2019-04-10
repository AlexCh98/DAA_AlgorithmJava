package Ivan.CosasQueNoSonMias.DivideYVenceras;

public class QuickSortPractica {

    private int[] vector;

    //7 minutos

    public QuickSortPractica(int[] vector) {
        this.vector = vector;
    }

    public void quicksort() {
        this.quicksortrec(0, vector.length - 1);
    }

    private void quicksortrec(int l, int r) {
        int i = l;
        int j = r;
        int pivot = vector[l];
        int aux;

        while (i < j) {
            while (vector[i] <= pivot && i < j) {
                i++;
            }
            while (vector[j] > pivot) {
                j--;
            }
            if (i < j) {
                aux = vector[i];
                vector[i] = vector[j];
                vector[j] = aux;
            }
        }
        vector[l] = vector[j];
        vector[j] = pivot;
        if (l < j - 1) {
            quicksortrec(l, j - 1);
        }
        if (j + 1 < r) {
            quicksortrec(j + 1, r);
        }

    }

    public void printArray() {
        int n = this.vector.length;
        for (int i = 0; i < n; ++i)
            System.out.print(this.vector[i] + " ");
        System.out.println();
    }
}
