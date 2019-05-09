package Ivan.MierdaPaBorrar;

import java.util.Arrays;

public class busBinMergSort {
    public static int TAM =200;
    public static void main(String[] args) {
        int [] vector =new int [TAM];
        for (int x = 0; x < TAM; x++) {
            vector[x]= (int) (Math.random() * 20) + 1;
        }
        for (int i = 0; i < TAM; i++) {
            System.out.print(vector[i] + " ");
        }
        msort(vector);
        System.out.println();
        for (int i = 0; i < TAM; i++) {
            System.out.print(vector[i] + " ");
        }
        int numero=3;
        int posicion=busbin(vector,numero);
        System.out.println("numero encontrado posicion : "+posicion+" que vale: "+vector[posicion]);
    }

    private static int busbin(int[] vector, int numero) {
        int primero =vector[0];
        int ultimo = vector[vector.length-1];
        if ((numero<vector[0])||(numero>vector[vector.length-1])){
            System.out.println("no esta en el vector");
            return -1;
        }
        else{
            while(primero<=ultimo){
                int mitad=(primero+ultimo)/2;
                if (numero==vector[mitad]){
                    return mitad;
                }
                if(vector[mitad]<numero){
                    primero=mitad+1;
                }
                else{
                    ultimo=mitad-1;
                }
            }
            return ultimo;
        }

    }

    private static void msort(int[] vector) {
        if (vector.length<=1)return;
        int mitad= vector.length/2;
        int[] izq= Arrays.copyOfRange(vector,0,mitad);
        int[] der= Arrays.copyOfRange(vector,mitad,vector.length);
        msort(izq);
        msort(der);
        merge(vector,izq,der);

    }

    private static void merge(int[] vector, int[] izq, int[] der) {
        int i=0;
        int j=0;
        for (int k = 0; k < vector.length; k++) {
            if(i>=izq.length){
                vector[k]=der[j];
                j++;
                continue;
            }
            if (j>=der.length){
                vector[k]=izq[i];
                i++;
                continue;
            }
            if(izq[i]<der[j]){
                vector[k]=izq[i];
                i++;
            }else{
                vector[k]=der[j];
                j++;
            }

        }
    }
}
