package Dani.RamaYPoda;

import java.util.*;

public class MochilaRP {

    static class Item {
        private int value;
        private int weight;
        private float ratio;

        Item(int v, int w) {
            this.value = v;
            this.weight = w;
            this.ratio = v / (float) w;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "value=" + value +
                    ", weight=" + weight +
                    ", ratio=" + ratio +
                    '}';
        }
    }

    static class Solucion implements Comparable<Solucion>{
        private double valor;
        private double pesoLibre;
        private boolean[] cogidos;
        private double limSuperior;
        private int sigObjeto;
        private Item[] objetos;

        public Solucion(Item[] objetos, int maxPeso){
            this.objetos = objetos;
            valor = 0;
            pesoLibre = maxPeso;
            cogidos = new boolean[objetos.length];

            limSuperior = objetos[0].ratio * this.pesoLibre;
        }


        public Solucion(Solucion other) {
            this.objetos = other.objetos;
            this.valor = other.getValor();
            this.cogidos = other.cogidos.clone();
            this.pesoLibre = other.pesoLibre;
            this.sigObjeto = other.sigObjeto;
            this.limSuperior = other.limSuperior;
        }

        public boolean isSolution() {
            return sigObjeto == objetos.length && pesoLibre >= 0.0D;
        }

        public boolean esCandidato(int indice){return pesoLibre-objetos[indice].weight >= 0;}

        public void saltarSiguienteCandidato(){
            cogidos[sigObjeto] = false;
            limSuperior = valor;
            sigObjeto++;
            //Actualizamos el limite superior
            actualizarLimite();
        }

        public void cogerSiguienteCandidato(){
            cogidos[sigObjeto] = true;
            valor+=objetos[sigObjeto].value;
            pesoLibre-=objetos[sigObjeto].weight;

            sigObjeto++;

            //Ahora actualizamos el limite del
            limSuperior = valor;
            actualizarLimite();
        }

        public void actualizarLimite(){
            if(sigObjeto < objetos.length){
                limSuperior += objetos[sigObjeto].ratio * pesoLibre;
            }
        }

        public List<Solucion> getHijos(){

            List<Solucion> hijos = new ArrayList<>();

            //Árbol binario
            Solucion hijo0 = new Solucion(this);


            hijo0.saltarSiguienteCandidato();
            hijos.add(hijo0);

            if(esCandidato(sigObjeto)){
                Solucion hijo1 = new Solucion(this);
                hijo1.cogerSiguienteCandidato();
                hijos.add(hijo1);
            }
            return hijos;
        }

        public double getValor() {
            return valor;
        }

        public double getPesoLibre() {
            return pesoLibre;
        }

        public double getLimSuperior() {
            return limSuperior;
        }

        @Override
        public int compareTo(Solucion o) {

            /* Dar prioridad a las soluciones mas completas */
            int nivel = Integer.compare(this.sigObjeto, o.sigObjeto);
            if (nivel != 0)
                return -nivel; //Signo negativo para ordenar de mayor a menor
            /* Si estan al mismo nivel la mas prometedora*/
            return -Double.compare(this.limSuperior, o.limSuperior);
        }
    }
    public static void main(String[] args) {

        int[] values = new int[]{10, 10, 12, 18};
        int[] weights = new int[]{2, 4, 6, 9};
        int max_weight = 15;
        Solucion solution = mochila(values, weights, max_weight);
        for(Item it: solution.objetos){
            System.out.println(it.toString());
        }
    }

    public static Solucion inicializar(int[]valores, int[]pesos, int maxPeso){
        Item[] objetos = new Item[valores.length];
        for(int i=0; i<valores.length; i++){
            Item ob = new Item(valores[i], pesos[i]);
            objetos[i] = ob;
        }
        Arrays.sort(objetos, Comparator.comparing(item -> -(item.ratio)));
        Solucion s = new Solucion(objetos, maxPeso);
        return s;
    }


    public static Solucion mochila(int[] valores, int[] pesos, int maxPeso){
        Solucion bestSol = inicializar(valores, pesos, maxPeso);

        PriorityQueue<Solucion> posibilidades = new PriorityQueue<>();
        Solucion primera = inicializar(valores, pesos, maxPeso);

        posibilidades.add(primera);

        while(!posibilidades.isEmpty()){
            Solucion sol = posibilidades.poll();

            if(sol.isSolution() && sol.valor > bestSol.valor){
                bestSol = new Solucion(sol);

            }else if(sol.limSuperior > bestSol.valor){

                for(Solucion s: sol.getHijos()){
                    if(s.limSuperior > bestSol.valor){
                        posibilidades.add(s);
                    }
                }
            }
        }
        return bestSol;
    }

}
