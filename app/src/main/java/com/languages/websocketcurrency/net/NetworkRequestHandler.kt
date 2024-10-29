package com.languages.websocketcurrency.net

import com.languages.websocketcurrency.utils.messageOrDefault
import retrofit2.HttpException
import timber.log.Timber
import java.lang.Exception
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object NetworkRequestHandler {

    private val onErrorDefault = { e: Exception ->
        Timber.e(e.messageOrDefault())
    }

    suspend fun <T> doRequest(
        request: suspend () -> T?,
        onError: (e: Exception) -> Any = onErrorDefault
    ): ResultWrapper<T> {
        var result: T? = null
        return try {
            result = request()
            ResultWrapper.Success<T>(result!!)
        } catch (e: Exception) {
            onError(e)
            e.printStackTrace()
            when (e) {
                is UnknownHostException, is SocketTimeoutException -> ResultWrapper.NetworkError
                is HttpException -> ResultWrapper.HttpError(e, e.code())
                else -> {
                    Timber.e("OTHER ERROR"); ResultWrapper.GenericError(e)
                }
            }
        }
    }
}