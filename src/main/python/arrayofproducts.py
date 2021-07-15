# [5,1,4,2], Rev: [2,4,1,5] to reduce the same products tasks again and again
# Calculate the product of left numbers and right numbers
# for 5 the left product is 1 and right product is 1*4*2 = 8
# for 1 the left prduct is 1*5 = 5 and right product is 4*2 = 8

def arrayOfProducts(array):

    product = [1 for _ in range(len(array))] # products array that is our desired output
    leftProducts = [1 for _ in range(len(array))]
    rightProducts = [1 for _ in range(len(array))]

    currentLeftProduct = 1
    for i in range(len(array)):
        leftProducts[i] = currentLeftProduct
        currentLeftProduct*= array[i]

    currentRightProduct = 1
    for i in reversed(range(len(array))):
        rightProducts[i] = currentRightProduct
        currentRightProduct*= array[i]

    for i in (range(len(array))):
        product[i] = rightProducts[i] * leftProducts[i]

    return product


if __name__ == "__main__":
    array = [5, 1, 4, 2]
    print(arrayOfProducts(array))