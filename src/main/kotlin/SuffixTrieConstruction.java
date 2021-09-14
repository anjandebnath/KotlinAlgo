// Suffix creation
// bunch of hashtable will be used, where each hash table key points to another hashtable
// every node in the suffix trie will be pointed to another hashtable

// start from the root node and that root node will be mapped to an empty hashtable

// traverse the first node (b) from suffix trie and check if the node is contained
// in the hash table that the root node maps to. If not then add that node in the hash table.
// Now make node b as the acting root and add an empty hashtable that will point to this new node (b)
// Now traverse the second node (a) of the suffix trie and check if the node contained in the hashtable
// that the node b maps to.


// Suffix search

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SuffixTrieConstruction {

    static class TrieNode{
        // HashMap allows one null key and multiple null values whereas Hashtable doesn’t allow any null key or value.
        Map<Character, TrieNode> children = new HashMap<Character, TrieNode>();

        public String toString(){
            return this.children.toString() ;
        }
    }

    static class SuffixTrie{
        TrieNode root = new TrieNode();
        char endSymbol = '*';

        public SuffixTrie(String str){
            populateSuffixTrieFrom(str);
        }

        // suffix trie creation
        public void populateSuffixTrieFrom(String str){
            for (int i=0; i< str.length(); i++){
                insertSubstringStartingAt(i,str);
            }
        }

        public void insertSubstringStartingAt(int i, String str){
            TrieNode node = root;
            for (int j=i; j<str.length(); j++){
                char letter = str.charAt(j);
                if(!node.children.containsKey(letter)){
                    TrieNode newNode = new TrieNode();
                    node.children.put(letter, newNode);
                }
                // make the new children node as root node
                // so new
                node = node.children.get(letter);
            }
            node.children.put(endSymbol, null);
        }

        // suffix trie search
        public boolean contains(String str){
            TrieNode node = root;
            for (int i=0; i<str.length(); i++){
                char letter = str.charAt(i);
                if(!node.children.containsKey(letter)){
                    return false;
                }
                // make the currently added new node as the root node
                node = node.children.get(letter);
            }
            // if the end symbol is not contained then it will be false
            return node.children.containsKey(endSymbol);
        }


        public String toString(){
            return this.root+"";
        }
    }

    public static void main(String[] args){
        SuffixTrie trie = new SuffixTrie("babc");
        boolean isContains = trie.contains("ab");

        System.out.println(Arrays.asList(trie));
        System.out.println(isContains);




    }
}
