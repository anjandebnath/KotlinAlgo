
// Hint
// try using a queue to store all of the future Nodes at once
// that you need to explore as you traverse the graph.
// Every time you explore the new nodes from the queue, add the nodes' name to the array that we will return finally
// and add the nodes' children nodes to the queue
// and by using the FIFO property of the queue, you can traverse the graph in BFS way.
// When a new node is explored from the queue by polling out the node


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch {

    static class Node{

        String name;
        List<Node> children = new ArrayList<Node>();

        public Node(String name){
            this.name = name;
        }

        public List<String> breadthFirstSearch(List<String> array){

            Queue<Node> queue = new LinkedList<Node>();
            queue.add(this);
            while(!queue.isEmpty()){
                // take the head as the current node and remove from the poll
                Node currentNode = queue.poll();
                array.add(currentNode.name);
                queue.addAll(currentNode.children);
            }
            return array;
        }

        public Node addChild(String name) {
            Node child = new Node(name);
            children.add(child);
            return this;
        }
    }

    public static void main(String[] args){

        // keep all the nodes as the sequence of child and
        // parent
        Node startNode = new Node("A");
        startNode.addChild("B").addChild("C").addChild("D");
        startNode.children.get(0).addChild("E").addChild("F");
        startNode.children.get(2).addChild("G").addChild("H");
        startNode.children.get(0).children.get(1).addChild("I").addChild("J");
        startNode.children.get(2).children.get(0).addChild("K");


        List<String> array = new ArrayList<String>();
        // take the whole graph and apply BFS on that
        List<String> dfs = startNode.breadthFirstSearch(array);
        for (String df : dfs) {
            System.out.print("\'");
            System.out.print(df);
            System.out.print("\' ");
        }

    }
}
