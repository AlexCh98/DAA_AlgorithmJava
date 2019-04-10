package Ivan.CosasQueNoSonMias.DivideYVenceras;

import java.util.Comparator;

public class BinarySearch<E> {

	private Comparator<E> comp;

	public BinarySearch() {
		this.comp = new DefaultComparator<>();
	}

	public BinarySearch(Comparator<E> c) {
		this.comp = c;
	}

	public int binarySearch(E[] inputArr, E key) {
			return  binarySearchRec(inputArr, key, 0, inputArr.length-1);
	}

	private int binarySearchRec(E[] inputArr, E key, int start, int end) {
		int mid = (start + end) / 2;
		E midValue = inputArr[mid];
		if(start > end){
			return -1;
		}
		else{
			if (key.equals(midValue)) {
			//if (key  == midValue) {
				return mid;
			}
			else if (this.comp.compare(key, midValue) > 0) {
				return binarySearchRec(inputArr, key, mid+1, end);
			} 
			else {
				return binarySearchRec(inputArr, key, start, mid-1);
			}
		}
	}

}
