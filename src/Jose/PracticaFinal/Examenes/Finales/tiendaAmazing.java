package Examenes.Finales;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class tiendaAmazing {
    public static void main(String[] args) {
        double[] precios = {2.15,2.75,3.75,3.35,4.20,5.80,6.55};

        double caso1 = 15.05;
        double caso2 = 7.95;
        double caso3 = 13.8;
        Set<Integer> solucion = execute(caso2,precios);

        if(solucion == null){
            System.out.println("No se puede saber cual es el pedido");
        }else{
            System.out.println("Hay solución");
        }

    }

    public static Set<Integer> execute(double precioTotal, double [] precios){
        List<Set<Integer>> soluciones = new ArrayList<>();
        Set<Integer> objetos = new HashSet<>();
        calculo(precioTotal, precios,objetos, 0, soluciones, 0 );
        if(soluciones.size()!= 1){
            return null;
        }else {
            return soluciones.get(0);
        }
    }

    private static void calculo(double precioTotal, double[] precios, Set<Integer> obejtos, double precioActual, List<Set<Integer>> soluciones, int producto) {
        if (soluciones.size() < 2 && producto < precios.length) {
            if (precioActual == precioTotal) {
                soluciones.add(new HashSet<>(obejtos));
            }
        } else {
            if (!obejtos.contains(producto) && Math.round((precioActual + precios[producto]) * 100) / 100.0 <= precioTotal) {
                obejtos.add(producto);
                calculo(precioTotal, precios, obejtos, precioActual + precios[producto], soluciones, producto + 1);
                obejtos.remove(producto);
            }
            calculo(precioTotal, precios, obejtos, precioActual, soluciones, producto + 1);
        }
    }
}
