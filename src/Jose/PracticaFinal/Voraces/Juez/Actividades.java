package Voraces.Juez;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Actividades {

    public static class Datos{
        int [] inicios;
        int [] finales;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numeroCasos = Integer.parseInt(reader.readLine());
        Datos datos = new Datos();
        for (int caso = 0; caso < numeroCasos; caso++) {
            int numeroActividades = Integer.parseInt(reader.readLine());
            String[] linea = reader.readLine().split(" ");
            datos.inicios = new int[numeroActividades];
            datos.finales = new int[numeroActividades];
            for (int i = 0; i < linea.length; i++) {
                int valor = Integer.parseInt(linea[i]);
                if (i % 2 == 0) {
                    datos.inicios[i / 2] = valor;
                } else {
                    datos.finales[i / 2] = valor;
                }
            }
            int solucion = horario(datos);
            System.out.println(solucion);
        }
    }

    private static int horario(Datos datos) {
        int numeroAcitvidades = datos.inicios.length;
        Set<Integer> candidatos = IntStream.range(0, numeroAcitvidades).boxed().collect(Collectors.toSet());
        int fin = 0;
        int solucion = 0;
        while(!candidatos.isEmpty()){
            int mejorCandidato = mejorCandidato(datos, candidatos);
            candidatos.remove(mejorCandidato);
            if(datos.inicios[mejorCandidato] >= fin){
                solucion++;
                fin = datos.finales[mejorCandidato];
            }
        }
        return solucion;
    }

    private static int mejorCandidato(Datos datos, Set<Integer> candidatos) {
        int mejorHoraFin = Integer.MAX_VALUE;
        int mejorCandidato = 0;
        for (int candidato : candidatos) {
            int horaFin = datos.finales[candidato];
            if(horaFin < mejorHoraFin){
                mejorHoraFin = horaFin;
                mejorCandidato = candidato;
            }
        }
        return mejorCandidato;
    }
}
/*
4
2
1 5 2 7
3
1 2 2 3 3 4
3
1 3 1 2 2 3
7
1 4 3 6 2 8 4 8 5 10 8 12 12 20

 */