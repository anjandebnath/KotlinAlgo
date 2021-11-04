import java.util.ArrayList;

public class SumofLinkedList {
    /**
     * The least significant digit of an integer in a given base is the digit in the one's place.
     * For example, in base 10, the least significant digit of 1729 is 9.
     *
     * The first node in each linked list always represents the least significant digit and
     */

    public static class LinkedList{
        public int value;
        public LinkedList next;

        public LinkedList(int value){
            this.value = value;
            this.next = null;

        }
    }

    public static LinkedList sumOfLinkedList(LinkedList linkedListOne, LinkedList linkedListTwo){

        // This is a dummy linkedlist, it stores a dummy node whose .next
        // attribute will point to the head of our new LL.
        LinkedList newLinkedListHeadPointer = new LinkedList(0);
        LinkedList currentNode = newLinkedListHeadPointer;
        int carry = 0;

        LinkedList nodeOne = linkedListOne;
        LinkedList nodeTwo = linkedListTwo;

        while(nodeOne != null || nodeTwo != null || carry!= 0){
            int valueOne = (nodeOne != null) ? nodeOne.value : 0;
            int valueTwo = (nodeTwo != null) ? nodeTwo.value : 0;

            int sumOfValues = valueOne + valueTwo + carry;

            int newValue = sumOfValues % 10;
            LinkedList newNode = new LinkedList(newValue);
            currentNode.next = newNode; // this is the actual line that changes the newLinkedListHeaderPointer.next
            currentNode = newNode;

            carry =sumOfValues /10;
            nodeOne = (nodeOne != null) ? nodeOne.next : null;
            nodeTwo = (nodeTwo != null) ? nodeTwo.next : null;
        }

        return newLinkedListHeadPointer.next;
    }

    public static LinkedList addMany(LinkedList linkedList, int[] values) {
        LinkedList current = linkedList;
        while (current.next != null) {
            current = current.next;
        }
        for (int value : values) {
            current.next = new LinkedList(value);
            current = current.next;
        }
        return linkedList;
    }

    public static ArrayList<Integer> getNodesInArray(LinkedList linkedList) {
        ArrayList<Integer> nodeValues = new ArrayList<Integer>();
        LinkedList current = linkedList;
        while (current != null) {
            nodeValues.add(current.value);
            current = current.next;
        }
        return nodeValues;
    }


    public static void main(String[] args){

      LinkedList ll1 = addMany(new LinkedList(2), new int[] {4, 7, 1});
      LinkedList ll2 = addMany(new LinkedList(9), new int[] {4, 5});

        sumOfLinkedList(ll1, ll2);
    }
}
