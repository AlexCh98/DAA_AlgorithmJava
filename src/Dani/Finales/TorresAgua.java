package Dani.Finales;

import java.util.*;

public class TorresAgua {

    public static void main(String[] args){
        int[] alturas = {4,0,7,3};
        int[] bases = {2,3,1,1};

        long solucionVolumen = calculateVolume(1,alturas,bases);
        System.out.println(solucionVolumen);
    }

    public static long calculateVolume(int level, int[]alturas, int[]bases){
        boolean[] candidatos = new boolean[alturas.length];
        Arrays.fill(candidatos,true);

        long volumenTotal = 0;
        int nivelActual = 0;
        while(nivelActual <= level){

            for (int i = 0; i < candidatos.length ; i++) {
                if(esFactible(i, nivelActual, alturas, candidatos)){
                    volumenTotal += bases[i];
                }else{
                    candidatos[i] = false;
                }
            }

            nivelActual++;
        }

        return volumenTotal;
    }

    public static boolean esFactible(int torre, int nivelActual, int[]alturas, boolean[] candidatos){
        return ((alturas[torre] >= nivelActual)&&(candidatos[torre]));
    }
}
