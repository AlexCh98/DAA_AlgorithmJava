package Voraces.Juez;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FinalStory {
    public static void main(String[] Args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numeroCombates = Integer.parseInt(reader.readLine());
        for (int combate = 0; combate < numeroCombates; combate++) {
            int ataqueJugador = Integer.parseInt(reader.readLine());
            int numeroEnmigos = Integer.parseInt(reader.readLine());
            Datos datos = new Datos();
            String[] danos = reader.readLine().split(" ");
            String[] vidas = reader.readLine().split(" ");
            datos.dano = Arrays.stream(danos).mapToInt(Integer::parseInt).toArray();
            datos.vida = Arrays.stream(vidas).mapToInt(Integer::parseInt).toArray();
            int danoToatal = Arrays.stream(datos.dano).sum();
            long danoRecibido = calcularDano(datos, ataqueJugador, numeroEnmigos, danoToatal);
            System.out.println(danoRecibido);
        }
    }

    private static long calcularDano(Datos datos, int ataqueJugador, int numeroEnemigos, int danoTotal) {
        long dano = 0;
        Set<Integer> candidatos = IntStream.range(0, datos.dano.length).boxed().collect(Collectors.toSet());
        while (!candidatos.isEmpty()) {
            int mejorCandidato = mejorCandidato(datos, candidatos, ataqueJugador);
            candidatos.remove(mejorCandidato);
            int vidaMejor = datos.vida[mejorCandidato];
            int danoMejor = datos.dano[mejorCandidato];
            int turnos = vidaMejor % ataqueJugador == 0 ? vidaMejor / ataqueJugador : vidaMejor / ataqueJugador + 1;
            dano += turnos * danoTotal;
            danoTotal -= danoMejor;
        }
        return dano;
    }

    private static int mejorCandidato(Datos datos, Set<Integer> candidatos, int ataqueJugador) {
        int mejorCandidato = 0;
        double mejorRatio = Integer.MIN_VALUE;
        for (int candidato : candidatos) {
            int dano = datos.dano[candidato];
            int vida = datos.vida[candidato];
            int turnos = vida % ataqueJugador == 0 ? vida / ataqueJugador : vida / ataqueJugador + 1;
            double ratio = dano / turnos;
            if (ratio > mejorRatio) {
                mejorCandidato = candidato;
                mejorRatio = ratio;
            } else if (ratio == mejorRatio && dano > datos.dano[mejorCandidato]) {
                mejorCandidato = candidato;
                mejorRatio = ratio;
            }
        }
        return mejorCandidato;
    }

    private static class Datos {
        int[] dano;
        int[] vida;
    }
}
/*
2
10
3
10 10 10
30 10 20
5
3
15 5 10
10 10 10

3
50
3
10 50 100
100 300 100
50
3
10 500 200
1 50 100
50
3
2000 200
1000 100

 */