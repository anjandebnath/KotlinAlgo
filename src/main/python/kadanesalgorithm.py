# Kadane's Algorithm is used to solve the Maximum Subarray problem
# dynamic programming approach has been used to resolve the problem
# Here is the algortihm: maxEndingHere = max of (maxEndingHere+current integer, current integer)


def kadanesAlgorithm(array):
    maxEndingHere = array[0]
    maxSoFar = array[0]
    for i in range(1, len(array)):
        num = array[i]
        maxEndingHere = max(num, maxEndingHere + num)
        maxSoFar = max(maxSoFar, maxEndingHere)

    return maxSoFar

if __name__ == "__main__":

    array = [3, 5, -9, 1, 3, -2, 3, 4, 7, 2, -9, 6, 3, 1, -5, 4]
    print(kadanesAlgorithm(array))

