package Ivan.CódigosClase.Complejidad;


import java.util.Random;

public class SearchVector {
	//La semilla fija la serie aleatoria que genera (seed=1234)
	private static Random rng = new Random(1234);


    @SuppressWarnings("unused")
	public static void main(String[] args){
	        int min=100000, max=1000000, step=100000;
	        int queries = 10000;
	
	        int index=-1;
            System.out.println("n\tms");
	        for(int i=min; i<=max; i+=step){
	            int v[] = generateVector(i);
	            int[] elements = generateQueries(queries, i);
	            long start = System.currentTimeMillis();
	            for (int j = 0; j < elements.length; j++) {
	            	//index = linearSearch(v, elements[j]);
	            	//index = binarySearch(v, elements[j]);
	            	index = randomSearch(v, elements[j]);
	            }
	            long time = System.currentTimeMillis() - start;
	            System.out.printf("%d\t%d\n", i, time);
	        }
	        
	    }

	private static int[] generateQueries(int queries, int size) {
		int elements[] = new int[queries];
		for(int j=0; j<queries; j++){
		    elements[j] = rng.nextInt(size);
		}
		return elements;
	}

	private static int[] generateVector(int size){
        int v[] = new int[size];
        for(int i=0; i<size; i++){
            v[i]=i;
        }
        return v;

    }

    //Precondicion: el elemento existe o tendriamos un bucle infinito
    public static int randomSearch(int v[], int element){
        Random rng = new Random(0);
        while(true){
            int index = rng.nextInt(v.length);
            if(v[index]==element)
                return index;
        }
    }

    //Precondicion: ninguna (ordenado o desordenado)
    public static int linearSearch(int v[], int element){
        for(int i=0; i<v.length; i++){
            if(v[i]==element)
                return i;
        }
        return -1;
    }

    //Precondicion: ordenado de menor a mayor O(logn)
    public static int binarySearch(int v[], int element){
        int low=0, high=v.length;
        while(low<high){
            int mid = (low+high)/2;
            if(v[mid]==element)
                return mid;
            else if(v[mid]<element){
                low = mid;
            } else{
                high = mid;
            }
        }
        return -1;
    }


}

