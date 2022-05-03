package cz.stepanzalis.spacexlifts.io.base

import retrofit2.HttpException

/**
 * Parsing error body to an exception with error message
 */
open class LaunchErrorHandler {

    open fun handleError(exception: Exception): Pair<Exception, String?> {
        exception.printStackTrace()
        val errorBody = (exception as? HttpException)?.response()?.errorBody()?.string()
        return exception to errorBody
    }
}
