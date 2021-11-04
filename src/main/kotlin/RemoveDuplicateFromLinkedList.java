import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemoveDuplicateFromLinkedList {

    public static class LinkedList{

        public int value;
        public LinkedList next;

        public LinkedList(int value){
            this.value = value;
            this.next = null;
        }
    }

    public static LinkedList removeDuplicatesFromLinkedList(LinkedList linkedList){
        LinkedList currentNode = linkedList;
        while(currentNode != null) {

            LinkedList nextDistinctNode = currentNode.next;
            while(nextDistinctNode!= null && nextDistinctNode.value == currentNode.value){
                nextDistinctNode = nextDistinctNode.next;
            }

            // why currentNode.next is not directly assigned to currentNode?
            //https://medium.com/swlh/java-passing-by-value-or-passing-by-reference-c75e312069ed
            currentNode.next = nextDistinctNode; // this will assign to the main linkedList next value
            currentNode = nextDistinctNode;
        }
        return linkedList;
    }

    // form a linkedlist from the list of integers
    // the head of linked list will be the first value
    // current linked list is used to convert the current array value
    // to a linked list and linked it to main linked list
    public static LinkedList addMany(LinkedList ll, List<Integer> values){
        // copy object to another object
        /**
         * original current object is released and available for garbage collection and
         * now current holds the reference of ll object, which mean current and ll are referring the same object now, which is ll.
         */
        LinkedList current = ll;
        while (current.next != null) {
            current = current.next;
        }

        // linked list formation
        for(int value: values){
            current.next = new LinkedList(value);
            current = current.next;
        }

        return ll;
    }

    public static List<Integer> getNodesInArray(LinkedList ll){
        List<Integer> nodes = new ArrayList<>();
        LinkedList current = ll;

        while (current != null) {
            nodes.add(current.value);
            current = current.next;
        }
        return nodes;
    }



    public static void main(String[] args){

        /**
         * A singly linked list is represented by a list of nodes and a head node.
         * Every node has to have a unique string id that will be referenced
         * by other nodes' next pointers and by the head
         */
        LinkedList head = new LinkedList(1);

        /**
         * to form linkedlist arraylist get converted to linkedList
         */
        LinkedList linkedList = addMany(head, new ArrayList<>(Arrays.asList(1,3,4,4,4,5,6,6)));
        LinkedList removeDuplicate = removeDuplicatesFromLinkedList(linkedList);
        List<Integer> nodes = getNodesInArray(removeDuplicate);
        for (int i=0; i<nodes.size(); i++){
            int node = nodes.get(i);
            System.out.print(node+" ");
        }
    }
}
