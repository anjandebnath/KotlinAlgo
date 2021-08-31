# reorder the letters of each words alphabetically 
# sort the words inside the strings
# now dump them in a hash table and easily all the anagrams have bucketed together
# the sorted word would be the key in the hashmap
# if the key is already present then append

def groupAnagrams(words):
    anagrams = {}

    for word in words:
        sortedWord = "".join(sorted(word))
        if sortedWord in anagrams:
            anagrams[sortedWord].append(word)
        else:
            anagrams[sortedWord] = [word] # since we need to separate the words so create list

    return list(anagrams.values())   


if __name__ == "__main__":
        
    words = ["yo", "act", "flop", "tac", "foo", "cat", "oy", "olfp"]
    print(groupAnagrams(words))       
