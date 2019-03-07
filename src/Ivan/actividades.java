package Ivan;

import java.io.IOException;
import java.util.*;

public class actividades {

    public static class actividad implements Comparable{
        int inicio;
        int fin;
        int duracion;

        public actividad(int inicio, int fin) {
            this.inicio = inicio;
            this.fin = fin;
            this.duracion = this.fin-this.inicio;
        }

        @Override
        public String toString() {
            return "actividad{" +
                    "inicio=" + inicio +
                    ", fin=" + fin +
                    '}';
        }

        @Override
        public int compareTo(Object o) {
            actividad actividad = (actividad) o;
            if(this.fin == actividad.fin) {
                if(this.duracion == actividad.duracion){
                    return Integer.compare(this.inicio, actividad.inicio);
                }else{
                    return Integer.compare(this.duracion, actividad.duracion);
                }
            }else{
                return Integer.compare(this.fin, actividad.fin);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(System.in);
        int numero_casos = Integer.parseInt(reader.nextLine());
        for(int i = 0; i < numero_casos; i++){
            int numero_actividades = Integer.parseInt(reader.nextLine());
            TreeSet<actividad> actividades = new TreeSet<>();
            for(int j = 0; j < numero_actividades*2; j = j+2){
                actividades.add(new actividad(reader.nextInt(), reader.nextInt()));
            }
            if(i != numero_casos-1)reader.nextLine();
            System.out.println(Asistencia(actividades));
            //System.out.println(actividades);
        }

    }

    private static int Asistencia(TreeSet<actividad> actividades) {
        int solucion = 0;
        TreeSet<actividad> actividadesAsistencia = new TreeSet<>();
        while(!actividades.isEmpty()){
            actividad actividad = actividades.first();
            if(puedoActividad(actividad , actividadesAsistencia)){
                actividadesAsistencia.add(actividad);
                solucion++;
            }
            actividades.remove(actividad);
        }
        return solucion;
    }

    private static boolean puedoActividad(actividad actividad, TreeSet<actividad> actividades) {
        if(actividades.isEmpty()){return true;}
        boolean salida = true;
        Iterator<actividad> it = actividades.iterator();
        while(salida && it.hasNext()){
            actividad actividad1 = it.next();
            salida = !(actividad.inicio < actividad1.fin);
        }
        return salida;
    }

}
