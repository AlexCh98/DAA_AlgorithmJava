package Dani.Finales;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TiendaAmazing {

    public static void main(String[] args){

        double[] precios = {2.15,2.75,3.75,3.35,4.20,5.80,6.55};

        double caso1 = 15.05; //Da una solución (lo hace bien
        double caso2 = 7.95; //Hay dos soluciones pero por la suma del double A+F = 7.9499999999999999
        double caso3 = 13.8; //No hay solución (lo hace bien)
        Set<Integer> solucion = execute(caso3,precios);

        if(solucion == null){
            System.out.println("No se puede saber cual es el pedido");
        }else{
            System.out.println("Hay solución");
        }
    }

    public static Set<Integer> execute(double precioTotal, double[] precios){
        Set<Integer> carrito = new HashSet<>();
        List<Set<Integer>> soluciones = new ArrayList<>();
        btProductos(precioTotal, precios, carrito, 0, soluciones, 0);

        System.out.println("-----------------------------------");
        for(Set<Integer> s: soluciones){
            for(int i: s){
                System.out.print(i +" ");
            }
            System.out.println("");
        }

        if(soluciones.size() != 1){
            return null;
        }else{
            return soluciones.get(0);
        }
    }

    public static void btProductos(double precioTotal, double[] precios, Set<Integer> carrito, double precioActual, List<Set<Integer>> soluciones, int producto){
        if(soluciones.size() < 2 && producto<precios.length){
            //Hemos llegado al caso base
            if (precioActual == precioTotal) {
                System.out.println("Solucion ");
                //Lo copio porque si guardo carrito en la solucion lo hace por referencia y se cambia también el que está guardado en la lista
                Set<Integer> copia = new HashSet<>();
                for(int i: carrito){
                    copia.add(i);
                }
                soluciones.add(copia);

                //No es caso base
            }else{
                //Árbol binario
                System.out.println("Precio actual: "+precioActual);
                //Lo cojo (solo si puedo)
                if(esFactible(precios, producto, precioActual, precioTotal, carrito)) {
                    //Guardo en el carrito el índice del producto
                    System.out.println("Meto el: " + producto);
                    carrito.add(producto);
                    btProductos(precioTotal, precios, carrito, precioActual + precios[producto], soluciones, producto + 1);
                    carrito.remove(producto);
                    System.out.println("Quito el: " + producto);
                }
                //No lo cojo
                //System.out.println("No meto el: "+ producto);
                btProductos(precioTotal, precios, carrito, precioActual, soluciones, producto+1);
            }
        }
    }

    public static boolean esFactible(double[] precios, int producto, double precioActual, double precioTotal, Set<Integer> carrito){
        return (!carrito.contains(producto) && (precioActual+precios[producto] <= precioTotal));
    }
}
