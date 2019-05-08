package DivideYVenceras;

public class BusquedaBinaria {
    public static void main(String[] Args) {

    }

    private static int busquedaBinaria(int element, int[] arr) {
        int inicio = 0, fin = arr.length - 1;
        if (element < arr[inicio] || element > arr[fin]) return -1;
        while (fin >= inicio) {
            int medio = (fin + inicio) / 2;
            if (arr[medio] == element) return medio;
            if (arr[medio] > element) {
                fin = medio - 1;
            } else {
                inicio = medio + 1;
            }
        }
        return -1;
    }
}
