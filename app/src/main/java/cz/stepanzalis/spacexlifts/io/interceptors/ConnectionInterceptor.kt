package cz.stepanzalis.spacexlifts.io.interceptors

import android.content.Context
import cz.stepanzalis.spacexlifts.io.errors.NoInternetException
import cz.stepanzalis.spacexlifts.io.utils.ext.hasInternetAccess
import okhttp3.Interceptor
import okhttp3.Response

class ConnectionInterceptor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response =
        if (context.hasInternetAccess().not()) throw NoInternetException()
        else chain.proceed(chain.request())
}