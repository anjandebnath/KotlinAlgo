# An array is said to be monotonic in nature if it is either continuously increasing or continuously decreasing.
# there can be  plateaus (same number) in the array
# either pairs of number are trending downwards or upwards or flat.
# this flat trait is important for monotonic array
# if either upwards trending is true or downwards trending true  or
# both when it's flat( both upward and downwards trending will be true for flat)
# solve with linear complexity

# find the direction that the array might be going in.
# is the array may be going up or going down
# by looking up the first two integers. then the third one..
# if on any pint the array breaks the direction. but the monotonic array can have same value in the adjacent position (pair of numbers).
# So the direction matching will be flat in that position.

# if the pairs of num is trending upwards or trending downwards.
# make both checks

# eliminate the case where we have an array of length either one or two

def isMonotonic(array):

    if len(array)<= 2:
        return True

    currentTrend = array[1] - array[0]

    for i in range(2, len(array)):
        if currentTrend == 0: #flat trend
            currentTrend = array[i] - array[i-1]
            continue

        breakTrend = breaksTrend(currentTrend, array[i], array[i-1])  # if direction breaks then return false
        if breakTrend: # if breakTrend then it's not monotonic
            return False

    return True


def breaksTrend(trend, currentInt, previousInt):
    difference = currentInt - previousInt
    if trend > 0: # upward trend
        if difference < 0:
            return True
        else:
            return False
    elif trend < 0: #downward trend
        return difference > 0 # simple way to manage the same logic of upper




if __name__ == "__main__":
    array = [1, 1, 2, 3, 4, 5, 5, 5, 6, 7, 8, 8, 9, 10, 11]
    print(isMonotonic(array))
