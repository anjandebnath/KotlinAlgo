import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinHeapConstruct {

    static class MinHeap{
        // Primitive type
        List<Integer> heap = new ArrayList<Integer>();

        public MinHeap(List<Integer> array){
            heap = buildHeap(array);
        }

        // from the arraylist of given nodes
        // calculate the first parent index with the equation (array list.size()-2/2)
        public List<Integer> buildHeap(List<Integer> array){
            // first parent index rule
            int firstParentIdx = (array.size() -2) / 2;
            // it is not always true that the first parent index will be the parent
            // so if the initial parent index doesn't satisfy then decrease the parent idx gradually until it is not less than 0.
            for (int currentIdx = firstParentIdx; currentIdx >= 0; currentIdx--) {
                siftDown(currentIdx, array.size() -1, array);
            }
            return array;
        }

        public void siftDown(int currentIdx, int endIdx, List<Integer> heap) {
            int childOneIdx = (currentIdx * 2) + 1;
            // if the childOneIdx is less than or equal to endIdx then go for the childTwoIdx
            while (childOneIdx <= endIdx){
                // if the childTwoIdx can't be found due to endIdx overflow then set it -1
                // and swap
                int childTwoIdx = (currentIdx * 2) + 2 <= endIdx ? (currentIdx * 2) + 2 : -1;
                int idxToSwap;

                // if the left id(childOneIdx) is greater than right id (childTwoIdx)
                // then current idx need to be swaped with the right id (childTwoIdx)
                if(childTwoIdx != -1 && heap.get(childTwoIdx) < heap.get(childOneIdx)){
                    idxToSwap = childTwoIdx;
                }else{
                    idxToSwap = childOneIdx;
                }

                if(heap.get(idxToSwap) < heap.get(currentIdx)){
                    swap(currentIdx, idxToSwap, heap);
                    currentIdx = idxToSwap;
                    childOneIdx = currentIdx *2 +1;
                }else{
                    return;
                }
            }
        }

        public void shiftUp(int currentIdx, List<Integer> heap) {

            // here initially current idx would be the size of the heap, say its 15
            // to get the actual index, it needs to be 14. and also to get the parent index
            // since the binary tree is considered, so also another 1 will be decreased.
            int parentIdx = (currentIdx -1) / 2;
            while (currentIdx > 0 && heap.get(currentIdx) < heap.get(parentIdx)) {
                swap(currentIdx, parentIdx, heap);
                currentIdx = parentIdx;
                parentIdx = (currentIdx -1) /2;
            }
        }

        public void swap(int i, int j, List<Integer> heap){
            Integer temp = heap.get(j);
            heap.set(j, heap.get(i));
            heap.set(i, temp);
        }

        public void insert(int value){
            // add the value at the end of the heap
            // then take the idx of the value and shiftup
            heap.add(value);
            shiftUp(heap.size()-1, heap);
        }

        public int peek(){
            return heap.get(0);
        }

        public int remove(){
            swap(0, heap.size()-1, heap);
            int valueToRemove = heap.get(heap.size() -1);
            heap.remove(heap.size() -1);
            siftDown(0, heap.size()-1, heap);
            return valueToRemove;
        }
    }


    public static void main(String[] args){

        List<Integer> array = new ArrayList<Integer>(
                Arrays.asList(48, 12, 24, 7, 8, -5, 24, 391, 24, 56, 2, 6, 8, 41));

        MinHeap meanHeap = new MinHeap(array);
        meanHeap.insert(16);

        for (int i=0; i<meanHeap.heap.size(); i++){
            System.out.print(meanHeap.heap.get(i)+", ");
        }
    }

}
