import java.util.ArrayList;
import java.util.List;

public class FindLoopLinkedList {

    // A Singly Linked List is represented by a list of nodes and a head node.
    // Every node has to have a unique string id that will be referenced by other nodes' next pointers and by the head.
    static class LinkedList{

        int value;
        LinkedList next = null;

        public LinkedList(int value){
            this.value = value;
        }

        public void addMany(int[] values) {
            LinkedList current = this;
            while (current.next != null) {
                current = current.next;
            }
            for (int value : values) {
                current.next = new LinkedList(value);
                current = current.next;
            }
        }

        public LinkedList getNthNode(int n) {
            int counter = 1;
            LinkedList current = this;
            while (counter < n) {
                current = current.next;
                counter++;
            }
            return current;
        }
    }

    public static LinkedList findLoop(LinkedList head){
        LinkedList first = head.next;
        LinkedList second = head.next.next;
        while(first != second){
            first = first.next;
            second = second.next.next;
        }

        first = head;
        while(first != second) {
            first = first.next;
            second = second.next;
        }
        return first;
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


        LinkedList head = new LinkedList(0);
        head.addMany(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9});
        // last node will be pointed to some other node in the linked list
        head.getNthNode(10).next = head.getNthNode(5);
        LinkedList l1 = findLoop(head);
        List<Integer> nodes = getNodesInArray(l1);
        /*for (int i=0; i<nodes.size(); i++){
            int node = nodes.get(i);
            System.out.print(node+" ");
        }*/
    }
}
