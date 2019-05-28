package com.marcecuevas.hotelsapp.data.model

import java.lang.Exception

sealed class MResult<out T: Any> {

    data class Success<out T: Any>(val data: T): MResult<T>()
    data class Error(val message: String): MResult<Nothing>()
}