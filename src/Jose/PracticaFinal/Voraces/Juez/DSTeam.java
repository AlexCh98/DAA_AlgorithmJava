package Voraces.Juez;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DSTeam {


    public static void main(String[] Args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Double                                                                                                          > modos = new HashMap<>();
        modos.put("ligero", 0.5);
        modos.put("medio", 0.75);
        modos.put("pesado", 1.0);
        int numeroPiezas = Integer.parseInt(reader.readLine());
        double pesoMaximo = Integer.parseInt(reader.readLine());
        String modo = reader.readLine();
        pesoMaximo *= modos.get(modo);
        Datos datos = new Datos();
        datos.nombres = new String[numeroPiezas];
        datos.pesos = new int[numeroPiezas];
        datos.valores = new int[numeroPiezas];
        for (int pieza = 0; pieza < numeroPiezas; pieza++) {
            String[] linea = reader.readLine().split(" ");
            datos.nombres[pieza] = linea[0];
            datos.pesos[pieza] = Integer.parseInt(linea[1]);
            datos.valores[pieza] = Integer.parseInt(linea[2]);
        }
        double[] solucion = llenarMochila(datos, pesoMaximo);
        TreeSet<String> nombres = new TreeSet<>();
        double valorTotal = 0;
        for (int i = 0; i < solucion.length; i++) {
            if (solucion[i] > 0) {
                nombres.add(datos.nombres[i]);
                valorTotal += datos.valores[i] * solucion[i];
            }
        }
        System.out.println(String.format("%.2f", valorTotal).replace(",", "."));
        for (String nombre : nombres) {
            System.out.println(nombre);
        }
    }

    private static double[] llenarMochila(Datos datos, double pesoMaximo) {
        int numeroObjetos = datos.pesos.length;
        double[] solucion = new double[numeroObjetos];
        Set<Integer> candidatos = IntStream.range(0, numeroObjetos).boxed().collect(Collectors.toSet());
        double pesoLibre = pesoMaximo;
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

    public static class Datos {
        String[] nombres;
        int[] pesos;
        int[] valores;
    }
}
/*
5
100
ligero
cinturon 46 77
botas 38 19
casco 15 78
pechera 37 86
malla 17 84

 */