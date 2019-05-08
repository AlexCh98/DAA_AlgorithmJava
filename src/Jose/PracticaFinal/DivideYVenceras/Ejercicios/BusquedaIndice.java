package DivideYVenceras.Ejercicios;

public class BusquedaIndice {
    public static void main (String[] args){
        int[] v = {-3, -2, -1, 0, 1, 5};
        System.out.println(puntoFijo(v));
    }
    private static int puntoFijo(int[] arr) {
        int inicio = 0, fin = arr.length - 1;
        while (inicio <= fin) {
            int medio = (fin + inicio) / 2;
            if (arr[medio] == medio) return medio;
            if (arr[medio] > medio) fin = medio - 1;
            else inicio = medio + 1;
        }
        return -1;
    }
}
