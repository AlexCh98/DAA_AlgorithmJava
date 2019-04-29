package Dani.RamaYPoda;

import java.util.*;

public class AsignarActividades {

    private static class Solution {
        private int[] sol;
        private int[][] costes;
        private int[] minCostVector;
        private int cost;
        private boolean[] disponibles;
        private int limiteInf;
        private int sigAgente;

        public Solution(int[][] costes) {
            sigAgente = 0;
            sol = new int[costes.length];
            this.minCostVector = new int[costes.length];
            this.costes = costes;
            cost = 0;

            disponibles = new boolean[costes.length];
            Arrays.fill(disponibles, true);

            this.minCostVector = calcularMinCostes(costes);
            this.limiteInf = calcularLimiteInferior();
        }

        public Solution(Solution sol) {
            this.sol = sol.sol.clone();
            this.costes = sol.costes;
            this.minCostVector = sol.minCostVector.clone();
            this.cost = sol.cost;
            this.disponibles = sol.disponibles.clone();
            this.limiteInf = sol.limiteInf;
            this.sigAgente = sol.sigAgente;
        }

        public StringBuilder aString(){
            StringBuilder str = new StringBuilder();
            str.append("La solución:\n");
            for(int i=0; i<costes.length; i++){
                    str.append("Trabajador: "+ i +" tiene la tarea "+sol[i] +"\n");
            }

            str.append("El coste es: " + cost + "\n\n");

            return str;
        }
        //Este solo para inicializar
        public int[] calcularMinCostes(int[][] costes){
            int[] sol = new int[costes.length];
            for(int i=0; i<costes.length; i++){
                int min = Integer.MAX_VALUE;
                for(int j=0; j<costes[0].length; j++){
                    if(costes[i][j] < min){
                        min = costes[i][j];
                    }
                }
                sol[i] = min;
            }
            return sol;
        }

        //Este solo para inicializar
        public int calcularLimiteInferior(){
            int sol =0;

            for(int i=0; i<minCostVector.length; i++){
                sol+=minCostVector[i];
            }

            return sol;
        }

        public void annadirActividad(int sigActividad){
            sol[sigAgente] = sigActividad;
            disponibles[sigActividad] = false;
            cost+=costes[sigAgente][sigActividad];

            actualizarLimiteInferior();

            sigAgente++;
        }

        public void actualizarLimiteInferior(){
            limiteInf = 0;
            calcularMinimosCostes(sigAgente+1);
            limiteInf += cost;
        }

        //Calcula los minimos costes a partir de un trabajador y eligiendo actividades disponibles
        public void calcularMinimosCostes(int inicio){
            for (int a = inicio; a < sol.length; a++) {
                int minCost = Integer.MAX_VALUE;
                for (int t = 0; t < costes.length; t++) {
                    if (costes[a][t] < minCost && disponibles[t]) {
                        minCost = costes[a][t];
                    }
                }
                this.minCostVector[a] = minCost;
                limiteInf+=minCost;
            }
        }

        public boolean esSolucion(){return sigAgente==costes.length; }


        public int getCost() {
            return cost;
        }

        public int getLimiteInf() {
            return limiteInf;
        }

        public List<Solution> getChildren(){
            List<Solution> children = new ArrayList<Solution>();

            //Probamos qué actividades podemos meter
            for(int i=0; i< costes.length; i++) {
                if(disponibles[i]) {
                    //Creamos el siguiente nodo
                    Solution child = new Solution(this);
                    child.annadirActividad(i);
                    children.add(child);
                }
            }
            return children;
        }
    }

    public static Solution initSol(int[][] costs) {
        Solution sol = new Solution(costs);
        for (int i = 0; i < costs.length; i++) {
            sol.annadirActividad(i);
        }
        return sol;
    }

    public static void main(String[] args){
        int[][] matrizCostes = new int[4][4];

        matrizCostes[0][0] = 11;
        matrizCostes[0][1] = 12;
        matrizCostes[0][2] = 18;
        matrizCostes[0][3] = 40;

        matrizCostes[1][0] = 14;
        matrizCostes[1][1] = 15;
        matrizCostes[1][2] = 13;
        matrizCostes[1][3] = 22;

        matrizCostes[2][0] = 11;
        matrizCostes[2][1] = 17;
        matrizCostes[2][2] = 19;
        matrizCostes[2][3] = 23;

        matrizCostes[3][0] = 17;
        matrizCostes[3][1] = 14;
        matrizCostes[3][2] = 20;
        matrizCostes[3][3] = 28;

        Solution sol = asignarTareas(matrizCostes);
        System.out.println(sol.aString());
    }

    public static Solution asignarTareas(int[][] costs){
        int nodosGenerados = 0;
        PriorityQueue<Solution> q = new PriorityQueue<>(costs.length, Comparator.comparingInt(s -> s.cost));
        Solution bestSol = initSol(costs);

        q.add(new Solution(costs));
        nodosGenerados++;

        while(!q.isEmpty()){
            //Cogemos la siguiente solucion
            Solution actual = q.poll();
            //Si es una solución y es mejor que la anterior, entonces
            if(actual.esSolucion() && actual.getCost() < bestSol.getCost()){
                System.out.println(actual.aString());
                bestSol = actual;
            }else{
                if(actual.getLimiteInf() < bestSol.getCost()){
                    for(Solution hijo: actual.getChildren()){
                        if(hijo.getLimiteInf() < bestSol.getCost()){
                            q.add(hijo);
                            nodosGenerados++;
                        }
                    }
                }
            }
        }
        return bestSol;
    }

}
