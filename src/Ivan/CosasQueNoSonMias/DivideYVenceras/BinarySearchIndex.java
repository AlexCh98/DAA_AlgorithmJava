package Ivan.CosasQueNoSonMias.DivideYVenceras;

public class BinarySearchIndex {

    public int indexSearch(int[] inputArr, int index) {
        return indexSearchRec(inputArr, index, 0, inputArr.length - 1);
    }

    private int indexSearchRec(int[] inputArr, int index, int start, int end) {
        int mid = (start + end) / 2;
        int midValue = inputArr[mid];
        if (start > end) {
            return -1;
        } else {
            if (midValue == index) {
                return index;
            } else if (index > midValue){
                return indexSearchRec(inputArr, index,mid+1, end);
            } else {
                return indexSearchRec(inputArr, index,start, mid-1);
            }
        }
    }
}
