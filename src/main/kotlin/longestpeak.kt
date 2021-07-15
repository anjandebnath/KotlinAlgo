import kotlin.math.max

//"array": [1, 2, 3, 3, 4, 0, 10, 6, 5, -1, -3, 2, 3]
// find the tip of the peak
// traverse from tip to left and right until decrease
fun longestPeak(array: List<Int>) : Int {

    var longestPeakLength: Int = 0
    var pointer = 1 // to be a peak minimum 3 number needed and adjacent previous and post value is also important

    while (pointer < array.size -1){

        // triangular shape is the peak.
        // a value will be the tip of the peak if it's strictly greater than the two
        // adjacent values to it
        val isTipOfPeak = array[pointer-1] < array[pointer]
                && array[pointer+1] < array[pointer] // when value is immutable and used by other then it is val
        if (!isTipOfPeak){
            pointer++
            continue
        }

        // to calculate the length of the peak we need to calculate
        // how long the values strictly decreasing from left and right of the tip of the peak

        var leftDistance = pointer -2 // when the value is mutable and change by other conditions then var
        while (leftDistance >= 0 && array[leftDistance] < array[leftDistance+1]){
            leftDistance --  // increase the length
        }

        var rightDistance = pointer +2
        while (rightDistance < array.size && array[rightDistance] < array[rightDistance-1]){
            rightDistance ++ // increase the length
        }

        val currentPeakLength = rightDistance -leftDistance -1
        longestPeakLength = max(longestPeakLength, currentPeakLength)

        pointer = rightDistance // next peak count will be started just after the ending of the current peak

    }
    return longestPeakLength

}

fun main(array: Array<String>){

    val array = mutableListOf(1, 3, 2)
    print(longestPeak(array))
}