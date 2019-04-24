package Dani.EjsQuePasaLucas;

import java.util.*;

public class subVectoresSumaIgual {

    public static void main(String[] args){

        int[] vector = new int[6];
        Random r = new Random();
        /*for(int i=0; i<6; i++){
           vector[i] = r.nextInt(100)+1;}*/
        vector[0] = 1;
        vector[1] = 2;
        vector[2] = 3;
        vector[3] = 4;
        vector[4] = 5;
        vector[5] = 9;
        Set<Integer> subUno = new HashSet<>();
        Set<Integer> subDos = new HashSet<>();
        partirEnDos(vector, 0, subUno, subDos);

    }

    public static void partirEnDos(int[] v, int i, Set<Integer> subUno, Set<Integer> subDos){
        if(i < v.length){

            subUno.add(i);
            partirEnDos(v, i+1, subUno, subDos);
            subUno.remove(i);

            subDos.add(i);
            partirEnDos(v, i+1, subUno, subDos);
            subDos.remove(i);

        }else if(i==v.length){
            //Calcular conjunto uno
            int sumaUno = 0;
            for(int e: subUno){
                sumaUno += v[e];
            }

            //Calcular conjunto dos
            int sumaDos = 0;
            for(int e: subDos){
                sumaDos += v[e];
            }

            if(sumaUno == sumaDos){
                System.out.print("({");
                for(int e: subUno){
                    System.out.print(v[e]+", ");
                }
                System.out.print("},{");
                for(int e: subDos){
                    System.out.print(v[e]+", ");
                }
                System.out.println("})");
            }
        }
    }



}
