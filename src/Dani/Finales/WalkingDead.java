package Dani.Finales;

import java.util.HashSet;
import java.util.Set;

public class WalkingDead {

    public static class Mochila{
        public Set<Integer> objetos; //Para guardar los índices de los objetos que tenemos en la mochila
        public Set<Integer> mejoresObjetos;
        public int maxPeso;
        public int pesoLibre;
        public int valor;
        public int mejorValor;

        public Mochila(int maxPeso){
            this.maxPeso = maxPeso;
            pesoLibre = maxPeso;
            objetos = new HashSet<>();
            mejoresObjetos = new HashSet<>();
            valor = 0;
        }

        public void actualizarPeso(int peso){
            pesoLibre -= peso;
        }

        //Cuando se quita un objeto el peso libre aumenta
        public void annadirPesoLibre(int peso){
            pesoLibre+=peso;
        }

        //Añade el objeto y actualiza atributos
        public void annadirObjeto(int indice, int peso, int valor){
            objetos.add(indice);
            actualizarPeso(peso);
            annadirValor(valor);
        }

        //Quita el objeto y actualiza atributos
        public void eliminarObjeto(int indice, int peso, int valor){
            objetos.remove(indice);
            annadirPesoLibre(peso);
            restarValor(valor);
        }

        public void annadirValor(int valor){
            this.valor+=valor;
        }

        public void restarValor(int valor){
            this.valor-=valor;
        }

        public void actualizarMejorSolucion(){
            mejorValor = valor;
            mejoresObjetos.clear();
            mejoresObjetos.addAll(objetos);
        }

        public boolean esFactible(int peso){
            return (pesoLibre-peso >= 0);
        }

    }

    public static void main(String[] args){
        //Datos cogidos de DS-TEAM y añado yo algunos más
        int[] pesos = {46,38,15,37,17,9,20,50};
        int[] valores = {77,19,78,86,84,17,29,70};

        Mochila mochilaRick = new Mochila(60);
        Mochila mochilaCarl = new Mochila(20);

        //No hace falta algoritmo voraz al ser bt y recorrer todas las ramas
        int indiceObj = 0;

        calcularMejorReparto(mochilaRick, mochilaCarl, indiceObj, pesos, valores);

        System.out.println("--Mochila Rick--");
        System.out.print("Objetos: ");
        for(int i: mochilaRick.mejoresObjetos){
            System.out.print(i + ":["+ pesos[i] + "][" +valores[i]+"], ");
        }
        System.out.println("\nMejor Valor: "+ mochilaRick.mejorValor);

        System.out.println("--Mochila Carl--");
        System.out.print("Objetos: ");
        for(int i: mochilaCarl.mejoresObjetos){
            System.out.print(i + ":["+ pesos[i] + "][" +valores[i]+"], ");
        }
        System.out.println("\nMejor Valor: "+ mochilaCarl.mejorValor);

        System.out.println("VALOR TOTAL = "+ (mochilaCarl.mejorValor+mochilaRick.mejorValor));
    }

    public static void calcularMejorReparto(Mochila mochilaRick, Mochila mochilaCarl, int indiceObj, int[] pesos, int[] valores){
        if(indiceObj == pesos.length){
            int valorActualTotal = mochilaCarl.valor+ mochilaRick.valor;
            int mejorValor = mochilaCarl.mejorValor+mochilaRick.mejorValor;

            if(valorActualTotal > mejorValor){
                mochilaCarl.actualizarMejorSolucion();
                mochilaRick.actualizarMejorSolucion();
            }
        }else{
            //Árbol ternario

            //Coge el objeto Rick
            if(mochilaRick.esFactible(pesos[indiceObj])) {

                mochilaRick.annadirObjeto(indiceObj, pesos[indiceObj], valores[indiceObj]);
                calcularMejorReparto(mochilaRick, mochilaCarl, indiceObj + 1, pesos, valores);
                mochilaRick.eliminarObjeto(indiceObj, pesos[indiceObj], valores[indiceObj]);
            }

            //Coge el objeto Carl
            if(mochilaCarl.esFactible(pesos[indiceObj])) {
                mochilaCarl.annadirObjeto(indiceObj, pesos[indiceObj], valores[indiceObj]);
                calcularMejorReparto(mochilaRick, mochilaCarl, indiceObj + 1, pesos, valores);
                mochilaCarl.eliminarObjeto(indiceObj, pesos[indiceObj], valores[indiceObj]);
            }

            //No lo coge ninguno
            calcularMejorReparto(mochilaRick, mochilaCarl, indiceObj+1, pesos, valores);
        }
    }

}
