package cz.stepanzalis.spacexlifts.io.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseVM : ViewModel() {

    val defaultStatus = MutableStateFlow<Status>(Init)

    /**
    Flag indicates if request can be retried
    If true, all necessary info is passed down to be able to repeat the request
     */
    open val defaultRetryRequest = true

    protected open val launchErrorHandler = LaunchErrorHandler()

    fun <ResponseType> launch(
        retry: Boolean = defaultRetryRequest,
        block: suspend CoroutineScope.() -> ResponseType
    ) = launch(retry, block, defaultStatus)

    fun <ResponseType> launch(
        retry: Boolean = defaultRetryRequest,
        block: suspend CoroutineScope.() -> ResponseType,
        status: MutableStateFlow<Status>? = defaultStatus
    ) = launch(retry, block, null, status)

    fun <ResponseType> launch(
        retry: Boolean = defaultRetryRequest,
        block: suspend CoroutineScope.() -> ResponseType,
        onError: ((e: Exception, errorBody: String?) -> Unit)? = null,
        status: MutableStateFlow<Status>? = defaultStatus
    ) = viewModelScope.launch(
        context = CoroutineExceptionHandler { _, throwable ->
            when (throwable) {
                is Exception -> {
                    val (exception, body) = launchErrorHandler.handleError(throwable)
                    status?.value = (
                            if (retry.not()) Failure(exception, body)
                            else FailureRetry(
                                exception = exception,
                                errorBody = body,
                                retryRequest = buildRepeatAction(retry, block, onError, status)
                            )
                            )
                    onError?.invoke(exception, body)
                }
            }
        } + Dispatchers.IO,
        block = {
            status?.emit(Loading)
            val result = block.invoke(this)
            status?.emit(Success(result))
        },
    )

    private fun <ResponseType> buildRepeatAction(
        retry: Boolean,
        block: suspend CoroutineScope.() -> ResponseType,
        onError: ((e: Exception, errorBody: String?) -> Unit)?,
        status: MutableStateFlow<Status>?
    ): () -> Unit = { launch(retry, block, onError, status) }

    abstract fun dismissErrorDialog()

}
