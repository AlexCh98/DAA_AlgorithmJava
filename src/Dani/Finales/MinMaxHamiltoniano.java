package Dani.Finales;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//TODO probar si funciona, creo que si
public class MinMaxHamiltoniano {

    public static int minCoste = Integer.MAX_VALUE;

    public static void main(String[] args){

        List<List<Integer[]>> grafoPonderado = new ArrayList<>();

        //Rellenar el grafo



        calcularMinHamiltoniano(grafoPonderado);
    }

    public static void calcularMinHamiltoniano(List<List<Integer[]>> grafoPonderado){
        boolean[] visitados = new boolean[grafoPonderado.size()];
        int minValor = Integer.MAX_VALUE;

        minHamiltoniano(grafoPonderado, visitados, 0, 0);
    }

    public static void minHamiltoniano(List<List<Integer[]>> grafoPonderado, boolean[] visitados, int nodoActual, int coste){

        visitados[nodoActual] = true;
        if(esSolucion(visitados) && cierraCiclo(grafoPonderado, nodoActual)){
                if(coste < minCoste){
                    minCoste = coste;
                }
        }else{

            List<Integer[]> ady = new ArrayList<>();
            ady.addAll(grafoPonderado.get(nodoActual));

            //Recorremos los adyacentes
            while(!ady.isEmpty()){
                Integer[] sigNodo = ady.remove(0);
                if(esFactible(sigNodo[0], visitados)){
                    minHamiltoniano(grafoPonderado,visitados,sigNodo[0],coste+sigNodo[1]);
                }
            }
        }
        visitados[nodoActual] = false;
    }

    public static boolean cierraCiclo(List<List<Integer[]>> grafoPonderado, int nodo){
        List<Integer[]> ady = grafoPonderado.get(nodo);

        Iterator it = ady.iterator();

        while(it.hasNext()){
            Integer[] sig = (Integer[]) it.next();
            //El ciclo empieza y termina en 0
            if(sig[0] == 0){
                return true;
            }
        }
        return false;
    }

    public static boolean esFactible(int nodo, boolean[] visitados){
        return !visitados[nodo];
    }

    public static boolean esSolucion(boolean[] visitados){
        int i=0;

        while(i<visitados.length){
            if(!visitados[i]){
                return false;
            }
            i++;
        }

        return true;
    }

}
