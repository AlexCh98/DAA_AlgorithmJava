package Voraces;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Mochila {

    public static void main(String[] args) {
        Datos datos = new Datos();
        datos.pesos = IntStream.range(1, 6).map(i -> i * 10).toArray();
        datos.valores = new int[]{20, 30, 66, 40, 60};
        datos.pesoMaximo = 100;
        double[] solucion = calcularMochila(datos);
        System.out.println(Arrays.toString(solucion));
    }

    private static double[] calcularMochila(Datos datos) {
        int numeroObjetos = datos.pesos.length;
        double[] solucion = new double[numeroObjetos];
        Set<Integer> candidatos = IntStream.range(0, numeroObjetos).boxed().collect(Collectors.toSet());
        int pesoLibre = datos.pesoMaximo;
        while (!candidatos.isEmpty() && pesoLibre > 0) {
            int mejorCandidato = mejorCandidato(datos, candidatos);
            int pesoMejorCandidato = datos.pesos[mejorCandidato];
            if (pesoMejorCandidato <= pesoLibre) {
                pesoLibre -= pesoMejorCandidato;
                solucion[mejorCandidato] = 1.0;
            } else {
                double proporcion = (double) pesoLibre / pesoMejorCandidato;
                pesoLibre -= pesoMejorCandidato;
                solucion[mejorCandidato] = proporcion;
            }
            candidatos.remove(mejorCandidato);
        }
        return solucion;
    }

    private static int mejorCandidato(Datos datos, Set<Integer> candidatos) {
        double mejorRatio = Integer.MIN_VALUE;
        int mejorCandidato = 0;
        for (int candidato : candidatos) {
            double ratio = (double) datos.valores[candidato] / datos.pesos[candidato];
            if (ratio > mejorRatio) {
                mejorCandidato = candidato;
                mejorRatio = ratio;
            }
        }
        return mejorCandidato;
    }

    private static class Datos {
        int[] pesos;
        int[] valores;
        int pesoMaximo;
    }
}
