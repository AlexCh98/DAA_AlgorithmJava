import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeSet;

public class ProblemaCambioMonedas {
    final static int[] valores = new int[]{500, 200, 100, 50, 20, 10, 5, 2, 1};

    public static void main(String[] Args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int numero_casos = Integer.parseInt(bf.readLine());
        String[] linea = bf.readLine().split(" ");
        HashMap<Integer, Integer> monedas = new HashMap<>(linea.length);//<Valor, cantidad>
        int maximo_cambio = 0;
        int cantidad_monedas = 0;
        for (int i = 0; i < linea.length; i++) {
            int cantidad = Integer.parseInt(linea[i]);
            monedas.put(valores[i], cantidad);
            maximo_cambio += valores[i] * cantidad;
            cantidad_monedas += cantidad;
        }
        for (int i = 0; i < numero_casos; i++) {
            int cambio = Integer.parseInt(bf.readLine());
            if (cambio == 0) {
                System.out.println(0);
            } else if (cambio > maximo_cambio) {
                System.out.println(-1);
            } else if (cambio == maximo_cambio) {
                System.out.println(cantidad_monedas);
            } else {
                int numero_monedas = calcularCambio(monedas, cambio);
                System.out.println(numero_monedas);
                //System.out.println("Cambio: " + cambio + " numero de monedas: " + numero_monedas);
            }
        }
        //int cambio = 41;
        //int numero_monedas = calcularCambio(monedas, cambio);
        //System.out.println("Cambio: " + cambio + " numero de monedas: " + numero_monedas);
    }

    private static int calcularCambio(HashMap<Integer, Integer> monedas, int cambio) {
        int cantidad_monedas = 0;
        TreeSet<Integer> candidatos = new TreeSet<>(Collections.reverseOrder());
        for (int moneda : valores) {
            candidatos.add(moneda);
        }
        int cambio_devuleto = 0;
        while (!candidatos.isEmpty() && cambio_devuleto < cambio) {
            int mejorMoneda = candidatos.first();
            //System.out.println("Mejor moneda: " + mejorMoneda);
            candidatos.remove(mejorMoneda);
            int monedaTengo = monedas.get(mejorMoneda);
            //System.out.println("De la mejor moneda tengo: " + monedaTengo);
            //System.out.println("MonedaTengo > 0: " + (monedaTengo >0) + "\t (cambio - cambio_devuleto) >= mejorMoneda: " + ((cambio - cambio_devuleto) > mejorMoneda));
            if (monedaTengo > 0 && (cambio - cambio_devuleto) >= mejorMoneda) {//Mientras la moneda sea factible
                int numero_monedas = Math.min(monedaTengo, (cambio - cambio_devuleto) / mejorMoneda);
                //System.out.println("Esta moneda es factible cojo " + numero_monedas);
                cantidad_monedas += numero_monedas;
                //System.out.println("Actualizo cantidad de monedas " + cantidad_monedas);
                cambio_devuleto += numero_monedas * mejorMoneda;
                //System.out.println("Actualizo el cambio que llevo a " + cambio_devuleto);
            /*}else{
                //System.out.println("Esta moneda no es factible");
            */
            }
        }
        if (cambio_devuleto != cambio) return -1;
        return cantidad_monedas;
    }
}
