package Examenes.Finales;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Mediana {
    public static void main(String[] args) {
        List<Integer> arr = IntStream.range(0, 11).mapToObj(i -> (int) (Math.random() * 1000) + 1).collect(Collectors.toList());
        int k = 2;
        kElementosMasCercanos(arr, k);
        System.out.println(kesimoElementoMaspequeno(arr, (arr.size() - 1) / 2) + 1);
        arr.sort(Integer::compareTo);
        System.out.println(arr);
    }

    private static int kElementosMasCercanos(List<Integer> arr, int k) {
        int orden = (arr.size() - 1) / 2 - k;
        int[] elementos = new int[2 * k + 1];
        for (int i = 0; i < 2 * k + 1; i++) {
            elementos[i] = kesimoElementoMaspequeno(arr, orden + i);
        }
        System.out.println(Arrays.toString(elementos));
        return 0;
    }

    private static int kesimoElementoMaspequeno(List<Integer> arr, int k) {
        int pivote = arr.get(arr.size() / 2);
        List<Integer> mayores = new ArrayList<>(), iguales = new ArrayList<>(), menores = new ArrayList<>();
        for (int elemento : arr) {
            if (elemento > pivote) mayores.add(elemento);
            else if (elemento == pivote) iguales.add(elemento);
            else menores.add(elemento);
        }
        if (k < menores.size()) return kesimoElementoMaspequeno(menores, k);
        else if (k < menores.size() + iguales.size()) return pivote;
        else {
            return kesimoElementoMaspequeno(mayores, k - menores.size() - iguales.size());
        }
    }
}
