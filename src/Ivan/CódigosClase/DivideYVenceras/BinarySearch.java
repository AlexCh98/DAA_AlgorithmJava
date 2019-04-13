package Ivan.CódigosClase.DivideYVenceras;

public class BinarySearch {

    public static void main(String[] asdf) {
        int v[] = {1, 2, 2, 3, 3, 4, 5, 6, 8, 9};
        int q = 2;

        //int index = binarySearch(v, q);
        int index = recBinarySearch(v, q);
        if (index < 0) {
            System.out.printf("No encontrado. (Se podría insertar en la posición %d)\n", -index - 1);
        } else {
            System.out.printf("Encontrado en la posición: %d (Elemento: %d)", index, v[index]);
        }
    }

    public static int itBinarySearch(int v[], int element) {
        int low = 0, high = v.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (v[mid] == element)
                return mid; //found
            else if (v[mid] < element) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return -low - 1; //not found: where it should be -> (-index+1)
    }

    public static int recBinarySearch(int v[], int element) {
        return auxBinarySearch(v, 0, v.length - 1, element);
    }

    private static int auxBinarySearch(int[] v, int low, int high, int element) {
        if (low > high)
            return -low - 1; //not found

        int mid = (low + high) / 2;
        if (v[mid] == element)
            return mid; //found
        else if (v[mid] < element) {
            return auxBinarySearch(v, mid + 1, high, element);
        } else {
            return auxBinarySearch(v, low, mid - 1, element);
        }
    }
}
