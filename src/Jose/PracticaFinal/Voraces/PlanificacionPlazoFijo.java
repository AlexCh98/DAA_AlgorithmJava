package Voraces;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PlanificacionPlazoFijo {

    public static void main(String[] Args) {
        Datos datos = new Datos();
        datos.fechasMaxima = new int[]{2, 1, 2, 1};
        datos.benficios = new int[]{50, 10, 15, 30};
        int[] solucion = planificar(datos);
        System.out.println(Arrays.toString(solucion));
    }

    private static int[] planificar(Datos datos) {
        int numeroActividades = datos.benficios.length;
        int ultimaFecha = Arrays.stream(datos.fechasMaxima).max().getAsInt();
        int[] solucion = new int[ultimaFecha + 1];
        Arrays.fill(solucion, -1);
        Set<Integer> candiadatos = IntStream.range(0, numeroActividades).boxed().collect(Collectors.toSet());
        while (!candiadatos.isEmpty()) {
            int mejorCandidato = mejorCandidato(datos, candiadatos);
            candiadatos.remove(mejorCandidato);
            for (int i = datos.fechasMaxima[mejorCandidato]; i >= 0; i--) {
                if (solucion[i] == -1) {
                    solucion[i] = mejorCandidato;
                    break;
                }
            }
        }
        return solucion;

    }

    private static int mejorCandidato(Datos datos, Set<Integer> candiadatos) {
        int mejorBenficio = Integer.MIN_VALUE;
        int mejorCandidato = 0;
        for (int candiadato : candiadatos) {
            int beneficio = datos.benficios[candiadato];
            if (beneficio > mejorBenficio) {
                mejorCandidato = candiadato;
                mejorBenficio = beneficio;
            }
        }
        return mejorCandidato;
    }

    private static class Datos {
        int[] benficios;
        int[] fechasMaxima;
    }
}
