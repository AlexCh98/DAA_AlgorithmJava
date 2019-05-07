package Ivan.HechasPorMi.Voraces.EjerciciosResumenes;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MochilaDiscreta {
    public static class Objeto implements Comparable<Objeto>{
        String nombre;
        int peso;

        public Objeto(String nombre,int peso) {
            this.nombre = nombre;
            this.peso =peso;
        }

        @Override
        public int compareTo(Objeto o) {
            return Integer.compare(this.peso,o.peso);
        }
    }


    public static void main(String[] args) {
        Objeto o1 = new Objeto("raton",1);
        Objeto o2 = new Objeto("teclado",2);
        Objeto o3 = new Objeto("ipad",2);
        Objeto o4 = new Objeto("portatil",3);
        Objeto o5 = new Objeto("mesa",15);
        Objeto o6 = new Objeto("planta",4);
        Objeto o7 = new Objeto("cargador",1);
        Objeto o8 = new Objeto("papeles",5);
        Objeto o9 = new Objeto("altavoz",8);
        List<Objeto> objetos = new ArrayList<>();
        objetos.add(o1);objetos.add(o2);objetos.add(o3);objetos.add(o4);
        objetos.add(o5);objetos.add(o6);objetos.add(o7);objetos.add(o8);
        objetos.add(o9);
        int pesoMax = 22;
        //Comprobamos que los datos estan bien introducidos
        for (int i = 0; i < objetos.size(); i++) {
            System.out.println(objetos.get(i).nombre+" "+objetos.get(i).peso);
        }
        //Ordenamos los objetos
        Collections.sort(objetos);
        ArrayList<Objeto>mochila=new ArrayList<>();
        greedyMochilaDiscreta(objetos,pesoMax,mochila);
        //Imprimimos el contenido de la mochila
        System.out.println("Estos son los objetos cogidos");
        for (int i = 0; i < mochila.size(); i++) {
            System.out.println(mochila.get(i).nombre+" "+mochila.get(i).peso);
        }
    }
//Vamos a maximizar el numero de objetos, entonces nuestro criterio es meter el que menos pese
    private static void greedyMochilaDiscreta(List<Objeto> objetos, int pesoMax, ArrayList<Objeto> mochila) {
        List<Objeto> copiaObjs = objetos;
        int auxPeso=0;
        while ((auxPeso<pesoMax)&&(!copiaObjs.isEmpty())){
            for (int i = 0; i <objetos.size() ; i++) {
                if(auxPeso+copiaObjs.get(i).peso<=pesoMax){
                    System.out.println(" Añado Obj = " + copiaObjs.get(i).nombre);
                    mochila.add(copiaObjs.get(i));
                    auxPeso=auxPeso+copiaObjs.get(i).peso;
                    copiaObjs.remove(i);
                }
                System.out.println(" Elimino Obj = " + copiaObjs.get(i).nombre);
                copiaObjs.remove(i);
            }



        }
    }


}
