package com.marcecuevas.hotelsapp.utils

import com.marcecuevas.hotelsapp.data.model.MResult
import kotlinx.coroutines.*
import java.io.IOException

fun<T> lazyDeferred(block: suspend CoroutineScope.() -> T): Lazy<Deferred<T>> {
    return lazy {
        GlobalScope.async(start = CoroutineStart.LAZY) {
            block.invoke(this)
        }
    }
}

suspend fun <T : Any> safeApiCall(call: suspend () -> MResult<T>, errorMessage: String): MResult<T> = try {
    call.invoke()
} catch (e: Exception) {
    MResult.Error(IOException(errorMessage, e))
}

val <T> T.exhaustive: T get() = this