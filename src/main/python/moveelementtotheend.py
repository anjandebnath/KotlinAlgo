def moveElementToEnd(array, toMove):
    # Write your code here.

    startPoint = 0
    endPoint = len(array) -1

    while startPoint < endPoint:

        while endPoint>=0 and array[endPoint] == toMove:
            endPoint -= 1
        if startPoint < endPoint and array[startPoint] == toMove:
            array[startPoint], array[endPoint]  = array[endPoint], array[startPoint]

        startPoint += 1

    return array

if __name__ == "__main__":

    array = [3, 3, 3, 3, 3]
    toMove = 3
    print(moveElementToEnd(array, toMove))