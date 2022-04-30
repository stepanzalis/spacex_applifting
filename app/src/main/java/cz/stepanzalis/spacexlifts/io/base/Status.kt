package cz.stepanzalis.spacexlifts.io.base

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.HttpException

typealias Loading = Status.Loading
typealias Success = Status.Success
typealias Failure = Status.Failure
typealias FailureRetry = Status.FailureRetry

sealed class Status {

    object Loading : Status()

    class Success(val any: Any? = null) : Status()

    open class Failure(val exception: Exception? = null, val errorBody: String? = null) : Status() {
        inline fun <reified T> parseErrorBody(): T? {
            return fromJson<T>(
                errorBody ?: (exception as? HttpException)?.response()?.errorBody()?.string()
            )
        }
    }

    class FailureRetry(
        exception: Exception? = null,
        errorBody: String? = null,
        val retryRequest: () -> Unit
    ) : Failure(exception, errorBody)

}


inline fun <reified T> fromJson(string: String?): T? {
    val adapter = Moshi.Builder().add(KotlinJsonAdapterFactory()).build().adapter(T::class.java)
    return string?.let { adapter.fromJson(string) }
}
