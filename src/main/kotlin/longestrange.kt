// array = [1, 11, 3, 0, 15, 5, 2, 4, 10, 7, 12, 6 ]
fun largestRange(array: List<Int>) : Pair<Int, Int>{ // List is immutable

    var bestRange = Pair(array[0], array[0])
    var longestLength = 0

    // the array elements can be simply sorted and measure the range but
    // it would take linearithmic O(NLogN) time complexity

    // to search the array elements very fast with Linear O(N) complexity
    // we can preload the array elements in another data structure
    val nums = mutableMapOf<Int, Boolean>()

    // to ensure the array elements not iterated again we could point
    // each elements in the hash table with boolean
    for (num in array){
        nums[num] = true
    }
    for (num in array){
        if (nums[num] == false)
            continue
        nums[num] = false
        var currentLength = 1
        // check if the previous value (0) and next value (2)  of the current num (1)
        // value is present in the nums map
        var leftValue = num -1
        var rightValue = num + 1


        while (nums.containsKey(leftValue)){
            nums[leftValue] = false
            currentLength ++
            leftValue--
        }

        while (nums.containsKey(rightValue)){
            nums[rightValue] = false
            currentLength ++
            rightValue ++
        }

        if (currentLength > longestLength){
            longestLength = currentLength
            bestRange = Pair(leftValue+1, rightValue-1)
        }

    }
    return bestRange
}

fun main(args: Array<String>){ // Array is mutable in kotlin

    val array = mutableListOf<Int>(1, 11, 3, 0, 15, 5, 2, 4, 10, 7, 12, 6)
    print(largestRange(array))
}