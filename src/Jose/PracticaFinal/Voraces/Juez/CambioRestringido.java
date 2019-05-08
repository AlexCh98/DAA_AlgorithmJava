package Voraces.Juez;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CambioRestringido {

    private static final int[] MONEDAS = new int[]{500, 200, 100, 50, 20, 10, 5, 2, 1};

    public static void main(String[] Args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numero_casos = Integer.parseInt(reader.readLine());
        String[] linea = reader.readLine().split(" ");
        Map<Integer, Integer> cantidades = new HashMap<>();
        int maximo_cambio = 0;
        int cantidad_monedas = 0;
        for (int i = 0; i < linea.length; i++) {
            int cantidad = Integer.parseInt(linea[i]);
            cantidades.put(MONEDAS[i], cantidad);
            maximo_cambio += MONEDAS[i] * cantidad;
            cantidad_monedas += cantidad;
        }
        for (int i = 0; i < numero_casos; i++) {
            int cambio = Integer.parseInt(reader.readLine());
            if (cambio == 0) System.out.println(0);
            else if (cambio == maximo_cambio) System.out.println(cantidad_monedas);
            else if (cambio > maximo_cambio) System.out.println(-1);
            else System.out.println(calcularNumeroMonedas(cantidades, cambio));
        }
    }

    private static int calcularNumeroMonedas(Map<Integer, Integer> cantidades, int cambio) {
        int numero_monedas = 0;
        List<Integer> candidatos = Arrays.stream(MONEDAS).boxed().collect(Collectors.toList());
        int cambio_devuelto = 0;
        while (!candidatos.isEmpty() && cambio_devuelto < cambio) {
            int moneda = candidatos.stream().max(Integer::compareTo).get();
            int cantidad = cantidades.get(moneda);
            if (cantidad > 0 && moneda <= (cambio - cambio_devuelto)) {
                int monedas_a_devolver = Math.min(cantidad, (cambio - cambio_devuelto) / moneda);
                numero_monedas += monedas_a_devolver;
                cambio_devuelto += monedas_a_devolver * moneda;
            }
            candidatos.remove((Integer) moneda);
        }
        if (cambio != cambio_devuelto) return -1;
        return numero_monedas;
    }

}