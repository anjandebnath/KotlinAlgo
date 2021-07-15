# sorting the input array in to another array will cause time complexity O(nlogn)
# so try to find an algo which could cause O(n)

# if we don't want to sort the array for big time complexity then we need to find a solution
# where we can track the array elements very fast at the moments notice.
# it can be done to have a hash map and store all the values.
# so it can be accessed in a constant time.

# iterate through input array, and we can try to explore the range of each number
# by checking the if the smaller numbers and greater numbers of current number is in hash table.

# to reduce duplicate checking for each number and to prevent iterating through each number
# which are already iterated for other values we could point each value in hash table with boolean true.
# So intially all the numbers in the hashtable are good to iterate

def largestRange(array):
    bestRange = []
    longestLength = 0
    numsVisited = {} # python dictionary to store hashtable

    # intialize the hash table all value to true so it can be used all values
    for num in array:
        numsVisited[num] = True

    for num in array:
        if not numsVisited[num]: # if false already then nothing to da
            continue
        numsVisited[num] = False
        currentLength = 1 #since we take the current num in consider so current length is 1
        left = num -1
        right = num +1
        while left in numsVisited: # need to check how it happens in kotlin
            numsVisited[left] = False
            currentLength += 1
            left -= 1
        while right in numsVisited:
            numsVisited[right] = False
            currentLength += 1
            right+= 1

        if currentLength > longestLength:
            longestLength = currentLength
            bestRange = [left +1, right -1] # it's because when we are in while loop, we decrease and increase the value until the value is presented so for last exact value the left value get decreased one more extra time and right value increased one more extra time

    return bestRange

if __name__ == "__main__":
    array = [1, 11, 3, 0, 15, 5, 2, 4, 10, 7, 12, 6 ]
    print(largestRange(array))