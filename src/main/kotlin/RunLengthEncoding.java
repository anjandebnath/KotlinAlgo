
// direct string use is not good practice for it's immutable constraint
// we should use char list to mutable the list at any time
// after that the char list would be converted to string

// if the input length is 1 that is only A
// then char list would be length, last char of the input string

// if the length is greater than 1 then
// check if previous char and current char is same
// if same then increase the current length
// if not same then put current length and the previous char in the char list
public class RunLengthEncoding {

    public String runLengthEncoding(String input){

        StringBuilder encodedStringchars = new StringBuilder();
        int currentLength = 1;

        for(int i=1; i<input.length(); i++){

            char currentChar = input.charAt(i);
            char prevChar = input.charAt(i-1);

            if((prevChar != currentChar) || (currentLength == 9)){
                encodedStringchars.append(Integer.toString(currentLength));
                encodedStringchars.append(prevChar);
                currentLength = 0;
            }

            currentLength+=1;
        }

        encodedStringchars.append(Integer.toString(currentLength));
        encodedStringchars.append(input.charAt(input.length()-1));

    return encodedStringchars.toString();
    }

    public static void main (String[] args){

        RunLengthEncoding encoding = new RunLengthEncoding();
        String input = "aaaaaaaabbbccd";
        String output = encoding.runLengthEncoding(input);
        System.out.println(output);
    }
}
