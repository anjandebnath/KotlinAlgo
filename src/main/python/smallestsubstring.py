# "bigString": "abcd$ef$axb$c$",
#  "smallString": "$$abf"


# Sliding window technique:
# store the chararcters of the small string into a hashmap
# Count the total unique characters of the small string
# For big string perform sliding window technique to get the samllest substring
#     Start from index 0 as the left(L) and right(R) pointer.
#     Increase/traverse the R pointer until the count of small string chars and chars from big string are same.
#       Store the characters that mathches the small string to a hashmap. No need to store extra char that not in small string
#       Keep track of the current substring bound 
#     When the count is same then traverse the L pointer.
#         Eliminate the char from the big string hashmap 
#     Increase/traverse the R pointer until the count of small string chars and chars from big string are same.    

def smallestSubstringContaining(bigString, smallString):

    # store every character count of small string in a hashtable
    # if the same char remain multiple times in the string that would be also added
    charofSmallString = getCharCounts(smallString)
    # then try to find the substring
    # we need to keep track of the bound of the substring for each iteration,
    # so we don't have to store random substring
    substringBounds = getSubStringBounds(bigString, charofSmallString)
    
    return getStringFromBounds(bigString, substringBounds)

def getCharCounts(string):
    charCounts = {}   
    for char in string:
        # since the character count gets increasing and decreasing
        # on checking the char count in the hash table, so 2 diferent helper methods
        increaseCharCount(char, charCounts)
    return charCounts    

# main logic here
def getSubStringBounds(string, targerCharsofSmallString):

    # initailal bound will be 0 to infinity 
    # we will update it inside based on new bound
    subStringBounds = [0, float("inf")] 

    # mew hash table for substring character counts
    charCountBigStringforUniqueCharOnly = {}

    numUniqueCharsInSmallString = len(targerCharsofSmallString.keys())
    numUniqueCharsTraverse = 0

    # sliding will start from position 0
    leftIdx = 0
    rightIdx = 0

    # move the right idx to the right in the big string until you've counted
    # all of the target characters of small string for enough times
    while rightIdx < len(string):

        targetChar = string[rightIdx]
        if targetChar not in targerCharsofSmallString:
            rightIdx += 1
            continue
        
        # store target char count in the hash table for big string
        # it will help to track the substring bound
        increaseCharCount(targetChar, charCountBigStringforUniqueCharOnly)

        # if get the exact char count then increase the left pointer
        # in the targetchar hashmap th char might present more than once
        # so in the bigstring's hashmap that also needs to be exact same amount
        if charCountBigStringforUniqueCharOnly[targetChar] == targerCharsofSmallString[targetChar]:
            numUniqueCharsTraverse += 1


        # Move the leftIdx to the right in the string until you no longer
        # have enough of the target charachters in between the left idx and right idx.
        # Update the substring bounds accordingly 
        
        while numUniqueCharsTraverse == numUniqueCharsInSmallString and leftIdx <= rightIdx:

            # if leftidx and rightidx is less than the initial value then update with that bound
            subStringBounds = getCloserBounds(leftIdx, rightIdx, subStringBounds[0], subStringBounds[1])

            leftChar = string[leftIdx]
            if leftChar not in targerCharsofSmallString:
                leftIdx += 1
                continue
            # if found match then decrease the count
            if charCountBigStringforUniqueCharOnly[leftChar] == targerCharsofSmallString[leftChar]:
                numUniqueCharsTraverse -=1

            decreaseCharCount(leftChar, charCountBigStringforUniqueCharOnly)  

            leftIdx += 1

        rightIdx+=1 

    return subStringBounds  

def getCloserBounds (idx1, idx2, idx3, idx4):
    return [idx1, idx2] if idx2 - idx1 < idx4 - idx3 else [idx3, idx4]

def getStringFromBounds(string, bounds):
    start, end = bounds
    if end == float("inf"):
        return ""
    return string[start : end +1]    



def increaseCharCount(char, charCounts):
    if char not in charCounts:
        charCounts[char] = 0
    charCounts[char] += 1 

# it will be called when the left pointer is moving and eliminate the characters
def decreaseCharCount(char, charCounts):
    charCounts[char] -= 1       


if __name__ == "__main__":

    bigString =  "abcd$ef$axb$c$"
    smallString =  "$$abf"

    print(smallestSubstringContaining(bigString, smallString))
