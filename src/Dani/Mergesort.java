package poo;

import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Mergesort {

    public static void main(String[] args) throws IOException {

        //FileReader f = new FileReader("entradaMergesort");
        Scanner scan = new Scanner(System.in);
        Random r = new Random();
        //Leer los datos

        int longitud = 100;
        int[] miArray = new int[longitud];

        for(int i=0; i < longitud; i++){
            miArray[i] = r.nextInt(100);
        }

        //Ordenar
        int[] sol = mergeSort(miArray, 0, miArray.length-1);

        //Mostrar la solución
        for(int i=0; i<sol.length; i++){
            System.out.print(sol[i]+" ");
        }
    }

    public static int[] mergeSort(int[] miArray, int inicio, int fin){

        if(fin == inicio){ //Si llega al caso base (un array de tamaño 1) devuelve ese array
            int[] min = {miArray[fin]};
            return min;

        }else {

            int medio = (inicio + fin) / 2;

            //Fisión
            int[] mitad1 = mergeSort(miArray, inicio, medio);
            int[] mitad2 = mergeSort(miArray, medio + 1, fin);

            //Una vez se ha dividido comienza la fuuuuuuusiooón
            return fusionar(mitad1,mitad2);
        }
    }

    public static int[] fusionar(int[] mitad1, int[] mitad2){

        //Se la miden
        int longitud1 = mitad1.length;
        int longitud2 = mitad2.length;

        int[] sol = new int[longitud1+longitud2]; //Unen las longitudes guiño guiño

        int punt1=0; //Para recorrer la mitad 1
        int punt2=0; //Para recorrer la mitad 2
        int i=0; //Para recorrer la solución

        while(punt1 < longitud1 && punt2 < longitud2){ //Recorremos las mitades hasta llegar al final de una de ellas guardando el resultado ordenado
            //Ordena en orden ascendente
            if(mitad1[punt1] <= mitad2[punt2]){ //Si el número apuntado en la mitad 1 es menor que el apuntado en la mitad 2 se mete en la solución el de la mitad 1
                System.out.print("Pim ");
                sol[i] = mitad1[punt1];
                punt1++;
                i++;
            }else{
                System.out.print("Pam ");
                sol[i] = mitad2[punt2];
                punt2++;
                i++;
            }
        }

        //Hemos llegado al fin de una de las dos mitades así que rellenamos la solución con lo que queda de la otra mitad

        if(punt1 == longitud1){ //Caso 1: La mitad 1 ya ha llegado

            for(int j=punt2; j<longitud2; j++){ //Recorro lo que queda de la mitad 2 para que llegue mientras lo meto en la solución
                System.out.print("Trucu ");
                sol[i] = mitad2[j];
                i++;
            }

        }else{ //Caso 2: La mitad 2 ya ha llegado

            for(int j=punt1; j<longitud1; j++){ //Recorro lo que queda de la mitad 1 para que llegue mientras lo meto en la solución
                System.out.print("Ratatata ");
                sol[i] = mitad1[j];
                i++;
            }
        }

        System.out.println();

        return sol;
    }
}
