package Ivan.CódigosClase.DivideYVenceras.ejercicios;

/**
 * Divide y vencerás: Ejercicio 1
 * Se pide desarrollar un código que encuentre (si existe)
 * un elemento que sea un punto fijo en el vector, es decir, un
 * elemento cuyo valor sea igual al del índice en el que
 * se encuentra.
 * <p>
 * Si recorremos todos los elementos la solución sería O(n),
 * podemos utilizar DyV para hallar una solución en O(log(n))
 *
 * <p>
 * Precondicion: No hay elementos repetidos.
 */
public class E1_busquedaIndice {


    public static void main(String[] asdf) {
        int v[] = {-3, -2, -1, 0, 1, 5};
        int a[] = {-5, 0, 1, 5, 6, 7, 8};
        int found = findFixedPoint(a);
        System.out.println(found);
    }

    public static int findFixedPoint(int v[]) {
        return auxBinarySearch(v, 0, v.length);
    }

    private static int auxBinarySearch(int[] v, int start, int end) {
        if (start >= end)
            return -1;
        int mid = (start + end) / 2;
        if (mid == v[mid])
            return mid;
        else if (mid < v[mid])
            return auxBinarySearch(v, start, mid);
        else
            return auxBinarySearch(v, mid + 1, end);


    }


}
