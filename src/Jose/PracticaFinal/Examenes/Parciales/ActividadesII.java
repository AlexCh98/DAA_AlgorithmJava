package Examenes.Parciales;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ActividadesII {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numeroCasos = Integer.parseInt(reader.readLine());
        for (int caso = 0; caso < numeroCasos; caso++) {
            int numeroActivades = Integer.parseInt(reader.readLine());
            Datos datos = new Datos();
            String[] linea = reader.readLine().split(" ");
            datos.inicio = new int[numeroActivades];
            datos.fin = new int[numeroActivades];
            for (int i = 0; i < (numeroActivades * 3); i++) {
                int valorLeido = Integer.parseInt(linea[i]);
                if (i % 3 == 0) datos.inicio[i / 3] = valorLeido;
                else if (i % 3 == 1) datos.fin[i / 3] = valorLeido;
                else datos.fin[(i - 1) / 3] += valorLeido;
            }
            System.out.println(calcularAsistencia(datos));
        }
    }

    private static int calcularAsistencia(Datos datos) {
        Set<Integer> candidatos = IntStream.range(0, datos.fin.length).boxed().collect(Collectors.toSet());
        int fin = 0;
        int solucion = 0;
        while (!candidatos.isEmpty()) {
            int mejorActividadad = mejorActividad(datos, candidatos);
            candidatos.remove(mejorActividadad);
            if (datos.inicio[mejorActividadad] >= fin) {
                solucion++;
                fin = datos.fin[mejorActividadad];
            }
        }
        return solucion;
    }

    private static int mejorActividad(Datos datos, Set<Integer> candidatos) {
        int mejorFin = Integer.MAX_VALUE;
        int mejorCandidato = 0;
        for (int candidato : candidatos) {
            int fin = datos.fin[candidato];
            if(fin < mejorFin){
                mejorFin = fin;
                mejorCandidato = candidato;
            }
        }
        return mejorCandidato;
    }

    private static class Datos {
        int[] inicio;
        int[] fin;
    }
}
