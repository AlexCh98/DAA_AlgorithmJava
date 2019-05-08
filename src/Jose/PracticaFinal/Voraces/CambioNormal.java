package Voraces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CambioNormal {

    public static void main(String[] Args) {
        int[] monedas = new int[]{1, 2, 5, 10, 20, 50, 100};
        int numero_de_casos = 5;
        int maximo_cambio = 300;
        for (int i = 0; i < numero_de_casos; i++) {
            int cantidad = (int) (Math.random()*maximo_cambio)+1;
            System.out.println("Cambio a devolver: " + cantidad + " con monedas de: " + Arrays.toString(monedas));
            int[] cambio = devolverCambio(cantidad, monedas);
            imprimirCambio(cambio, monedas);
        }
    }

    private static void imprimirCambio(int[] cambio, int[] monedas) {
        StringBuffer sb = new StringBuffer();
        IntStream.range(0, monedas.length).forEachOrdered(i -> sb.append(cambio[i]).append(" monedas de ").append(monedas[i]).append("\n"));
        System.out.println(sb);
    }

    private static int[] devolverCambio(int cantidad, int[] monedas) {
        int numero_monedas = monedas.length;
        int[] cambio = new int[numero_monedas];
        List<Integer> candidatos = Arrays.stream(monedas).boxed().collect(Collectors.toList());
        while (!candidatos.isEmpty() && cantidad >= 0) {
            int moneda = candidatos.stream().mapToInt(o -> o).max().getAsInt();
            if (moneda <= cantidad) {
                int cambio_monedas = cantidad / moneda;
                cantidad -= cambio_monedas * moneda;
                cambio[candidatos.indexOf(moneda)] = cambio_monedas;
            }
            candidatos.remove((Integer) moneda);
        }
        return cambio;
    }
}
