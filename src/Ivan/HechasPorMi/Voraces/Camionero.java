package Ivan.HechasPorMi.Voraces;

import java.util.ArrayList;
import java.util.Scanner;

public class Camionero {
    public static void main(String[] args) {
        Scanner scaner = new Scanner(System.in);
        System.out.println("Introduzca capacidad de deposito:");
        int deposito = scaner.nextInt();
        System.out.println("Cuantas gasolineras hay");
        int nGasolineras = scaner.nextInt();
        ArrayList<Integer> dgasolinera= new ArrayList<Integer>();
        ArrayList<Integer> solucion= new ArrayList<Integer>();
        int distancia=0;
        for (int i = 0; i < nGasolineras; i++) {
            System.out.println("Introduzca distancia a la gasolinera "+i+":");
            dgasolinera.add(scaner.nextInt());
            distancia = distancia+dgasolinera.get(i);
        }
        while (!dgasolinera.isEmpty()){
            algVoraz(deposito,dgasolinera,solucion);
        }
        System.out.println("Paro en:"+solucion.size()+"gasolineras");
        for (int i = 0; i <solucion.size() ; i++) {
            System.out.println(solucion.get(i));
        }

        }
    public static ArrayList<Integer> algVoraz (int dep,ArrayList<Integer> dgasolineras,ArrayList<Integer> solucion){
        int suma=0;
        for (int i = 0; i <dgasolineras.size()+1 ; i++) {
            if ((suma+dgasolineras.get(i))<dep){
                suma = suma + dgasolineras.get(i);
                dgasolineras.remove(i);
            }else {
                solucion.add(dgasolineras.get(i-1));
                dgasolineras.remove(i-1);
            }
        }return solucion;
    }
}
