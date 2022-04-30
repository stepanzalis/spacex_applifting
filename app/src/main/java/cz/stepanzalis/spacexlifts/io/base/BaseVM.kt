package cz.stepanzalis.spacexlifts.io.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseVM : ViewModel() {

    val defaultStatus = SingleLiveEvent<Status>()

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
        status: MutableLiveData<Status>? = defaultStatus
    ) = launch(retry, block, null, status)

    fun <ResponseType> launch(
        retry: Boolean = defaultRetryRequest,
        block: suspend CoroutineScope.() -> ResponseType,
        onError: ((e: Exception, errorBody: String?) -> Unit)? = null,
        status: MutableLiveData<Status>? = defaultStatus
    ) = viewModelScope.launch(
        context = CoroutineExceptionHandler { _, throwable ->
            when (throwable) {
                is Exception -> {
                    val (exception, body) = launchErrorHandler.handleError(throwable)
                    status?.postValue(
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
        },
        block = {
            status?.postValue(Loading)
            val result = block.invoke(this)
            status?.postValue(Success(result))
        },
    )

    private fun <ResponseType> buildRepeatAction(
        retry: Boolean,
        block: suspend CoroutineScope.() -> ResponseType,
        onError: ((e: Exception, errorBody: String?) -> Unit)?,
        status: MutableLiveData<Status>?
    ): () -> Unit = { launch(retry, block, onError, status) }

}
