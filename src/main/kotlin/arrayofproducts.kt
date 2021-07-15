fun arrayOfProducts(array: List<Int>): List<Int>{
    val products = MutableList(array.size) {1}  // val is like final variable in java
    val leftProducts = MutableList(array.size) {1} // when the value is not assigned multiple times
    val rightProducts = MutableList(array.size) {1}

    var leftRunningProduct = 1
    for (i in 0 until array.size){
        leftProducts[i] = leftRunningProduct
        leftRunningProduct *= array[i]
    }

    var rightRunningProduct =1 // value can be assigned multiple times
    for (i in array.size -1 downTo 0){
        rightProducts[i] = rightRunningProduct
        rightRunningProduct *= array[i] // when the variable value needs to be get and assign to any arithmetic operation
    }

    for (i in 0 until array.size){
        products[i] = leftProducts[i] * rightProducts[i]
    }

    return products
}

fun main(args: Array<String>){
    val array = mutableListOf<Int>(5, 1, 4, 2)
    print(arrayOfProducts(array))
}