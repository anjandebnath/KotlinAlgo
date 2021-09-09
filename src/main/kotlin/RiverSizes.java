//Hint1
// Since you must return the sizes of river, which consist of horizontally
// and vertically adjacent 1s in the input matrix,
// you must somehow keep track of group of neighbouring 1s as you traverse the matrix.

// Try treating the matrix as a graph, where each element in the matrix is a node
// in the graph with upto 4 neighbouring nodes(above, below, left and right)
// and traverse it using a popular graph traversal algorithm like DFS or BFS

// Hint2
// By traversing the matrix using DFS or BFS, anytime that you encountered a
// 1, you can traverse the entire river by simply iterating through the given
// node's neighbouring nodes and their own neighbouring nodes so long as the
// nodes are 1s


// Solution

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 1 0 0 1 0
 * 1 0 1 0 0
 * 0 0 1 0 1
 * 1 0 1 0 1
 * 1 0 1 1 0
 */

// start from the starting node
// keep track of the nodes that we already visited
// if the current node is already visited then skip it
// if the node value is 1 then traverse the neighbouring nodes (left, right, above, below)
// Consider the edge cases if there is no neighbouring nodes at the edges
// traveral algorithm can be DFS or BFS
// DFS: recursively traverse the neighbouring nodes
// BFS: Iteratively traverse the neighbouring nodes
//       add the neighbouring nodes to a queue
//       pop the current node from the queue and add it's children node to the queue


public class RiverSizes {

    public static List<Integer> riverSizes(int[][] matrix){

        List<Integer> sizes = new ArrayList<Integer>();
        // to keep track of the already visited nodes
        // declare an array of same width[row] and height[column]
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        for (int i=0; i< matrix.length; i++){
            for(int j=0; j<matrix[0].length; j++){
                // if the node already visited then skip
                if(visited[i][j]){
                    continue;
                }
                traverseNode(i,j, matrix, visited, sizes);
            }
        }

       return sizes;
    }

    public static void traverseNode(
            int i, int j, int[][] matrix, boolean[][] visited,
            List<Integer> sizes
    ){
        int currentRiverSize = 0;
       // traverse through iteratively
       // stack is for LIFO
       // why stack: Since during node traversal, we will traverse the nodes and their immediate neighbors so
       // priority is always the current node and their neighbours
        Stack<Integer[]> nodesToExplore = new Stack<Integer[]>();
        nodesToExplore.push(new Integer[]{i, j});
        while (!nodesToExplore.empty()){
            Integer[] currentNode = nodesToExplore.pop();
            i = currentNode[0];
            j = currentNode[1];
            // if node already visited skip it
            if (visited[i][j]){
                continue;
            }
            visited[i][j] = true;
            // if the node is 0 then skip it
            if(matrix[i][j] == 0){
                continue;
            }
            currentRiverSize+= 1;
            // Traverse left, right, above and below and list down the actual unvisited neighbors
            List<Integer[]> unvisitedNeighbours = getUnvisitedNeighbours(i,j,matrix,visited);
            for(Integer[] neighbour: unvisitedNeighbours){
                nodesToExplore.add(neighbour);
            }
        }

        if(currentRiverSize > 0){
            sizes.add(currentRiverSize);
        }
    }

    public static List<Integer[]> getUnvisitedNeighbours(int i, int j, int[][] matrix, boolean[][] visited){

        List<Integer[]> unvisitedNeighbours = new ArrayList<>();
        //right  = arr2[r][c+1];
        //left  = arr2[r][c-1];
        //up  = arr2[r-1][c];
        //down  = arr2[r+1][c];

        if(i>0 && !visited[i-1][j]){ //up
            unvisitedNeighbours.add(new Integer[]{i-1, j});
        }
        if (i< matrix.length-1 && !visited[i+1][j]){ // down
            unvisitedNeighbours.add(new Integer[]{i+1, j});
        }
        if( j>0 && !visited[i][j-1]){ //left
            unvisitedNeighbours.add(new Integer[]{i, j-1});
        }
        // need to understand why matrix[0]
        if( j< matrix[0].length-1 && !visited[i][j+1]){ // right
        //if( j< matrix.length-1 && !visited[i][j+1]){ // right
            unvisitedNeighbours.add(new Integer[]{i, j+1});
        }

        return unvisitedNeighbours;
    }




    public static void main(String[] args){
        /*int[][] input = {
                {1, 0, 0, 1, 0},
                {1, 0, 1, 0, 0},
                {0, 0, 1, 0, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 1, 0},
        };*/

        int[][] input = {
        {1, 1, 0, 0, 0, 0, 1, 1},
        {1, 0, 1, 1, 1, 1, 0, 1},
        {0, 1, 1, 0, 0, 0, 1, 1}

        };

        System.out.println(riverSizes(input));

    }
}
