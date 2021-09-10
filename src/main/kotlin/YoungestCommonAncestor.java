import java.util.HashMap;

public class YoungestCommonAncestor {

    public static AncestralTree getYoungestCommonAncestor(
            AncestralTree topAncestor, AncestralTree descendantOne,
            AncestralTree descendantTwo){

        // calculate the depth
        int depthOfDescendantOne = getDescendantDepth(descendantOne, topAncestor);
        int depthOfDescendantTwo = getDescendantDepth(descendantTwo, topAncestor);

        // if depth is not same then make the depth equal for the 2 descendants
        // Then we can easily find hte common ancestor
        if(depthOfDescendantOne > depthOfDescendantTwo){
            return backtrackAncestralTree(descendantOne, descendantTwo, depthOfDescendantOne - depthOfDescendantTwo);
        }else{
            return backtrackAncestralTree(descendantTwo, descendantOne,  depthOfDescendantTwo - depthOfDescendantOne);
        }

    }

    public static int getDescendantDepth(AncestralTree descendant, AncestralTree topAncestor){
        int depth = 0;
        while(descendant != topAncestor){
            depth++;
            descendant = descendant.ancestor;
        }
        return depth;
    }

    public static AncestralTree backtrackAncestralTree(
            AncestralTree lowerDescendent, AncestralTree higherDescendant, int diff
    ){

        // make their leve same
        while (diff > 0){
            lowerDescendent = lowerDescendent.ancestor;
            diff--;
        }

        while (lowerDescendent != higherDescendant){
            lowerDescendent = lowerDescendent.ancestor;
            higherDescendant = higherDescendant.ancestor;
        }

        return lowerDescendent;

    }

    static class AncestralTree{

        public char name;
        public AncestralTree ancestor;

        AncestralTree(char name){
            this.name = name;
            // normally no node contains ancestor
            // when a node become a descendant then it will have ancestor
            this.ancestor = null;
        }

        // add the node as the ancestor of descendants
        // It is needed to test
        void addAsAncestor(AncestralTree[] descendants){
            for(AncestralTree descendant: descendants){
                // add the ancestor of the descendant
                descendant.ancestor = this;
            }
        }
    }



    public static void main(String[] args){

        // keep all the nodes as the sequence of child and
        // parent
        HashMap<Character, AncestralTree> trees = new HashMap<>();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for(char a: alphabet.toCharArray()){
            trees.put(a, new AncestralTree(a));
        }

        trees.get('A').addAsAncestor(new AncestralTree[]{trees.get('B'), trees.get('C')});
        trees.get('B').addAsAncestor(new AncestralTree[]{trees.get('D'), trees.get('E')});
        trees.get('D').addAsAncestor(new AncestralTree[]{trees.get('H'), trees.get('I')});
        trees.get('C').addAsAncestor(new AncestralTree[]{trees.get('F'), trees.get('G')});



        AncestralTree YCD = getYoungestCommonAncestor(trees.get('A'), trees.get('E') , trees.get('I'));
        System.out.println(YCD.name);

    }
}
