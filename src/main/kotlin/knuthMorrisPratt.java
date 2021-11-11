import java.util.Arrays;

public class knuthMorrisPratt {


    // start with the comparison string that is the shorter screen to form a pattern of integers
    // at first take an array called pattern and fill the value with -1 for all index
    public static int[] buildPattern(String subString){

        int[] pattern = new int[subString.length()];
        Arrays.fill(pattern, -1);
        int j=0;
        int i=1;

        // begin with the first 2 adjacent char from 0 and 1
        while(i< subString.length()){
            // if the 2 chars are same then update the upper index's pattern value with lower index
            // it means we have identified a pattern where the prefix and suffix of the pattern are same
            // i and j value will indicate the pattern suffix and prefix respectively and
            // we need to update the suffix pattern value with the reference of the prefix
            if(subString.charAt(i) == subString.charAt(j)){
                pattern[i] = j;
                i++;
                j++;
            } else if(j>0){
                // if 1st logic not true and j is greater than 0
                // lets check the letter right before the index j is
                // and lets check if the letter has a pattern before as if the pattern value is not -1
                j = pattern[j-1] + 1;
            } else{
                i++;
            }
        }
        return pattern;
    }

    public static boolean knuthMorrisPatternAlgorithm(String string, String subString){
        int[] pattern = buildPattern(subString);
        return doesMatch(string, subString, pattern);
    }

    // it needs to match the whole substring, not a part of substring
    public static boolean doesMatch(String string, String subString, int[] pattern){
        int i=0; // initiate from string
        int j=0; // initiate from subString

        // substring is always less than largeString
        // to verify the while loop until i-j is less than the large string length

        /**
         *
         * if the large string length is 21 and substring length is 3 then
         * to check the pattern in large string , the length upto (21-3 = 18 + 1 =) 19
         * is enough.
         *
         */
        while(i+subString.length() - j <= string.length()){
            if(string.charAt(i) == subString.charAt(j)) {
                if(j== subString.length() -1) return true;
                i++;
                j++;
            } else if (j>0) {
                // this will find the previous letter which was occurred
                j = pattern[j-1] +1;
            } else{
                i++;
            }
        }
        return false;
    }

    public static void main(String[] args){

        String longString = "adafccfefbbbfeeccbcfd";
        String subString = "ecb";
        boolean isPattern = knuthMorrisPatternAlgorithm(longString, subString);
        System.out.println(isPattern);
    }

}
