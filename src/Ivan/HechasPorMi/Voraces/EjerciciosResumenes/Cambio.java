package Ivan.HechasPorMi.Voraces.EjerciciosResumenes;

import java.util.ArrayList;
import java.util.Scanner;

public class Cambio {

    public static void main(String[] args) {
        int[] valores = new int[]{500, 200, 100, 50, 20, 10, 5, 2, 1};
        Scanner scaner = new Scanner(System.in);
        System.out.println("¿Cuanto te has gastado?");
        int precio = scaner.nextInt();
        System.out.println("¿Cuanto has pagado?");
        int pagado = scaner.nextInt();
        int cambio =pagado-precio;
        ArrayList<Integer> solucion = new ArrayList<>();
        greedyCambio(cambio,valores,solucion);
        for (int i = 0; i < solucion.size(); i++) {
            System.out.println(solucion.get(i));
        }
    }

    public static void greedyCambio(int cambio,int[] valores,ArrayList<Integer> solucion) {
        int aux = 0;
        while (cambio > aux){
            for (int i = 0; i < valores.length; i++){
                if ((valores[i]<=cambio)&&((aux+valores[i])<=cambio)){
                    aux=aux+valores[i];
                    System.out.println("aux = " + aux);
                    solucion.add(valores[i]);
                }

            }break;
        }
    }
}
