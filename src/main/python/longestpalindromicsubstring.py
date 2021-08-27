# abaxyzzyxf
# xyzzyx

# Check if a substring of the given string is a palindrome
# It can be done by taking a char where each individual char is a palindrome
# Now move 1 char back and 1 char forward of the current char and check if 
#    the 2 chars are equal to each other, if yes then it's a palindrome
#    if not then check whether the current char and the previous char are equal, if yes then it's a palindrome
# Store the length of the palindrome

# 
# normal function in python:
# def function(arguments):
#     expression
#lambda function in python:  
# lambda argument(s): expression
def longestPalindromicSubstring(string):

    currentLongest = [0,1]

    for i in range(1, len(string)):
        odd = getLongestPalindrome(string, i-1, i+1)
        even = getLongestPalindrome(string, i-1, i)

        # to compare items by their integer value use key with simple lambda
        # key = function
        # odd[1] - odd[0] > even[1] - even[0] : odd : even

        #longest = max(odd, even, key=lambda x: x[1] - x[0])
        longest = odd if odd[1] - odd[0] > even[1] - even[0] else even
        currentLongest = max(longest, currentLongest, key=lambda x: x[1] - x[0])

    return string[currentLongest[0]: currentLongest[1]]

def findMax(list1, list2):
    list3 = [max(value) for value in zip(list1, list2)]
    return list3

def getLongestPalindrome(string, leftIdx, rightIdx):

    # check the edge case
    while leftIdx>= 0 and rightIdx < len(string):
        if string[leftIdx] != string[rightIdx]:
            break

        leftIdx -= 1
        rightIdx += 1

    return [leftIdx+1, rightIdx]


if __name__ == "__main__":

    #string = "abaxyzzyxf"
    #string = "a"
    string = "noon high it is"
    print(longestPalindromicSubstring(string))



