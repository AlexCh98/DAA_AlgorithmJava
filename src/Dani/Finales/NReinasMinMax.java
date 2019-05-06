package Dani.Finales;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class NReinasMinMax {

    public static class Solucion{
        public int[] pos; //Posicion i es la columna i, valor i es la fila
        public int mejor; //Max, min, promedio
        public int[] mejorPosiciones;

        public int mejorMin;
        public int[] mejorPosicionesMin;

        public double mejorPromedio;
        public int[] mejorPosicionesPromedio;

        public Solucion(){
            pos = new int[4];
            mejorPosiciones = new int[4];
            mejorPosicionesMin = new int[4];
            mejorPosicionesPromedio = new int[4];

            mejor = Integer.MAX_VALUE; //Esto es para minimizar el máximo
            mejorMin = 0;
            mejorPromedio = 0;
        }

        public void guardarMejor(){
            for(int i=0; i<mejorPosiciones.length; i++){
                mejorPosiciones[i] = pos[i];
            }
        }

        public void guardarMejorMinimo(){
            for(int i=0; i<mejorPosiciones.length; i++){
                mejorPosicionesMin[i] = pos[i];
            }
        }

        public void guardarMejorPromedio(){
            for(int i=0; i<mejorPosiciones.length; i++){
                mejorPosicionesPromedio[i] = pos[i];
            }
        }
    }
    public static void main(String[] args){

        int[][] tablero = new int[4][4];
        tablero[0][0] = 1;
        tablero[0][1] = 5;
        tablero[0][2] = 9;
        tablero[0][3] = 13;

        tablero[1][0] = 2;
        tablero[1][1] = 6;
        tablero[1][2] = 10;
        tablero[1][3] = 14;

        tablero[2][0] = 3;
        tablero[2][1] = 7;
        tablero[2][2] = 11;
        tablero[2][3] = 15;

        tablero[3][0] = 4;
        tablero[3][1] = 8;
        tablero[3][2] = 12;
        tablero[3][3] = 16;

        Set<Integer> diagPos = new HashSet<>(); //fila-columna
        Set<Integer> diagNeg = new HashSet<>(); //fila+columna
        Set<Integer> filas = new HashSet<>(); //fila
        Solucion sol = new Solucion();

        hacerProblema(tablero,0, diagPos, diagNeg, filas, sol);

        System.out.println("Minimizar maximo: ");
        System.out.println(sol.mejor);
        for(int i=0; i<sol.pos.length; i++){
            System.out.print(sol.mejorPosiciones[i]+ " ");
        }
        System.out.println();

        System.out.println("Maximinar minimo: ");
        System.out.println(sol.mejorMin);
        for(int i=0; i<sol.pos.length; i++){
            System.out.print(sol.mejorPosicionesMin[i]+ " ");
        }
        System.out.println();

        System.out.println("Maximizar promedio: ");
        System.out.println(sol.mejorPromedio);
        for(int i=0; i<sol.pos.length; i++){
            System.out.print(sol.mejorPosicionesPromedio[i]+ " ");
        }

        System.out.println("En este caso una solución da el óptimo para los tres apartados");
    }

    public static void hacerProblema(int[][] tablero, int columna, Set<Integer> diagPos, Set<Integer> diagNeg, Set<Integer> filas, Solucion sol){
        if(columna == tablero.length){
            int max = calcularMaximo(sol.pos, tablero);
            int min = calcularMinimo(sol.pos, tablero);
            double promedio = calcularPromedio(sol.pos, tablero);

            if(max < sol.mejor){
                sol.mejor = max;
                sol.guardarMejor();
            }

            if(min > sol.mejorMin){
                sol.mejorMin = min;
                sol.guardarMejorMinimo();
            }

            if(promedio > sol.mejorPromedio){
                sol.mejorPromedio = promedio;
                sol.guardarMejorPromedio();
            }


        }else{
            for(int i=0; i<tablero.length; i++){
                if(esFactible(columna, i, diagPos, diagNeg, filas)){

                    sol.pos[columna] = i;

                    diagPos.add(i-columna);
                    diagNeg.add(i+columna);
                    filas.add(i);

                    hacerProblema(tablero, columna+1, diagPos, diagNeg, filas, sol);

                    diagPos.remove(i-columna);
                    diagNeg.remove(i+columna);
                    filas.remove(i);
                }
            }
        }
    }

    public static boolean esFactible(int col, int row, Set<Integer> diagP, Set<Integer> diagN, Set<Integer> filas){
        int pos = row-col;
        int neg = row+col;

        return (!diagP.contains(pos) && !diagN.contains(neg) && !filas.contains(row));
    }

    public static int calcularMaximo(int[] posiciones, int tablero[][]){
        int max = 0;
        for(int i=0; i<posiciones.length; i++){
            if(tablero[i][posiciones[i]] > max){
                max = tablero[i][posiciones[i]];
            }
        }
        return max;
    }

    public static int calcularMinimo(int[] posiciones, int tablero[][]){
        int min = Integer.MAX_VALUE;
        for(int i=0; i<posiciones.length; i++){
            if(tablero[i][posiciones[i]] < min){
                min = tablero[i][posiciones[i]];
            }
        }
        return min;
    }

    public static double calcularPromedio(int[] posiciones, int tablero[][]){
        int promedio = 0;
        for(int i=0; i<posiciones.length; i++){
            promedio += tablero[i][posiciones[i]];
        }
        return promedio/posiciones.length;
    }
}
