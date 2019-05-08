package Voraces.Juez;

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
        Actividades.Datos datos = new Actividades.Datos();
        for (int caso = 0; caso < numeroCasos; caso++) {
            int numeroActividades = Integer.parseInt(reader.readLine());
            String[] linea = reader.readLine().split(" ");
            datos.inicios = new int[numeroActividades];
            datos.finales = new int[numeroActividades];
            for (int i = 0; i < linea.length; i++) {
                int valor = Integer.parseInt(linea[i]);
                if (i % 3 == 0) {
                    datos.inicios[i / 3] = valor;
                } else if (i % 3 == 1) {
                    datos.finales[i / 3] = valor;
                } else {
                    datos.finales[(i-1)/ 3] += valor;
                }
            }
            int solucion = horario(datos);
            System.out.println(solucion);
        }
    }

    private static int horario(Actividades.Datos datos) {
        int numeroAcitvidades = datos.inicios.length;
        Set<Integer> candidatos = IntStream.range(0, numeroAcitvidades).boxed().collect(Collectors.toSet());
        int fin = 0;
        int solucion = 0;
        while (!candidatos.isEmpty()) {
            int mejorCandidato = mejorCandidato(datos, candidatos);
            candidatos.remove(mejorCandidato);
            if (datos.inicios[mejorCandidato] >= fin) {
                solucion++;
                fin = datos.finales[mejorCandidato];
            }
        }
        return solucion;
    }

    private static int mejorCandidato(Actividades.Datos datos, Set<Integer> candidatos) {
        int mejorHoraFin = Integer.MAX_VALUE;
        int mejorCandidato = 0;
        for (int candidato : candidatos) {
            int horaFin = datos.finales[candidato];
            if (horaFin < mejorHoraFin) {
                mejorHoraFin = horaFin;
                mejorCandidato = candidato;
            }
        }
        return mejorCandidato;
    }

    public static class Datos {
        int[] inicios;
        int[] finales;
    }
}
/*
3
2
1 3 0 3 6 1
3
1 3 1 3 6 1 4 6 1
7
1 3 1 3 4 2 2 7 1 4 8 0 5 8 2 8 10 2 12 19 1

 */
