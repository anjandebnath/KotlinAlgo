
# time O(m *(m+n)), space O(1)
# "characters": "Bste!hetsi ogEAxpelrt x ",
# "document": "AlgoExpert is the Best!"
'''
def generateDocument(characters, document):

    # calculate the frequency of each char in both characters and document list
    for character in document: # first O (m) from this for loop
        documentFrequency = countCharacterFrequency(character, document)  # this is another for loop O(m)
        characterFrequency = countCharacterFrequency(character, characters) O(n)

        if documentFrequency > characterFrequency:
            return False

    return True  

def countCharacterFrequency(character, targetString):
    frequency = 0
    for char in targetString:
            if char == character:
                frequency+= 1

    return frequency   
'''


# O(n+m) time | O(c) space
# Since the characters are write in a map so the space complexity increased

# when in broute force concept we need to consider 2 for loop simultinaously 
# due to the fact of 2 individual list then 
# time complexity will be O(n*n) which is worst

# To reduce time complexity it is a good practice to keep a hashmap 
# first keep the characters count in the hashmap
# if character from document remain in hasmap then decrease the count from the charactercount
# if any of the character is not in the charactercount that means it's not possible to create the document
# or previously the charactar count was greater than 0 but now become 0 so the character limit is not enough

def generateDocument(characters, document):

    characterCounts = {}

    for character in characters:
        if character not in characterCounts:
            characterCounts[character] = 0

        characterCounts[character]   +=1   

    for character in document:
        if character not in characterCounts  or characterCounts[character] == 0:
            return False

        characterCounts[character] -= 1

    return True    

if __name__ == "__main__" :

   characters = " Bste!hetsi ogEAxpelrt x "
   document = " AlgoExpert is the Best!"
   print(generateDocument(characters, document))




