import kotlin.math.max

fun minRewards(scores: List<Int>) : Int{

    // initialize the rewards array for all indices with 1
    val rewards = MutableList(scores.size) {1}
    // to reduce time complexity we can apply technique of finding local min(valley) and local max(peak)
    // local mins are minimum value that are minimum to the 2 elements right next to them
    // the scores given in the input follow a pattern
    // local mins will get the minimum rewards and local maxs will get maximum rewards
    val localMinIdxs = getLocalMinIdx(scores)

    for (localMinIdx in localMinIdxs){
        // expand from local min until the local max is encountered in both right and left
        expandFromLocalMinIdx(localMinIdx, scores, rewards)
    }
    return rewards.sum()
}

fun getLocalMinIdx(array: List<Int>): List<Int>{
    // always check if array size is lowest or not for this particular operation,
    // during the design of the algorithm need to check whether array size has any dependency on output
    if(array.size == 1) return listOf<Int>(0) //readonly access
    val localMinIdxs = mutableListOf<Int>() // read and write access
    // calculate the trend to get the minimum peak value
    for(i in 0 until array.size){
        if (i==0 && array[i] < array[i+1]) localMinIdxs.add(i) // first value
        if (i== array.size -1 && array[i] < array[i-1])  localMinIdxs.add(i)// penaltymate value
        if (i== 0 || i == array.size -1) continue // edge cases
        // expand to the left and expand to the right
        if (array[i]< array[i+1] && array[i]< array[i-1]) localMinIdxs.add(i) // local min
    }

 return localMinIdxs

}

fun expandFromLocalMinIdx(localMinIdx: Int, scores: List<Int>, rewards:MutableList<Int>){

    // expand to the left until the next peak value
    // if the previous value of the local min is getter
    // then calculate the max value of the 2 rewards and which one is grater that will be the value.
    var leftIdx = localMinIdx -1
    while (leftIdx >= 0 && scores[leftIdx] > scores[leftIdx+1]){
        rewards[leftIdx] = max(rewards[leftIdx], rewards[leftIdx+1]+1)
        leftIdx--
    }

    // expand to the right
    //
    var rightIdx = localMinIdx +1
    while (rightIdx< scores.size && scores[rightIdx] > scores[rightIdx -1]){
        rewards[rightIdx] = rewards[rightIdx -1] +1
        rightIdx++
    }

}

//min_reward
// rather finding the local mins and expand from local min this can be solved easily
// iterate from left to right to entire array and compare with the previous number

// start from 2nd number because 1st one has no previous value to compare
// if current number is greater than previous number then increase reward value, otherwise skip it

// iterate form right to left to entire array and compare with previous number
// start from 2nd number because 1st one has no next value to compare
// if current number is greater than next number then calculate max reward value among them, otherwise skip it


fun main(args: Array<String>){
    val array = mutableListOf<Int>(8,4,2,1,3,6,7,9,5)

    print(minRewards(array))
}