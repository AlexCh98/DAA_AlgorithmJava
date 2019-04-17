package Dani.Backtracking;


import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class NReinas {

    private static final int NREINAS = 20;
    private static int contador = 0;

    public static void main(String[] args){
        int[] sol = new int[NREINAS];
        Set<Integer> diagP = new HashSet<>();
        Set<Integer> diagN = new HashSet<>();
        Set<Integer> filas = new HashSet<>();
        calcularNReinas(0,0, diagP, diagN, filas, sol);
        System.out.println(contador);
    }

    public static void calcularNReinas(int k, int col, Set<Integer> diagP, Set<Integer> diagN, Set<Integer> filas, int[] sol){
        /*
        System.out.print("\nNegativas: ");
        for(int i: diagN){
            System.out.print(i+", ");
        }
        System.out.println();

        System.out.print("Positivas: ");
        for(int i: diagN){
            System.out.print(i+", ");
        }
        System.out.println();

        System.out.print("Filas: ");
        for(int i: diagN){
            System.out.print(i+", ");
        }
        System.out.println();
        */

        //Si es solución la imprimo
        if(k==NREINAS){
            contador++;
            //mostrarTablero(sol);
            /*Scanner scan = new Scanner(System.in);
            scan.nextInt();*/
        }else{
            //Miramos la fila en la que podemos meter una reina en la columna actual
            for(int row=0; row<NREINAS; row++){

                if(esFactible(row, col, diagP, diagN, filas)){
                    //System.out.println("Pongo reina en: [" + row + "]["+ col + "]");
                    //Añado a la solución
                    sol[col]=row;

                    //Añado más diagonales ocupadas y la fila también
                    diagP.add(col-row);
                    diagN.add(col+row);
                    filas.add(row);

                    //Siguiente iteración
                    calcularNReinas(k+1,col+1, diagP, diagN, filas, sol);

                    //Quito las diagonales que se desocupan y la fila también
                    diagP.remove(col-row);
                    diagN.remove(col+row);
                    filas.remove(row);

                    //Siguiente fila
                }
            }
        }
    }

    public static boolean esFactible(int row, int col, Set<Integer> diagP, Set<Integer> diagN, Set<Integer> filas){
        int pos = col-row;
        int neg = col+row;

        return (!diagP.contains(pos) && !diagN.contains(neg) && !filas.contains(row));
    }

    public static void mostrarTablero(int[] sol){
        for(int row=0; row < NREINAS; row++){
            for (int col = 0; col < NREINAS ; col++) {
                if(sol[col] == row){
                    System.out.print("R ");
                }
                else{
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
