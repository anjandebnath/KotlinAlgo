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

    public static LinkedList removeDuplicatesFromLinkedList(LinkedList linkedList){ // formal parameter

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

    //  Formal parameters are those parameters that are defined during function definition
    public static LinkedList addMany(LinkedList ll, List<Integer> values){ // Formal parameters
        // copy object to another object
        /**
         * original "current" object is released and available for garbage collection and
         * now "current" holds the reference of ll object, which mean current and ll are referring the same object now, which is ll.
         * any change on "current" object will be reflected on ll
         */
        LinkedList current = ll;
        while (current.next != null) {
            current = current.next;
        }

        // linked list formation
        for(int value: values){
            current.next = new LinkedList(value); // it will simultaneously update the ll object
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
         * Object reference has been passed
         */
        LinkedList linkedList = addMany(head, new ArrayList<>(Arrays.asList(1,3,4,4,4,5,6,6)));
        /**
         * In order to pass the reference, we pass the object of the class
         * in the place of the actual parameter, and the formal parameter of a class object type
         * has the same reference to each other that’s why with the help of the
         * formal parameter object of class any changes will be reflected in both objects formal and actual objects.
         */

        // Actual parameters are those which are passed during the function call in other Function
        LinkedList removeDuplicate = removeDuplicatesFromLinkedList(linkedList); // This is actual parameters
        List<Integer> nodes = getNodesInArray(removeDuplicate);
        for (int i=0; i<nodes.size(); i++){
            int node = nodes.get(i);
            System.out.print(node+" ");
        }
    }
}
