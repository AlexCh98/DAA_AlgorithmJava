package Voraces;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class TiempoDeEspera {

    public static void main(String[] Args) {
        int[] tiempos = new int[]{5, 10, 3};
        int[] solucion = calcularOrden(tiempos);
        System.out.println(Arrays.toString(solucion));
    }

    private static int[] calcularOrden(int[] tiempos) {
        int numeroClientes = tiempos.length;
        int[] solucion = new int[numeroClientes];
        Set<Integer> candidatos = IntStream.range(0, numeroClientes).boxed().collect(Collectors.toSet());
        int i = 0;
        while (!candidatos.isEmpty()) {
            int mejorCandidato = mejorCandidato(candidatos, tiempos);
            solucion[i] = mejorCandidato;
            candidatos.remove(mejorCandidato);
            i++;
        }
        return solucion;
    }

    private static int mejorCandidato(Set<Integer> candidatos, int[] tiempos) {
        int mejorTiempo = Integer.MAX_VALUE;
        int mejorCandiato = 0;
        for (int candidato : candidatos) {
            if (tiempos[candidato] < mejorTiempo) {
                mejorCandiato = candidato;
                mejorTiempo = tiempos[candidato];
            }
        }
        return mejorCandiato;
    }
}
