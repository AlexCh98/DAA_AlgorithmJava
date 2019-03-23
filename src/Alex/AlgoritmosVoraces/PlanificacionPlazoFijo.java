import java.util.*;

/*  ####PLANIFICACIÓN PLAZO FIJO####
    -- Supongamos que tenemos n trabajos, donde cada trabajo i tiene una fecha
       tope de realización fi > 0 y un beneficio bi >0.
    -- Para cualquier trabajo i, el beneficio bi se gana si y sólo sí se realiza
       antes (o coincidiendo) con su fecha tope fi.
    -- El trabajo se realiza en una máquina que consume una unidad de tiempo y
       sólo hay una máquina disponible (i.e., en un instante de tiempo sólo se
       puede ejecutar una tarea).
    -- ¿Qué tenemos?
            -- Conjunto de candidatos: los n trabajos a realizar.
            -- Función solución: cuando se haya planificado todas las tareas.
            -- Función de factibilidad: conjunto T de trabajos que todavía se pueden completar antes de su tope.
            -- Función objetivo: maximizar beneficio.
            -- Función de selección: Considerar trabajos en orden decreciente de los beneficios.
 */
public class PlanificacionPlazoFijo {
    public static final int FREE_DAY = -1;

    public static class Data {
        int[] deadline;
        double[] profit;
    }

    private static int getBestItem(Data data, Set<Integer> cand) {
        double bestProfit = 0;
        int bestItem = 0;
        for (int i : cand) {
            double profit = data.profit[i];
            if (profit > bestProfit) {
                bestProfit = profit;
                bestItem = i;
            }
        }
        return bestItem;
    }



    public static int[] greedyAlgorithmSC(Data data) {
        int n = data.profit.length;
        Set<Integer> candidates = new HashSet<>(n);
        for (int i = 0; i < n; i++) {
            candidates.add(i);
        }
        int tareaCaducaUltima = 0;
        //VEMOS QUE TAREA ACABA LA ULTIMA, EN CASO DE HABER VARIAS COGEMOS LA DE MAYOR Nº TAREA
        for (int i = 0; i < data.profit.length; i++) {
            tareaCaducaUltima = Math.max(tareaCaducaUltima, data.deadline[i]);
        }
        System.out.println("Last Date: "+tareaCaducaUltima);
        //Creamos un array del nº maximoCaducaUltima +1, ya que como mucho habrá esas tareas.
        int schedule[] = new int[tareaCaducaUltima + 1];
        //Rellenamos el array con -1 (dia vacio)
        Arrays.fill(schedule, FREE_DAY);

        while (!candidates.isEmpty()) {
            int bestItem = getBestItem(data, candidates);
            candidates.remove(bestItem);
            for (int i = data.deadline[bestItem]; i >= 0; i--) {
                if (schedule[i] == FREE_DAY) {
                    schedule[i] = bestItem;
                    break;
                }
            }

        }
        return schedule;
    }

    public static void printSol(Data data, int[] sol) {
        System.out.println("Task\tProfit\tDate\tDeadline");
        double totalProfit = 0;
        for (int i = 0; i < sol.length; i++) {
            int task = sol[i];
            if (task != FREE_DAY) {
                System.out.println(task + "\t\t" + data.profit[task] + "\t" + i + "\t\t" + data.deadline[task]);
                totalProfit += data.profit[task];
            }
        }
        System.out.println("PROFIT: "+totalProfit);
    }

    public static void printData(Data data) {
        System.out.println("Task\tProfit\tDeadline");
        int n = data.profit.length;
        for (int i = 0; i < n; i++) {
            System.out.println(i+"\t\t"+data.profit[i]+"\t"+data.deadline[i]);
        }
    }

    public static void main(String[] args) {
        Random rnd = new Random(0);
        int n = 10;
        Data data = new Data();
        data.profit = new double[n];
        data.deadline = new int[n];

        for (int i = 0; i < n; i++) {
            data.profit[i] = Math.round(rnd.nextDouble() * 96 + 44);
            data.deadline[i] = (int) Math.round(rnd.nextDouble() * 0.7f * n);

        }
        System.out.println("DATA:");
        printData(data);
        int[] sol = greedyAlgorithmSC(data);
        System.out.println("SOLUTION:");
        printSol(data, sol);
    }
}
