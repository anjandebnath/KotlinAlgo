import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ClassPhotos {

    public static boolean classPhotos(ArrayList<Integer> redShirtHeights,
                               ArrayList<Integer> blueShirtHeights){

        // Short the heights in reverse order
        Collections.sort(redShirtHeights, Collections.reverseOrder());
        Collections.sort(blueShirtHeights, Collections.reverseOrder());

        String shirtColorInFirstRow = (redShirtHeights.get(0) < blueShirtHeights.get(0)) ? "RED" : "BLUE";

        for(int idx = 0; idx< redShirtHeights.size(); idx++){
            int redShirtHeight = redShirtHeights.get(idx);
            int blueShirtHeight = blueShirtHeights.get(idx);

            if(shirtColorInFirstRow == "RED"){
                if(redShirtHeight >= blueShirtHeight){
                    return false;
                }
            }
            else{
                if(blueShirtHeight >= redShirtHeight){
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args){
        ArrayList<Integer> redShirtHeights = new ArrayList<Integer>(Arrays.asList(5, 8, 1, 3, 4));
        ArrayList<Integer> blueShirtHeights = new ArrayList<Integer>(Arrays.asList(6, 9, 2, 4, 5));
        boolean isPossible = classPhotos(redShirtHeights,blueShirtHeights);
        System.out.print(isPossible);
    }
}
