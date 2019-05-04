package Alex.AlgoritmosDyV;

import java.util.Arrays;

public class NumeroMayoritario {
    //Un numero mayoritario es aquel que está repetido más de n/2 veces.

    public static void main(String[] asdf) {
        int[] v1 = {1, 1, 2};
        int[] v2 = {1, 2, 3, 5, 5, 4, 5, 5, 6, 5, 8};
        System.out.println(tieneNumeroPredominante(v2));
        System.out.println(tieneNumeroPredominante(v1));

    }

    public static boolean tieneNumeroPredominante(int vector[]) {
        int[] copia = Arrays.copyOf(vector,vector.length);
        if(tieneCandidato(copia,copia.length)){
            int candidato = copia[0];
            int contador = 0;
            for(int elem: vector){
                if(candidato == elem){
                    contador++;
                }
            }
            return contador > vector.length / 2; //Si algun contador es mayor que n /2.
        }
        return false;
    }

    private static boolean tieneCandidato(int[] copia, int length) {
        if(length == 1){
            return true;
        }
        if(length == 0){
            return false;
        }
        if(length % 2 == 0){
            int k = 0;
            for (int i = 0; i < length; i+=2) {
                if(copia[i] == copia[i+1]){
                    copia[k] = copia[i];
                    k++;
                }
            }
            length = k;
            return tieneCandidato(copia,length);
        }else {
            if(!tieneCandidato(copia,length-1)){
                copia[0] = copia[length-1];
            }
            return true;
        }
    }
}
