package Alex.EjerciciosProblemasClase.ClasePracticaVoracesGrafos_BackTracking;



import java.util.*;

public class Carreteras {
    private static int islas;
    private static long carreteras;
    private static long coste;
    public static class Carretera implements Comparable{
        int origen;
        int destino;
        int coste;

        public Carretera(int origen, int destino, int coste) {
            this.origen = origen;
            this.destino = destino;
            this.coste = coste;
        }


        @Override
        public int compareTo(Object o) {
            Carretera otra = (Carretera) o;
            if (this.coste == otra.coste) {
                if (this.origen == otra.origen && this.destino == otra.destino || this.destino == otra.origen && this.origen == otra.destino) {
                    return 0;
                } else {
                    return -1;
                }
            } else {
                return Integer.compare(otra.coste, this.coste); //Maximizar
                //return Integer.compare(this.coste, otra.coste); //Minimizar
            }
        }

    }
    public static void main(String [] args){
        Scanner lector = new Scanner(System.in);
        int numeroCiudades = Integer.parseInt(lector.nextLine());
        while(numeroCiudades != 0){
            int numeroCarreteras = Integer.parseInt(lector.nextLine());
            List[] listadoCarreteras = new List[numeroCiudades];
            for(int i = 0; i < numeroCiudades;i++){
                listadoCarreteras[i] = new ArrayList();
            }
            for (int j = 0; j < numeroCarreteras;j++){
                String[] linea = lector.nextLine().split(" ");
                int origen = Integer.parseInt(linea[0]);
                int destino = Integer.parseInt(linea[1]);
                int peso = Integer.parseInt(linea[2]);
                listadoCarreteras[origen].add(new Carretera(origen,destino,peso));
                listadoCarreteras[destino].add(new Carretera(destino,origen,peso));
            }
            Prim(listadoCarreteras);
            System.out.println(islas+" "+carreteras+" "+coste);
            System.out.println("---");
            coste = 0;
            islas = 0;
            carreteras = 0;
            numeroCiudades = Integer.parseInt(lector.nextLine());

        }
    }

    private static List<Carretera> Prim(List[] listadoCarreteras) {
        boolean[] visitado = new boolean[listadoCarreteras.length];
        List<Carretera> isla = new ArrayList<>();
        for(int i = 0; i < listadoCarreteras.length;i++){
            if(!visitado[i]){
                islas++;
                PriorityQueue<Carretera> cola = new PriorityQueue<>();
                cola.addAll(listadoCarreteras[i]);
                visitado[i] = true;
                while (!cola.isEmpty()){
                    Carretera carret = cola.poll(); //Cogemos el mejor elemento y lo borramos
                    if(!visitado[carret.destino]){
                        coste += carret.coste;
                        carreteras += 1;
                        isla.add(carret);
                        cola.addAll(listadoCarreteras[carret.destino]);
                        visitado[carret.destino] = true;
                    }
                }
            }
        }
        return isla;
    }
}
