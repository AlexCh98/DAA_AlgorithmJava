package Alex.AlgoritmosDyV;

import java.util.ArrayList;
import java.util.List;

public class Quickselect {
    public static void main( String[] args){
        List<Integer> l1 = new ArrayList<>(10);
        l1.add(0);
        l1.add(1);
        l1.add(2);
        l1.add(3);
        l1.add(4);
        l1.add(5);
        l1.add(6);
        l1.add(7);
        l1.add(8);
        l1.add(9);
        List<Integer> l2 = new ArrayList<>(10);
        l2.add(1);
        l2.add(0);
        l2.add(4);
        l2.add(3);
        l2.add(2);
        l2.add(9);
        l2.add(7);
        l2.add(5);
        l2.add(6);
        l2.add(8);
        System.out.println(median(l1));
        System.out.println(median(l2));
    }

    private static int median(List<Integer> array) {
        int mitad = (array.size() -1)/2;
        return quickselect(array,mitad);
    }

    private static int quickselect(List<Integer> array, int mitad) {
        int resultado = 0;
        int pivote = 0;
        List<Integer> porDebajoPivote = new ArrayList<>();
        List<Integer> porEncimaPivote = new ArrayList<>();
        List<Integer> igualPivote = new ArrayList<>();
        pivote = array.get(array.size()/2);
        //Separamos en 3 listas en base al pivote
        for(Integer num: array){
            if(num < pivote){
                porDebajoPivote.add(num);
            }else if(num > pivote){
                porEncimaPivote.add(num);
            }else{
                igualPivote.add(num);
            }
        }
        if(mitad < porDebajoPivote.size()){
            resultado = quickselect(porDebajoPivote,mitad);
        }else if(mitad < porDebajoPivote.size() + igualPivote.size()){
            resultado = pivote;
        }else{
            resultado = quickselect(porEncimaPivote,mitad - porDebajoPivote.size() - igualPivote.size());
        }
        return resultado;
    }
}
