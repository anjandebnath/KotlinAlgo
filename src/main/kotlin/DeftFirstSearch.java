

// Problem statement:
// Given a Node class that has a name and an array of optional children nodes.
// when put together, nodes form an acyclic tree-like structure.

//Hint
// the 2 important graph traversal algorithms are DFS and BFS
// DFS works by traversing a graph branch by branch. That means
// before traversing any node's sibling Nodes, it's children nodes must be traversed.

// how can you simply and effectively keep track of Node's sibling Nodes as you traverse
// them, all the while retaining the order in which you must traverse them?

// Graph representation can be done with 2 ways:
// using Neighbors list
// using Adjacency matrix
import java.util.ArrayList;
import java.util.List;

public class DeftFirstSearch {

    static class Node{

        String name;
        List<Node> children = new ArrayList<Node>();

        public Node(String name){
            this.name = name;
        }

        public List<String> depthFirstSearch(List<String> array){
            array.add(this.name); // put the current node name
            // traverse through children
            for (Node child : children) {
                child.depthFirstSearch(array);
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

        // parent node is A
        // Parent node has list of children
        // Each child of parent has list of children that is also connected with parent.
        // This is the graph data structure
        Node startNode = new Node("A");
        startNode.addChild("B").addChild("C").addChild("D");
        startNode.children.get(0).addChild("E").addChild("F");
        startNode.children.get(2).addChild("G").addChild("H");
        startNode.children.get(0).children.get(1).addChild("I").addChild("J");
        startNode.children.get(2).children.get(0).addChild("K");


        List<String> array = new ArrayList<String>();
        List<String> dfs = startNode.depthFirstSearch(array);
        for (String df : dfs) {
            System.out.print("\'");
            System.out.print(df);
            System.out.print("\' ");
        }

    }
}
