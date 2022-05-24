def sortedSquaredArray(array):
    # Write your code here.
    squaredShortedList = [0 for _ in array]
    smallerIdx = 0
    greaterIdx = len(array) -1 

    for idx in reversed(range(len(array))) :
        smallerValue = array[smallerIdx]
        greaterValue = array[greaterIdx]
        if abs(smallerValue) > abs(greaterValue):
            squaredShortedList[idx] = smallerValue * smallerValue
            smallerIdx += 1
        else:  
            squaredShortedList[idx] = greaterValue * greaterValue  
            greaterIdx -= 1


    return squaredShortedList


if __name__ == "__main__":
    mylist = [-4,-1,3,5,7]
    print(sortedSquaredArray(mylist))