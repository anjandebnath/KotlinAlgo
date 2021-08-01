# [2,3,1,-4,-4,2]
# a single cycle means that if we started any of these numbers and
# we start jumping through the array by following the values that we have in the array,
# we will sort of wrap back around to the first number that we started at,
# having visited every other element in the array exactly once.

# for the given array we can start at the first number which is 2,
# and we would move forward by two, and now we are at 1.
# Now we would move forward by one.So we are at -4.
# Now we would move forward by four and now we are at 2. (End)
# Now we would move forward by two and now we are at 3.
# Now we would move forward by three and now we are at -4.
# Now we would move forward by four and now we are at 2. (start point),
# So we always visited every element only once and back around to the first number, so it's a single cycle.

# Algorithm
# When we are jumping through the n elements, if at any point we've jumped through m elements,
# where m is less than n, and we are back at starting point, then we can say we have a cycle.
# Again, once we have jumped through n elements, but we are not at our starting point, then there is a cycle.

def hasSingleCycle(array):
    numElementsVisited = 0
    currentIdx = 0
    while numElementsVisited < len(array):
        # if we visited more than one element (pass the 1st element)
        # and yet we are at index 0, then we are dealing with multiple cycles
        if numElementsVisited > 0 and currentIdx == 0:
            return False
        numElementsVisited+= 1
        currentIdx = getNextIdx(currentIdx, array)

    return currentIdx == 0 # if we visited n elements and still the current idx is at starting index then it's true

def getNextIdx(currentIdx, array):
    # need to handle the edge cases
    # based on the current index, we need to find the next index
    # we want to see how many indices do we have to jump forward or backwards

    jump = array[currentIdx]
    # [26, 1, 2, 3, 4]
    # nextIdx = (0 + 26) % 5 == 1 (mod is used to handle the edge cases)
    nextIdx = (currentIdx + jump) % len(array)
    # Important: if nextIdx is negetive value, say -1 then in the array we will move towards the end of the array
    # [-26, 1, 2, 3, 4]
    # nextIdx = (0 - 26) % 5 == -1
    # -1 + (5) = 4
    return nextIdx if nextIdx >= 0 else nextIdx + len(array)

if __name__ == "__main__":

    array = [3, 5, 6, -5, -2, -5, -12, -2, -1, 2, -6, 1, 1, 2, -5, 2]
    print(hasSingleCycle(array))