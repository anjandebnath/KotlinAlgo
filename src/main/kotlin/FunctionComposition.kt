

fun main(){
    // OOP style
    val calculator = Calculator()
    val result = calculator.multiply(paramX = 5f, paramY = 4f)
    println("The result is : $result")

    // the new style (Composition Style)
    //val multiplicationResult : Float = multiplication(paramX = 5f, paramY = 4f)

    // the variable operation is of type function that does something and returns a Float.
    // on the right side we describe what this function actually does
    //val operation: ()-> Float = {multiplication(paramX = 5f, paramY = 4f)}
    //prettyPrinter(operationAsParam = operation)

    //prettyPrinter(operationAsParam = {multiplication(paramX = 5f, paramY = 4f)})

    //prettyPrinter({multiplication(paramX = 5f, paramY = 4f)})

    // when we have a higher-order function which has only one parameter passed as an
    // anonymous function expression
    // this is called function composition
    // with Trailing Lambda
    PrettyPrinter(message = "The result is :") {
        Multiplication(paramX = 5f, paramY = 4f)
    }
}

// Kotlin Top-Level function
fun Multiplication(paramX: Float, paramY: Float) : Float{
    return paramX * paramY
}

fun testHighOrder(highOrderParam: ()->Unit){


}


// High-Order function : it accepts a function as a parameter
/*fun prettyPrinter(operationAsParam: () -> Float){
    println("The result is : ${operationAsParam()}")
}*/

// High-Order function : it accepts a function as a parameter
fun PrettyPrinter(message: String, operationAsParam: () -> Float){
    println("$message ${operationAsParam()}")
}

class Calculator{

    // Class level function
    fun multiply(paramX: Float, paramY: Float) : Float{

        return paramX * paramY
    }

    fun add(paramX: Float, paramY: Float): Float{
        return paramX + paramY
    }
}
