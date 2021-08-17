# pre compute the nearest buildings of the requirement and find out the closest distance
# It will reduce the iteration through the array, because on previous solution the time complexity was O(B^B*r)
# when there are lots of list and we need to iterate through the array multiple times then this pre compute is good technique
# Imagine we have the requirement of Gym, school, and store
# Then imagine for the gym we already knew for index 0 the closest gym was one block away.
# for index 1 the closest gym was 0 blocks away, for index 2, 0 blocks away

# to precompute the list for each requirement
# we can do one pass through the blocks from left to right and keep track the closest requirement (gym, store)
# that came before me and again pass through the blocks from right to left.


def apartmentHunting(blocks, reqs):

    #precompute all the requirements (nearest gym, store, school) at every single block
    # min distances from all the blocks is a map of the requirements, where for each requirement
    # we get all of the minimum distances of that requirement from every single block

    # def a_name(x):
    #     return x+x

    # lambda x: x+x

    # Lambda functions can be used along with built-in functions like filter(), map() and reduce().
    # map(fun, iter)
    # map() function returns a map object (which is an iterator) of the results after applying the given function to each item of a given iterable
    minDistancesFromBlocks = list(map(lambda req: getMinDistancesForEachReq(blocks, req), reqs))
    print("min distances for each req from each block: \r")
    print(minDistancesFromBlocks)
    maxDistancesAtBlocks = getMaxDistancesAtBlocks(blocks, minDistancesFromBlocks)
    print("max distance req at each block: \r")
    print(maxDistancesAtBlocks)
    return getIdxAtMinValue(maxDistancesAtBlocks)

# pre compute all the values from passing through left to right and right to left
def getMinDistancesForEachReq(blocks, req):
    minDistances = [0 for _ in blocks] #  the for loop gives '0' output for blocks size
    closestReqIdx = float("inf") # find the closest index of the current requirement is, for the given block that we are at.
    for i in range(len(blocks)): # iterate from left to right
        # check if value is True
        if blocks[i][req]: # check if current requirement is present at every block
            closestReqIdx = i # if it is present then that will be the closest requirement in this direction

        # [gym, gym, store, school, gym]
        # min distance array for gym left to right [0,0,1,2,0]

        # min distance for 0 index is infinity
        # Distance calculaation cn be done through abs value calculation
        minDistances[i] = distanceBetween(i,closestReqIdx)
    for i in reversed(range(len(blocks))):
        if blocks[i][req]:
            closestReqIdx = i
        # tricky part
        # sample: [gym, gym, store, school, gym]
        # min distance array for gym right to Left [0,0,1,1,0]
        minDistances[i] = min(minDistances[i], distanceBetween(i,closestReqIdx))

        # min distance array for gym    [0,0,1,1,0]
        # min distance array for school [2,3,1,0,0]
        # min distance array for store [0,4,1,1,0]

    return minDistances

# after getting the min distance individual list of each req, calculate which req is at the max distance from the current block
# find which req has the maximum distance from the distances list
def getMaxDistancesAtBlocks(blocks, minDistancesFromBlocks):
    maxDistanceReqAtBlocks = [0 for _ in blocks]

    for i in range(len(blocks)):
        minDistancesOfReqAtaGivenBlock = list(map(lambda distances: distances[i], minDistancesFromBlocks))
        print("min distance of each req at each block: \r")
        print(minDistancesOfReqAtaGivenBlock)
        # min distance of gym,school,store for block 0 -> [0,2,0] and get the max value which is 2
        maxDistanceReqAtBlocks[i] =max(minDistancesOfReqAtaGivenBlock)
    return maxDistanceReqAtBlocks

def getIdxAtMinValue(array):
    idxAtMinValue = 0
    minValue = float("inf")
    for i in range(len(array)):
        currentValue = array[i]
        if currentValue < minValue:
            minValue = currentValue
            idxAtMinValue = i
    return idxAtMinValue

def distanceBetween(a,b):
    return abs(a-b)

if __name__ == "__main__":
    blocks = [
        {
           "gym": False,
           "school": True,
           "store": False
        },
        {
            "gym": True,
            "school": False,
            "store": False
        },
        {
            "gym": True,
            "school": True,
            "store": False

        },
        {
            "gym": False,
            "school": True,
            "store": False

        },
        {
            "gym": False,
            "school": True,
            "store": True

        }
    ]
    reqs = ["gym", "school", "store"]
    print(apartmentHunting(blocks, reqs))
