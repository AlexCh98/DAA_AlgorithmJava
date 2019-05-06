package Dani.Finales;

import java.util.*;

public class TorresAgua {

    public static void main(String[] args){
        int[] alturas = {4,0,7,3};
        int[] bases = {2,3,1,1};
        int maxNivAgua = 5;
        long solucionVolumen = calculateVolume(2,alturas,bases);
        System.out.println(solucionVolumen);
        System.out.println("-----------------");
        int nivelLlenado = execute(solucionVolumen, alturas, bases, maxNivAgua);
        System.out.println(nivelLlenado);
    }

    public static long calculateVolume(int level, int[]alturas, int[]bases){
        boolean[] candidatos = new boolean[alturas.length];
        Arrays.fill(candidatos,true);

        long volumenTotal = 0;

        for(int i=0; i<bases.length; i++){ System.out.println("Min: "+ Math.min(level,alturas[i]+1));
            volumenTotal += bases[i] * Math.min(level,alturas[i]+1);
        }
        return volumenTotal;
    }

    public static int execute(long volumen, int[] alturas, int[]bases, int NivelMax){
        //Búsqueda binaria
        int min = 0;
        int max = NivelMax;

        while(min <= max){
            int medio = (min+max)/2;
            long volumenMedio = calculateVolume(medio,alturas,bases);

            if(volumenMedio == volumen){
                return medio;
            }else if (volumenMedio > volumen) {
                min = medio+1;
            }else{
                max = medio-1;
            }
        }

        return max;
    }

    public static boolean esFactible(int torre, int nivelActual, int[]alturas, boolean[] candidatos){
        return ((alturas[torre] >= nivelActual)&&(candidatos[torre]));
    }
}
