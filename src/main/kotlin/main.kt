import kotlinx.coroutines.*
import java.security.InvalidParameterException
//my code is fire
fun main(args: Array<String>) {
    //never use run blocking
    runBlocking {
        val scope = CoroutineScope(Dispatchers.Default)
        val deferred = scope.async {
            throwExAsync("I'm exceptional", true)
        }
        try {
            val result = deferred.await()
            println(result)
        } catch(exception: InvalidParameterException) {
            println("Caught: ${exception.message}")
        }
    }
}

suspend fun throwExLaunch(message: String, throwIt: Boolean = false) {
    delay(500)
    if (throwIt) {
        throw InvalidParameterException("I'm an exception")
    } else {
        println(message)
    }
}

suspend fun throwExAsync(message: String, throwIt: Boolean = false): String {
    delay(500)
    if (throwIt) {
        throw InvalidParameterException("I'm an exception")
    } else {
        return message
    }
}