import kotlin.math.max
import kotlin.math.min

// array = [1,2,4,7,10,11,7,12,6,7,16,18,19]

fun subarraySort(array: List<Int>) : List<Int>{

    // find the minimum value and maximum value that are currently disorder
    var minOutOfOrderValue = Int.MAX_VALUE
    var maxOutOfOrderValue = Int.MIN_VALUE

    for(i in 0 until array.size){
        val currentNum = array[i]
        if(isCurrentNumIsOutOfOrder(i, currentNum, array)){
            minOutOfOrderValue = min(minOutOfOrderValue, currentNum)
            maxOutOfOrderValue = max(maxOutOfOrderValue, currentNum)
        }
    }

    if(minOutOfOrderValue == Int.MAX_VALUE){
        return listOf(-1, -1)
    }

    var subArrayLeftIdx = 0
    while (minOutOfOrderValue >= array[subArrayLeftIdx]){
        subArrayLeftIdx++
    }

    var subArrayRightIdx = array.size -1
    while (maxOutOfOrderValue <= array[subArrayRightIdx]){
        subArrayRightIdx--
    }

    return mutableListOf<Int>(subArrayLeftIdx, subArrayRightIdx)

}

fun isCurrentNumIsOutOfOrder(currentPosition: Int, currentNum: Int, array: List<Int>) : Boolean{

    if (currentPosition == 0){
        return currentNum > array[currentPosition+1]
    }else if (currentPosition == array.size -1){
        return currentNum < array[currentPosition-1]
    }

    return currentNum > array[currentPosition+1] || currentNum < array[currentPosition-1]

}

fun main(args: Array<String>){
    val array = mutableListOf<Int>(1,2,4,7,10,11,7,12,6,7,16,18,19)
    print(subarraySort(array))
}