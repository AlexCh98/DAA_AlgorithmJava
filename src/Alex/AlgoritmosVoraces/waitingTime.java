package Alex.AlgoritmosVoraces;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class waitingTime {
    public static void main(String[] args){
        int n = 10;
        double[] tasks = new double[n];
        for(int i = 0; i < n; i++){
            tasks[i] = Math.random()*96+44;
        }
        System.out.println(Arrays.toString(tasks));
        int [] sol = greedyAlgorithm(tasks);
        System.out.println(Arrays.toString(sol));
    }

    public static int[] greedyAlgorithm(double[] array){
        int n = array.length;
        Set<Integer> candidatos = new HashSet<>();
        for(int i=0;i<n;i++){
            candidatos.add(i);
        }
        int[] solucion = new int[n];
        int i = 0;
        while(!candidatos.isEmpty()) {
            int esfactible = esFactible(candidatos, array);
            candidatos.remove(esfactible);
            solucion[i] = esfactible + 1;
            i++;
        }
        return solucion;
    }

    public static int esFactible(Set<Integer> candidatos, double[] array){
        double maximoValor = Integer.MAX_VALUE;
        int mejorOpcion = 0;
        for(int cand: candidatos){
            double valor = array[cand];
            if(valor < maximoValor){
                maximoValor = valor;
                mejorOpcion = cand;
            }
        }
        return mejorOpcion;
    }
}
