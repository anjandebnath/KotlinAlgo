// hashing based solution
// Store sums of all pair in the hash table
// traverse through all pairs again search for X - (current pair sum) in the hash table
// if a pair is found with required sum, then make sure all elements are distinct array elements

fun fourNumberSum(array: MutableList<Int>, targetSum: Int) : List<List<Int>>{

    // quadruplet can be presented into pair of numbers
    // x,y,z,k can be paired x,y = P  z,k = Q
    // sum of x&y = P and sum of z&k = Q

    // find the sum of all the possible pairs in the array
    // finding P and Q , we need to keep track of which nums are used to generate that P and Q
    // So, in hashtable, we will store the number P and it's corresponding pairs of numbers (x&y)

    // the number P might be obtained by summing up a bunch of pairs of numbers in the input array
    // e.g [7,6,4, -1, 1, 2] here if P=6 then it can be formed through {4,2} or {7,-1}
    // So in the hashtable we need to store a list of pairs of numbers that sumup to the big number P.

    // the pairs of numbers should not be double count in the quadruplet
    // e.g: [7,6,4,-1] and [6,4,7,-1] both form 16 but the numbers are double count in the array

    // for that we will use 2 inner for loop

    // the first inner for loop will come just after the number of main for loop
    // then calculate P (sum of pairs) [4+(-1) or 4+1 or 4+2] for all numbers in the inner for loop and the number of main for loop
    // then calculate the difference with the target and we check if the difference is in the hashtable.
    // if the difference is in the hashtable then then we found our quadruplet

    // but if it isn't (the difference is not in the hashtable) then we will not add the sum in the has table yet,
    // then we will iterate through 2nd for loop through all the numbers before the numbers of 1st inner loop
    // and calculate the pair Q [7+4 or 6+4] and store in th hashtable

    // map contains int key and a list of List of Integers value
    val allPairSums = mutableMapOf<Int, MutableList<MutableList<Int>>>()
    val quadruples = mutableListOf<MutableList<Int>>()

    // we start from 1 here because for i=0 the if condition of 1st inner for loop will
    // never be true because there will be no value in the hash table
    for(i in 1 until array.size -1){

        for (j in i+1 until array.size){
            val currentSum = array[i] + array[j]
            val difference = targetSum - currentSum
            // if the difference is contained in the hashmap then it is must true that every single pair of that difference
            // like difference = 13 so pair can be [7,6] and [9,4] will be in the quadruplets
            if (allPairSums.containsKey(difference)){
                for (pair in allPairSums[difference]!!){ //the not-null assertion operator (!!) converts any value to a non-null type and throws an exception if the value is null
                    val p = pair.toMutableList<Int>() // form the list with pair [7,6]
                    p.add(array[i]) //[7,6,4]
                    p.add(array[j]) // 7,6,4,1]
                    quadruples.add(p)
                }
            }
        }

        // to ensure same pair is not get duplicated in the quadruplet
        for (k in 0 until i){
            val currentSum = array[i] + array[k]
            if(!allPairSums.containsKey(currentSum)){
                // since the map can contains multiple pair of sum, in the list
                allPairSums[currentSum] = mutableListOf<MutableList<Int>>() //form a list
                allPairSums[currentSum]!!.add(mutableListOf<Int>(array[k], array[i])) //the not-null assertion operator (!!) converts any value to a non-null type and throws an exception if the value is null
            } else{
                allPairSums[currentSum]!!.add(mutableListOf<Int>(array[k], array[i]))
            }
        }
    }

    return quadruples
}

fun main(args: Array<String>){
    val array = mutableListOf<Int>(7, 6, 4, -1, 1, 2)
    val targetSum = 16
    print(fourNumberSum(array, targetSum))
}