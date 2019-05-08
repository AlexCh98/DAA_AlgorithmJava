package DivideYVenceras.Ejercicios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Median {

    public static void main(String[] args) {
        IntStream.range(0, 10000).forEachOrdered(o -> {
            List<Integer> arr = IntStream.range(0, 10).map(i -> (int) (Math.random() * 100) + 1).boxed().collect(Collectors.toList());
            //System.out.println("arr = " + arr);
            int mediana = mediana(arr);
            //System.out.println("mediana = " + mediana);
            arr.sort(Integer::compareTo);
            int medianaReal = arr.get((arr.size() - 1) / 2);
            //System.out.println("medianaReal = " + medianaReal);
            System.out.println((mediana == medianaReal));
        });
    }

    private static int mediana(List<Integer> arr) {
        int orden_relativo = (arr.size() - 1) / 2;
        return nesimomaspequeno(arr, orden_relativo);
    }

    private static int nesimomaspequeno(List<Integer> arr, int n) {
        //System.out.println("arr = [" + arr + "], n = [" + n + "]");
        List<Integer> mayores = new ArrayList<>(), iguales = new ArrayList<>(), menores = new ArrayList<>();
        int pivote = arr.get(arr.size() / 2);
        for (int element : arr) {
            if (element > pivote) mayores.add(element);
            else if (element == pivote) iguales.add(element);
            else menores.add(element);
        }
        //System.out.println("pivote = " + pivote);
        //System.out.println("mayores = " + mayores);
        //System.out.println("iguales = " + iguales);
        //System.out.println("menores = " + menores);
        if (n < menores.size()) {
            return nesimomaspequeno(menores, n);
        } else if (n < menores.size() + iguales.size()) {
            return pivote;
        } else {
            return nesimomaspequeno(mayores, n - menores.size() - iguales.size());
        }
    }
}
