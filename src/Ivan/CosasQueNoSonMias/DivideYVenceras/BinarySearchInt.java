package Ivan.CosasQueNoSonMias.DivideYVenceras;

public class BinarySearchInt {
	
	public int binarySearch(int[] inputArr, int key) {
		
		return binarySearchRec(inputArr,  key, 0, inputArr.length-1);
		
	}
	
	private int binarySearchRec(int[] inputArr, int key, int start, int end) {
		int mid = (start + end) / 2;
		int midValue = inputArr[mid];
		if(start > end){
			return -1;
		}
		else{
			if (key == midValue) {
				return mid;
			}
			else if (key > midValue) {
				return binarySearchRec(inputArr, key, mid+1, end);
			} 
			else {
				return binarySearchRec(inputArr, key, start, mid-1);
			}
		}
	}
}
