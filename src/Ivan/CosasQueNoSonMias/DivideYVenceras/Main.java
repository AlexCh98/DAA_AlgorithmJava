package Ivan.CosasQueNoSonMias.DivideYVenceras;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int arr[] = {12, 11, 13, 5, 6, 7, 1, 8, 3, 20, 14};
        int arr2[] = {-3, -1, 0, 3, 7, 9, 12, 14, 16, 25};

        /////////////////////////////////////////////////////////////////////////////////////////////////

        /*MergeSort ms = new MergeSort(arr);

        System.out.println("Not-Sorted array: ");
        ms.printArray();

        ms.mergesort();

        System.out.println("Sorted array: ");
        ms.printArray();*/

        /////////////////////////////////////////////////////////////////////////////////////////////////

        /*QuickSort qs = new QuickSort(arr);

        System.out.println("Not-Sorted array: ");
        qs.printArray();

        qs.quicksort();

        System.out.println("Sorted array: ");
        qs.printArray();*/

        /////////////////////////////////////////////////////////////////////////////////////////////////

        /*MergeSortPractica ms = new MergeSortPractica(arr);

        System.out.println("Not-Sorted array: ");
        ms.printArray();

        ms.mergesort();

        System.out.println("Sorted array: ");
        ms.printArray();*/

        /////////////////////////////////////////////////////////////////////////////////////////////////

        /*QuickSortPractica qs = new QuickSortPractica(arr);

        System.out.println("Not-Sorted array: ");
        qs.printArray();

        qs.quicksort();

        System.out.println("Sorted array: ");
        qs.printArray();*/

        /////////////////////////////////////////////////////////////////////////////////////////////////

        /*Comparator<Integer> comp = Comparator.naturalOrder();
        MedianDaC<Integer> medDaC = new MedianDaC<>(comp);
        Random rnd = new Random();

        for (int size = 1; size <= 10; size++) {
            ArrayList<Integer> coll = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                coll.add(rnd.nextInt(100));
            }
            System.out.println("Median of " + coll.toString() + " is " + medDaC.median(coll));
        }*/

        /////////////////////////////////////////////////////////////////////////////////////////////////

        /*BinarySearchIndex bsi = new BinarySearchIndex();
        System.out.println(bsi.indexSearch(arr2, 3));*/

        /////////////////////////////////////////////////////////////////////////////////////////////////

        /*int[][] matrix = {{1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}};

        int[][] matrix2 = {{1, 2, 3},
                {4, 5, 6,},
                {7, 8, 9}};

        Traspuesta t = new Traspuesta();
        int[][] traspuesta = t.trasponer(matrix);

        for (int i = 0; i < traspuesta.length; i++) {
            for (int j = 0; j < traspuesta.length; j++) {
                System.out.print(traspuesta[i][j] + " ");
            }
            System.out.println();
        }*/


        /////////////////////////////////////////////////////////////////////////////////////////////////

        int[][] calendario = new int[6][6];

        for (int i = 0; i < calendario.length; i++) {
            for (int j = 0; j < calendario.length; j++) {
                calendario[i][j] = i+1;
            }
        }

       /* for (int i = 0; i < calendario.length; i++) {
            for (int j = 0; j < calendario.length; j++) {
                System.out.print(calendario[i][j] + " ");
            }
            System.out.println();
        }*/

        Calendario c = new Calendario();
        calendario = c.calendario(calendario);

        for (int i = 0; i < calendario.length; i++) {
            for (int j = 0; j < calendario.length; j++) {
                System.out.print(calendario[i][j] + " ");
            }
            System.out.println();
        }
    }
}
