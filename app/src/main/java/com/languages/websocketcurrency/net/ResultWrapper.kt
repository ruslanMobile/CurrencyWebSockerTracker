package com.languages.websocketcurrency.net

import retrofit2.HttpException
import java.lang.Exception

sealed class ResultWrapper<out T> {
    data class Success<T>(val data: T) : ResultWrapper<T>()
    data class HttpError<T>(val e: HttpException, val code: Int) : ResultWrapper<T>()
    data class GenericError(val e: Exception) : ResultWrapper<Nothing>()
    data class HttpErrorWithBody<T>(val data: T?) : ResultWrapper<T>()
    object NetworkError : ResultWrapper<Nothing>()
}