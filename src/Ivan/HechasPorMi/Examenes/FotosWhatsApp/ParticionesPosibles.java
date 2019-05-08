package Ivan.HechasPorMi.Examenes.FotosWhatsApp;

import java.util.ArrayList;
import java.util.TreeSet;

public class ParticionesPosibles {
    public static int TAM=12;
    public static void main(String[] args){
        int[] vector = new int[TAM];
        for (int x=0;x<TAM;x++) {
            vector[x] = (int) (Math.random() * 20) + 1;
        }
        TreeSet<Integer> subUno = new TreeSet<>();
        TreeSet<Integer> subDos = new TreeSet<>();
        ArrayList<TreeSet<Integer>> soluciones = new ArrayList<>();
        partirEnDos(soluciones,vector,0,subUno,0,subDos,0);
        System.out.println("Hay: "+soluciones.size()+" soluciones");

    }

    private static void partirEnDos(ArrayList<TreeSet<Integer>> soluciones,int[] vector, int contador, TreeSet<Integer> conjunto1, int suma1,TreeSet<Integer> conjunto2,int suma2) {
        if(contador < vector.length){//Si no hemos llegado al final
            conjunto1.add(vector[contador]);
            suma1 += vector[contador];
            partirEnDos(soluciones,vector,contador+1,conjunto1,suma1,conjunto2,suma2);
            suma1 -= vector[contador];
            conjunto1.remove(vector[contador]);

            conjunto2.add(vector[contador]);
            suma2 += vector[contador];
            partirEnDos(soluciones,vector,contador+1,conjunto1,suma1,conjunto2,suma2);
            suma2 -= vector[contador];
            conjunto2.remove(vector[contador]);
        }else{
            if(suma1 == suma2){
                soluciones.add(conjunto1);
            }
        }
    }
}