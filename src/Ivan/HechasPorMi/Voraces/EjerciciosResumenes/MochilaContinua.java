package Ivan.HechasPorMi.Voraces.EjerciciosResumenes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MochilaContinua {
    public static class Objeto implements Comparable<Objeto>{
        String nombre;
        double peso;
        double utilidad;
        double ratio;

        public void setPeso(double peso) {
            this.peso = peso;
        }

        public Objeto(String nombre, double peso, double utilidad) {
            this.nombre = nombre;
            this.peso =peso;
            this.utilidad=utilidad;
            ratio = (double) Math.round(((this.utilidad/this.peso) * 100d));
        }

        @Override
        public int compareTo(Objeto o) {
            return Double.compare(this.ratio,o.ratio);
        }
    }

    public static void main(String[] args) {
        Objeto o1 = new Objeto("raton",1.42,1);
        Objeto o2 = new Objeto("teclado",2.25,1);
        Objeto o3 = new Objeto("ipad",2.9,3);
        Objeto o4 = new Objeto("portatil",3.45,5);
        Objeto o5 = new Objeto("mesa",15.23,6);
        Objeto o6 = new Objeto("planta",4.14,1);
        Objeto o7 = new Objeto("cargador",1.28,3);
        Objeto o8 = new Objeto("papeles",5.52,5);
        Objeto o9 = new Objeto("altavoz",8.34,9);
        List<Objeto> objetos = new ArrayList<>();
        objetos.add(o1);objetos.add(o2);objetos.add(o3);objetos.add(o4);
        objetos.add(o5);objetos.add(o6);objetos.add(o7);objetos.add(o8);
        objetos.add(o9);
        int pesoMax = 22;
        Collections.sort(objetos);
        for (int i = 0; i <objetos.size() ; i++) {
            System.out.println(objetos.get(i).ratio);
        }
        List<Objeto> solucion = new ArrayList<>();
        greedyMochilaContinua(objetos,pesoMax,solucion);
        Collections.sort(solucion);
        for (int i = 0; i < solucion.size(); i++) {
            System.out.println(solucion.get(i).nombre+" "+solucion.get(i).peso);
            System.out.println("Ratio del objeto:"+solucion.get(i).ratio);
        }
    }

    private static void greedyMochilaContinua(List<Objeto> objetos, int pesoMax, List<Objeto> solucion) {
        List<Objeto> copiaObjs = objetos;
        double pesoAux=0;
        while ((pesoAux<pesoMax)&&(!copiaObjs.isEmpty())){
            for (int i = 0; i <objetos.size() ; i++) {
                double falta =(pesoMax-pesoAux);
                if ((copiaObjs.get(i).peso+pesoAux)<pesoMax){
                    solucion.add(copiaObjs.get(i));
                    pesoAux=pesoAux+copiaObjs.get(i).peso;
                }
                copiaObjs.remove(i);
            }
        }
    }

}
