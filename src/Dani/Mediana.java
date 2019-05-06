package Dani;

import java.util.*;

public class Mediana {

    public static void main(String[] args){
        Random r = new Random();

        int n = 100;
        ArrayList<Integer> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(r.nextInt(n * 2));
        }

        ArrayList<Integer> listExamen = new ArrayList<>(7);
        listExamen.add(2);
        listExamen.add(3);
        listExamen.add(4);
        listExamen.add(1);
        listExamen.add(9);
        listExamen.add(8);
        listExamen.add(7);

        int mediana = calcularMediana(list,(list.size()-1)/2);
        System.out.println(mediana);
    }

    public static void imprimirLista(Set<Integer> q){
        for (int i: q) {
            System.out.print(i +", ");
        }
        System.out.println();
    }

    public static int calcularMediana(List<Integer>list, int k){
        List<Integer> menores = new ArrayList<>();
        List<Integer> mayores = new ArrayList<>();
        List<Integer> iguales = new ArrayList<>();

        int pivot = list.get(list.size()/2);

        System.out.println("Pivote: "+pivot);
        System.out.println("K: "+k);

        for(Integer num: list){
            System.out.print(num+", ");
            if(num < pivot){
                menores.add(num);

            }else if(num > pivot){
                mayores.add(num);
            }else{
                iguales.add(num);
            }
        }
        if(menores.size() > k){
            return calcularMediana(menores, k);
        }else if(menores.size() + iguales.size() > k){
            return pivot;
        }else{
            return calcularMediana(mayores, k- menores.size() - iguales.size());
        }
    }
}
