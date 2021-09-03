# hint1: you will need to determine not only the unique characters required to form the input words, but also their frequencies. 
#        What determines the frequencies or repeatation of the characters to form multiple words?
# hint2: The word that contains the highest frequency of any character dictates how many of those characteres are required
# hint3: Use a hash table to keep track of the maximum frequencies of all unique characters.

# calculate the frequency of each charactars of each word
# and compare the current frequency with the max frequency and 
# if current frequency is greater than max then update the max with current

def minimumCharactersForWords(wordsList):
    # write the code
    maxCharFrequency = {}

    for word in wordsList:
        
        countCharFrequencyofEachWord = countFrequency(word)
        updateMaxFrequencyofEachWord(countCharFrequencyofEachWord, maxCharFrequency)
        
    listofFrequencies = makeArrayofFrequencies(maxCharFrequency)

    return listofFrequencies

def updateMaxFrequencyofEachWord(countCharFrequencyofEachWord, maxCharFrequency):

    for chararcter in countCharFrequencyofEachWord:
        frequency = countCharFrequencyofEachWord[chararcter]

        if chararcter in maxCharFrequency:
            maxCharFrequency[chararcter] = max(frequency, maxCharFrequency[chararcter])
        else:
            maxCharFrequency[chararcter] = frequency    
 
    
def countFrequency(word):
    charFrequecny = {}
    
    for char in word:
        if char not in charFrequecny:
            charFrequecny[char] = 0
        
        charFrequecny[char] +=1

    return charFrequecny    

def makeArrayofFrequencies(maxFrequency):

    list = []
    for char in maxFrequency:
        frequency = maxFrequency[char]

        for _ in range(frequency):
           list.append(char)

    return list    
 


if __name__ == "__main__":
    # wordsList = ["this", "that", "did", "deed", "them!", "a"] 
    wordsList = [] 
    print(minimumCharactersForWords(wordsList))
