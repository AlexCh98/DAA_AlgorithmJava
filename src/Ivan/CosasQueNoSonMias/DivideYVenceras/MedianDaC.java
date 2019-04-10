package Ivan.CosasQueNoSonMias.DivideYVenceras;

import java.util.ArrayList;
import java.util.Comparator;

/*import utils.DefaultComparator;*/


public class MedianDaC<T extends Number>{
	Comparator<T> comp;
		
	/*public MedianDaC() {
		this.comp = new DefaultComparator<>();
	}*/

	public MedianDaC(Comparator<T> c) {
		this.comp = c;
	}	
	
	public double median(ArrayList<T> coll) {
		double result;
		int n = coll.size() / 2; //nth smallest element
		
		if (coll.size() % 2 == 0){ 
			result = (nthSmallestElement(coll, n - 1).doubleValue() + nthSmallestElement(coll, n).doubleValue()) / 2.0;// even number of items; find the middle two and average them
		}
		else{			
			result = nthSmallestElement(coll, n).doubleValue(); // odd number of items; return the one in the middle
		}

		return result;
	} // median(coll)

	
	private T nthSmallestElement(ArrayList<T> coll, int n) {
		T result, pivot;
		ArrayList<T> underPivot = new ArrayList<>(), overPivot = new ArrayList<>(), equalPivot = new ArrayList<>();

		// choosing a pivot is a whole topic in itself.
		// this implementation uses the simple strategy of grabbing something
		// from the middle of the ArrayList.

		pivot = coll.get(n / 2);

		// split coll into 3 lists based on comparison with the pivot

		for (T obj : coll) {
			int order = comp.compare(obj, pivot);

			if (order < 0) // obj < pivot
				underPivot.add(obj);
			else if (order > 0) // obj > pivot
				overPivot.add(obj);
			else// obj = pivot
				equalPivot.add(obj);
		} 

		// recurse on the appropriate list
		if (n < underPivot.size())
			result = nthSmallestElement(underPivot, n);
		else if (n < underPivot.size() + equalPivot.size()) // equal to pivot; just return it
			result = pivot;
		else
			// everything in underPivot and equalPivot is too small. Adjust n
			// accordingly in the recursion.
			result = nthSmallestElement(overPivot, n - underPivot.size() - equalPivot.size());

		return result;
	} 


}
