package Ivan.HechasPorMi.DivideYVenceras.EjerciciosResumenes;

import java.util.Arrays;

public class BusqBinariaMergeSort {
    public static int TAM =200;
    public static void main(String[] args) {
        int[] vector = new int[TAM];
        for (int x=0;x<TAM;x++) {
            vector[x] = (int) (Math.random() * 20) + 1;
        }
        int numero=5;
        for (int i = 0; i <TAM ; i++) {
            System.out.print(vector[i]+" ");
        }
        mergesort(vector);
        System.out.println();
        System.out.println("Vector ordenado");
        for (int i = 0; i <TAM ; i++) {
            System.out.print(vector[i]+" ");
        }
        int posicion=busqBinaria(vector,numero);
        if (posicion==-1){
            System.out.println("no esta");
        }else {
            System.out.println();
            System.out.println("Numero:"+numero+" encontrado en la posicion: "+posicion+" que vale: "+vector[posicion]);
        }
    }

    private static void mergesort(int[] vector) {
        if (vector.length<=1)return;
        int mitad=vector.length/2;
        int[] vectorizq = Arrays.copyOfRange(vector,0,mitad);
        int[] vectorder =Arrays.copyOfRange(vector,mitad,vector.length);
        mergesort(vectorder);
        mergesort(vectorizq);
        combinar(vector,vectorizq,vectorder);
        }

    private static void combinar(int[] vector, int[] vectorizq, int[] vectorder) {
        int i=0;
        int j=0;
        for (int k = 0; k < vector.length; k++) {
            if(i>=vectorizq.length){
                vector[k]=vectorder[j];
                j++;
                continue;
            }
            if (j>= vectorder.length){
                vector[k]=vectorizq[i];
                i++;
                continue;

            }
            if (vectorizq[i]<vectorder[j]){
                vector[k]=vectorizq[i];
                i++;
            }
            else {
                vector[k]=vectorder[j];
                j++;

            }
        }
    }

    private static int busqBinaria(int[] vector, int numero) {
    int primero=vector[0];
    int ultimo=vector.length-1;
    if ((numero<vector[0])||(numero>vector[vector.length-1])){
        System.out.println("no esta en el vector");
        return -1;
    }else{
        while (primero<=ultimo ){
            int medio =(primero+ultimo)/2;
            if (numero==vector[medio]){
                return medio;
            }
            else if (numero>vector[medio]){
                primero=medio+1;
            }else{
                ultimo=medio-1;
            }
        }return ultimo;

    }
    }

}
