package com.marcecuevas.hotelsapp.utils

import com.marcecuevas.hotelsapp.data.model.Result
import kotlinx.coroutines.*
import java.io.IOException

fun<T> lazyDeferred(block: suspend CoroutineScope.() -> T): Lazy<Deferred<T>> {
    return lazy {
        GlobalScope.async(start = CoroutineStart.LAZY) {
            block.invoke(this)
        }
    }
}

suspend fun <T : Any> safeApiCall(call: suspend () -> Result<T>, errorMessage: String): Result<T> = try {
    call.invoke()
} catch (e: Exception) {
    Result.Error(IOException(errorMessage, e))
}

val <T> T.exhaustive: T get() = this