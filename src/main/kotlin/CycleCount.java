import java.util.Arrays;

public class CycleCount {

    public static boolean cycleInGraph(int[][] edges){

        int numberOfNodes = edges.length;
        boolean[] visited = new boolean[numberOfNodes];
        boolean[] currentlyInStack = new boolean[numberOfNodes];
        Arrays.fill(visited, false);
        Arrays.fill(currentlyInStack, false);

        for(int node = 0; node< numberOfNodes; node++){
            if (visited[node]){
                continue;
            }

            // apply DFS graph traversal to find the cycle
            boolean containCycle = isNodeInCycle(node, edges, visited, currentlyInStack);
            if(containCycle){
                return true;
            }
        }

        return false;
    }

    public static boolean isNodeInCycle(
            int node, int[][] edges, boolean[] visited, boolean[] currentlyInStock
    ){

        // primarily the taken node is in action and in the visited list
        // and currentlyInStack list will be true
        // then take the neighboring nodes of that node
        // apply same for that
        visited[node] = true;
        currentlyInStock[node] = true;

        boolean containsCycle = false;
        int[]neighbors = edges[node]; // [node 0's edges are 1 and 3] [node 1's edges are 2,3 and 4]
        for (int neighbor: neighbors){

            if(!visited[neighbor]){
                containsCycle = isNodeInCycle(neighbor, edges, visited, currentlyInStock);
            }

            if(containsCycle){
                return true;
            }else if(currentlyInStock[neighbor]){ // if the ancestor still in stock
                // if a node is currently in stock,
                // and we reach it from a different node,
                // then we found a back edge
                return true;
            }
        }

        currentlyInStock[node] = false;
        return false;
    }

    public static void main(String[] args){
        // adjacency list
        int[][] input = new int[][]{
                {1,3},
                {2,3,4},
                {0},
                {},
                {2,5},
                {}
        };
        System.out.println(cycleInGraph(input));

    }


}
