package Backtracking;


import java.util.Arrays;
import java.util.stream.IntStream;

public class MochilaBT {
    private static boolean[] mejorSolucion;
    private static int mejorValor;

    private static void llenarMochila(int valorActual, boolean[] solActual, int indice, int[] pesos, int[] valores, int pesoLibre) {
        if (indice == pesos.length) {
            if (valorActual > mejorValor) {
                mejorSolucion = Arrays.copyOf(solActual, solActual.length);
                mejorValor = valorActual;
            }
        } else {
            if (pesos[indice] <= pesoLibre) {
                valorActual += valores[indice];
                pesoLibre -= pesos[indice];
                solActual[indice] = true;
                llenarMochila(valorActual, solActual, indice + 1, pesos, valores, pesoLibre);
                solActual[indice] = false;
                pesoLibre += pesos[indice];
                valorActual -= valores[indice];
            }
            llenarMochila(valorActual, solActual, indice + 1, pesos, valores, pesoLibre);
        }
    }

    public static void main(String[] args) {
        int[] pesos = IntStream.range(1, 6).map(i -> i * 10).toArray();
        int[] valores = new int[]{20, 30, 66, 40, 60};
        boolean[] solActual = new boolean[pesos.length];
        int pesoMaximo = 100;
        llenarMochila(0, solActual, 0, pesos, valores, pesoMaximo);
        for (int i = 0; i < mejorSolucion.length; i++) {
            if (mejorSolucion[i]) {
                System.out.println("Meto objeto " + i);
            }
        }
        System.out.println("Valor: " + mejorValor);

    }

}