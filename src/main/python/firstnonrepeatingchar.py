
# in broute force approach we can check for each char 
# with the remaining chars and to do that we need to use 2 for loop
# It will increase the time complexity

# to reduce time complexity we could generate a hashmap of character counts
# then check the count and if the count is 1 for the character then that would be the 
# first char of non repeating char and return the index of the char from the string
def firstNonRepeatingCharacter(string):

    charCount = {}

    for char in string:

        if char not in charCount:
            charCount[char] = 0
        charCount[char] += 1

    for i, char in enumerate(charCount):
        if charCount[char] == 1:
          return string.index(char)
        i+=1  

    return -1        



if __name__ == "__main__":

    string = "faadabcbbebdf"
    print(firstNonRepeatingCharacter(string))