import java.util.*;

// A word can have repeated letters, but that must come from different positions
// Two or more words are allowed to overlap and use same letters

// form/create tries of the given words that we need to match
// [test, thought] both are the words that we need to match
// So, form tries of test and thought. Also for rest of the words do the same

// Now check if t is in the root node of our tries
// if yes, check all neighbours[Top, Down, left, right, diagonal] of t.
// Now, check if any of the neighbour character actually contained in our trie

// Once we find a character in the top level of the trie, we can say lets start
// looking at our neighbouring nodes and for the neighbouring node we will do the same.

// a clockwise DFS will do the job.
// at every branch that we are exploring, only for that branch we will keep track of the
// neighbouring nodes

public class BoggleBoard {

    public static List<String> boggleBoard(char[][] board, String[] words){

        // construct trie of words
        Trie trie = new Trie();
        for(String word : words) {
            trie.add(word);
        }

        Set<String> finalWords = new HashSet<String>();
        // make a 2D array of smae width and height of boogle board
        boolean[][] visited = new boolean[board.length][board[0].length];

        for(int i=0; i< board.length; i++){
            for(int j=0; j<board[0].length; j++){
                explore(i,j, board, trie.root, visited, finalWords);
            }
        }

        List<String> finalWordsArray = new ArrayList<String>();
        finalWordsArray.addAll(finalWords);


     return finalWordsArray;

    }

    public static void explore(
            int i,
            int j,
            char[][] board,
            TrieNode trieNode,
            boolean[][] visited,
            Set<String>finalWords
    ){
        if (visited[i][j]){
            return;
        }
        // traverse from the first node and all the position from [0,0]
        char letter = board[i][j];
        if(!trieNode.children.containsKey(letter)){
            return;
        }
        visited[i][j] = true;
        trieNode = trieNode.children.get(letter);

        if(trieNode.children.containsKey('*')){
            finalWords.add(trieNode.word);
        }

        // Integer is more flexible in storing and manipulating an int data
        // However in Integer we can directly convert its integer value to other bases such as
        // Binary, Octal or Hexadecimal format using toBinaryString(), toOctalString() or toHexString() respectively.
        List<Integer[]> neighbours = getNeighbours(i,j,board);
        for(Integer[] neighbour: neighbours){
            explore(neighbour[0], neighbour[1],
                    board, trieNode, visited, finalWords);
        }
        visited[i][j] = false;
    }

    // Clockwise DFS for left, right, UP, DOWN, Diagonal of all 4 side
    // total 8 check
    public static List<Integer[]>getNeighbours(int i, int j, char[][] board){

        List<Integer[]> neighbors = new ArrayList<Integer[]>();

        if(i>0 && j> 0){
            neighbors.add(new Integer[]{i-1, j-1});
        }
        if(i>0 && j<board[0].length-1){
            neighbors.add(new Integer[]{i-1, j+1});
        }
        if(i< board.length -1 && j<board[0].length-1){
            neighbors.add(new Integer[]{i+1, j+1});
        }
        if(i< board.length -1 && j > 0){
            neighbors.add(new Integer[]{i+1, j-1});
        }
        if(i> 0){
            neighbors.add(new Integer[]{i-1, j});
        }
        if(i< board.length -1){
            neighbors.add(new Integer[]{i+1, j});
        }
        if(j> 0){
            neighbors.add(new Integer[]{i, j-1});
        }
        if(j< board[0].length -1){
            neighbors.add(new Integer[]{i, j+1});
        }
        return neighbors;

    }

    static class TrieNode{

        Map<Character, TrieNode> children = new HashMap<>();
        String word = "";
    }

    static class Trie{

        TrieNode root;
        char endSymbol;

        public Trie(){
            this.root = new TrieNode();
            this.endSymbol = '*';
        }

        // here trie construction has only one for loop because
        // the string just needs to convert to trie
        // The string explore will be happened with the given 2D array
        public void add(String str) {
            TrieNode node = this.root;
            for(int i=0; i<str.length(); i++){
                char letter = str.charAt(i);
                if(!node.children.containsKey(letter)) {
                    TrieNode mewNode = new TrieNode();
                    node.children.put(letter, mewNode);
                }
                // make the currently added children as the root node
                node = node.children.get(letter);
            }
            node.children.put(this.endSymbol, null);
            node.word = str;
        }
    }

    public static void main(String[] args){

        char[][] board = {
                {'t', 'h', 'i', 's', 'i', 's', 'a'},
                {'s', 'i', 'm', 'p', 'l', 'e', 'x'},
                {'b', 'x', 'x', 'x', 'x', 'e', 'b'},
                {'x', 'o', 'g', 'g', 'l', 'x', 'o'},
                {'x', 'x', 'x', 'D', 'T', 'r', 'a'},
                {'R', 'E', 'P', 'E', 'A', 'd', 'x'},
                {'x', 'x', 'x', 'x', 'x', 'x', 'x'},
                {'N', 'O', 'T', 'R', 'E', '-', 'P'},
                {'x', 'x', 'D', 'E', 'T', 'A', 'E'},
        };
        String[] words = {
                "this", "is", "not", "a", "simple", "boggle", "board", "test", "REPEATED", "NOTRE-PEATED"
        };

        List<String> finalWordsArray=  boggleBoard(board, words);
        for(String finalWord: finalWordsArray){
            System.out.println(finalWord);
        }
    }
}
