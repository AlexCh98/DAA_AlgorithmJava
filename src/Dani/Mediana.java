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

        Set<Integer> menoresGeneral = new TreeSet<>(Comparator.comparing(integer -> -integer));
        Set<Integer> mayoresGeneral = new TreeSet<>(Comparator.comparing(integer -> -integer));
        Set<Integer> igualesGeneral = new TreeSet<>(Comparator.comparing(integer -> -integer));

        int mediana = calcularMediana(list,(list.size()-1)/2, menoresGeneral, mayoresGeneral, igualesGeneral);
        System.out.println("Mediana: "+mediana);
        System.out.print("Menores: ");
        imprimirLista(menoresGeneral);
        System.out.print("Mayores: ");
        imprimirLista(mayoresGeneral);
        System.out.print("Iguales: ");
        imprimirLista(igualesGeneral);
    }

    public static void imprimirLista(Set<Integer> q){
        for (int i: q) {
            System.out.print(i +", ");
        }
        System.out.println();
    }

    public static int calcularMediana(List<Integer>list, int k, Set<Integer> menoresGeneral, Set<Integer> mayoresGeneral, Set<Integer> igualesGeneral){
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
                menoresGeneral.add(num);
                mayoresGeneral.remove(num);

            }else if(num > pivot){
                mayores.add(num);
                mayoresGeneral.add(num);
                menoresGeneral.remove(num);
            }else{
                iguales.add(num);
                igualesGeneral.add(num);
            }
        }
        System.out.println();
        imprimirLista(menoresGeneral);
        imprimirLista(mayoresGeneral);
        imprimirLista(igualesGeneral);
        System.out.println("------------------");

        if(menores.size() > k){
            mayoresGeneral.add(pivot);
            igualesGeneral.remove(pivot);
            return calcularMediana(menores, k,menoresGeneral,mayoresGeneral,igualesGeneral);
        }else if(menores.size() + iguales.size() > k){
            mayoresGeneral.remove(pivot);
            menoresGeneral.remove(pivot);
            return pivot;
        }else{
            igualesGeneral.remove(pivot);
            menoresGeneral.add(pivot);
            return calcularMediana(mayores, k- menores.size() - iguales.size(), menoresGeneral, mayoresGeneral, igualesGeneral);
        }
    }
}
