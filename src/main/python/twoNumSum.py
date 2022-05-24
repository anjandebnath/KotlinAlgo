##


def twoNumberSum(array, output):
    array.sort()
    length = len(array)
    start = 0
    end = length-1 # python list end value indexing always -1

    while start < end:
        if array[start] + array[end] > output:
            end-= 1 # since sorted array so definitely previous value will be less
        elif array[start] + array[end] < output:
            start += 1 # since sorted array so definitely next value will be more
        elif array[start] + array[end] == output:
           return [array[start] , array[end]]


if __name__ == "__main__":
    array = [3, 8, 1, 4, -3, 9]
    output = 10
    print(twoNumberSum(array, output))

#Takeaway:
#1. For comparison type tasks while loop should be used.
#2. limit should be array length and

