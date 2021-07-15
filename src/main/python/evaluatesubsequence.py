# The sequence will be the same order but not mandatory that it will be adjacent.
# Since 2 array is given so need to use 2 array to compare to search the desired sequences.
# Array search always have the time complexity of O(N) for N elements.

def isValidSubsequence(array, sequence):
    arrayLen = len(array)
    sequenceLen = len(sequence)
    arrIdx = 0
    sqIdx = 0

    while sqIdx < sequenceLen and arrIdx < arrayLen:
        if sequence[sqIdx] == array[arrIdx]:
            sqIdx+= 1
        arrIdx+= 1
    return sqIdx == sequenceLen # if both are equal then return 1(True), otherwise return 0(False)


if __name__ == "__main__":
    array = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    sequence = [2, 4, 12, 8, 10]
    print(isValidSubsequence(array, sequence))

