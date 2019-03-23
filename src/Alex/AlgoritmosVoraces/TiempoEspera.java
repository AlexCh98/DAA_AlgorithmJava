import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/*  ####MÍNIMO TIEMPO DE ESPERA EN EL SISTEMA####
    -- Supongamos un servidor que tiene que dar servicio a n clientes (procesador, cajero, …).
       El tiempo requerido por cada cliente ti es conocido.
    -- Se desea minimizar el tiempo medio de cada cliente en el sistema.

    -- ¿Qué tenemos?
            -- Conjunto de candidatos: los n clientes.
            -- Función solución: todos los clientes han sido ordenados.
            -- Función de factibilidad: si han sido ordenados los clientes o no.
            -- Función objetivo: minimizar tiempo (T).
            -- Función de selección: Elegir los candidatos por orden creciente de tiempo.
                -- El algoritmo voraz se reduce a ordenar de forma no decreciente en ti los n clientes.
*/
public class TiempoEspera {

    private static int getBestTask(Set<Integer> candidates, double[] tasks) {
        double bestTimeTask = Integer.MAX_VALUE;
        int bestTask = 0;

        for (int c : candidates) {
            double time = tasks[c];
            if (time < bestTimeTask) {
                bestTimeTask = time;
                bestTask = c;
            }
        }
        return bestTask;
    }

    public static int[] greedyAlgorithmWT(double[] tasks) {
        int n = tasks.length;
        Set<Integer> candidates = new HashSet<>(n);
        for (int i = 0; i < n; i++) {
            candidates.add(i);
        }
        int[] sol = new int[n];
        int i = 0;
        while (!candidates.isEmpty()) {
            int bestTask = getBestTask(candidates, tasks);
            candidates.remove(bestTask);
            sol[i] = bestTask;
            i++;
        }
        return sol;
    }

    public static void main(String[] args) {
        int n = 10;
        double[] arrayTiempoTareas = new double[n];
        for (int i = 0; i < n; i++) {
            arrayTiempoTareas[i] = Math.round(Math.random() * 96 + 44);
            System.out.print(arrayTiempoTareas[i] + " ");

        }
        int[] sol = greedyAlgorithmWT(arrayTiempoTareas);
        double sumaFinal = 0;
        System.out.println();
        double[] suma = new double[n];
        boolean primero = true;
        for(int i = 0; i < n; i++){
            System.out.print(sol[i] + " ");
            if(i==0){
                suma[0] = arrayTiempoTareas[sol[0]];
            }else{
                suma[i] = suma[i-1] + arrayTiempoTareas[sol[i]];
            }
            sumaFinal += suma[i];
            System.out.println(", Suma: "+suma[i]);

        }
            /*for (int t : sol) {
                System.out.print(t + " ");
                if (primero) {
                    primero = false;
                    sumWaitingTime = arrayTiempoTareas[t];
                } else {
                    sumWaitingTime += arrayTiempoTareas[t];
                }
            }*/
        System.out.println();
        System.out.println("SUM: "+sumaFinal);
        System.out.println("AVG: " + sumaFinal / n);
    }
}

    /*public static void main(String[] args){
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
    }*/

