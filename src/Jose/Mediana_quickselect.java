package Jose;

import java.util.ArrayList;
import java.util.List;

public class Mediana_quickselect {
    public static void main( String [] args){
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
    private static int median(List<Integer> v){
        int relative_order = (v.size()-1)/2;
        return nthSmallestElement(v, relative_order);
    }
    private static int nthSmallestElement(List<Integer> list, int n){
        int pivot = list.get(0);
        int result;
        List<Integer> underPivot = new ArrayList<>();
        List<Integer> equalPivot = new ArrayList<>();
        List<Integer> overPivot = new ArrayList<>();
        for(int e: list){
            if(e < pivot){
                underPivot.add(e);
            }else if(e > pivot){
                overPivot.add(e);
            }else{
                equalPivot.add(e);
            }
        }
        if(n < underPivot.size()){
            result = nthSmallestElement(underPivot, n);
        }else if(n < underPivot.size()+equalPivot.size()){
            result = pivot;
        }else{
            result = nthSmallestElement(overPivot, n-underPivot.size()-equalPivot.size());
        }
        return result;
    }
}
