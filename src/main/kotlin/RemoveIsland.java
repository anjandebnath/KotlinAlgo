
// Steps:
// create a boolean matrix of same width and length and assign all values to false
// but when we found a 1 at the border then we can set it to true
// Also we should apply DFS to find the neighboring 1s and also set those 1s true
// Apply DFS to the border area that is 1st row and 1st column and last row and last column
// and mark the 1s as true.
// After getting the border area simply find the inerior 1s that are ot connected with any border 1s.
// Then update 1s with 0s to remove the island

import java.util.ArrayList;
import java.util.Stack;

public class RemoveIsland {

    public static int[][] removeIslands(int[][] matrix){
        boolean[][] onesThatConnectedToBorder = new boolean[matrix.length][matrix[0].length];

        for (int i=0; i< matrix.length; i++){
            onesThatConnectedToBorder[i][matrix[0].length -1] = false;
        }

        // find all the 1s tht are not islands
        for(int row=0; row< matrix.length; row++){
            for(int col = 0; col< matrix[row].length; col++){
                boolean rowIsBorder = row == 0 || row == matrix.length -1;
                boolean colIsBorder = col == 0 || col == matrix[row].length -1;
                boolean isBorder = rowIsBorder || colIsBorder;

                if(!isBorder){
                    continue;
                }

                if(matrix[row][col] != 1){
                    continue;
                }

                findOnesConnectedToBorder(matrix, row, col, onesThatConnectedToBorder);

            }
        }

        for (int row = 1; row < matrix.length -1; row++){
            for(int col =1; col < matrix[row].length -1; col++){
                if(onesThatConnectedToBorder[row][col]){
                    continue;
                }
                matrix[row][col] = 0;
            }
        }

        return matrix;
    }

    public static void findOnesConnectedToBorder(
            int[][] matrix, int startRow, int startCol,
            boolean[][] onesConnectedToBorder
    ){

        // apply DFS to current node and neighbouring node
        // to find border 1s and it's neighbouring 1s
        Stack<int[]> stack = new Stack<int[]>();
        stack.push(new int[]{startRow, startCol});

        while(stack.size() > 0){
            int[] currentPosition = stack.pop();
            int currentRow = currentPosition[0];
            int currentCol = currentPosition[1];

            boolean alreadyVisited = onesConnectedToBorder[currentRow] [currentCol];
            if(alreadyVisited){
                continue;
            }

            onesConnectedToBorder[currentRow][currentCol] = true;

            int[][] neighbours = getNeighborsOfBorder(matrix, currentRow, currentCol);
            for(int[] neighbour: neighbours){
                int row = neighbour[0];
                int col = neighbour[1];

                if(matrix[row][col] != 1){
                    continue;
                }
                stack.push(neighbour);
            }
        }
    }

    public static int[][] getNeighborsOfBorder(int[][] matrix, int row, int col){

        int numRows = matrix.length;
        int numColumns = matrix[row].length;

        ArrayList<int[]> temp = new ArrayList<int[]>();

        if(row -1 >= 0){ //up
            temp.add(new int[]{row-1, col});
        }
        if (row + 1 < numRows){ // down
            temp.add(new int[]{row + 1, col});
        }
        if( col -1>= 0 ){ //left
            temp.add(new int[]{row, col -1});
        }
        if( col + 1 < numColumns){ // right
            temp.add(new int[]{row, col + 1});
        }

        // since the border row and columns are in consideration
        // so the length is always 2
        int[][] neighbors = new int[temp.size()][2];
        for (int i=0; i< temp.size(); i++){
            neighbors[i] = temp.get(i);
        }
        return neighbors;

    }

    public static void main(String[] args){

        int[][] matrix = new int[][]{
                {1, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 1, 1},
                {0, 0, 1, 0, 1, 0},
                {1, 1, 0, 0, 1, 0},
                {1, 0, 1, 1, 0, 0},
                {1, 0, 0, 0, 0, 1},
        };
        int[][] removalIsland = removeIslands(matrix);

        for(int i=0; i<removalIsland.length; i++){
            for(int j=0; j< removalIsland[i].length; j++){
                System.out.print(removalIsland[i][j]);
                System.out.print(", ");
            }
            System.out.print("\n");
        }

    }
}
