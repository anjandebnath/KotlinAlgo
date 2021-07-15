# array = [1,2,4,7,10,11,7,12,6,7,16,18,19]
#the sub array that we want to sort is dependent on the final positions
# of the min and max unsorted numbers(out of order numbers)
# check from the starting position that if the current number is sorted or not
# if one number is not sorted then it is true that other num beside it is also unsorted
# So, find those all unsorted numbers
# Now, from the unsorted numbers find the min and max value that are out of order
# what is the final position of them in the final sorted array

def subArraySort(array):
    # find all unsorted numbers and find the smallest and largest among them
    minOutoforder = float("inf")
    maxOutofOrder = float("-inf")

    for i in range(len(array)):
        num = array[i]
        if isOutofOrder(i, num, array): # if the current num is out of order then we update the min and max out of order number
            minOutoforder = min(minOutoforder, num)
            maxOutofOrder = max(maxOutofOrder, num)

    if minOutoforder == float("inf"): # edge case
        return[-1, -1]

    # find the final position of the unsorted min and max value
    subarrayLeftIdx = 0
    while minOutoforder >= array[subarrayLeftIdx]:
        subarrayLeftIdx+= 1
    subarrayRightIdx = len(array) -1
    while maxOutofOrder <= array[subarrayRightIdx]:
        subarrayRightIdx-=1
    return [subarrayLeftIdx, subarrayRightIdx]

def isOutofOrder(i, num, array):
    if i==0:
        return num > array[i+1] # when current value is greater than next value then it will be out of order
    if i==len(array)-1:
        return num < array[i-1]
    return num > array[i+1] or num < array[i-1] # if it is abnormal then true

if __name__ == "__main__":
    array = [1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19]
    print(subArraySort(array))
