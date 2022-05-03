package cz.stepanzalis.spacexlifts

import android.app.Application
import android.content.Context
import cz.stepanzalis.spacexlifts.io.base.Failure
import okhttp3.OkHttpClient
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.logging.HttpLoggingInterceptor

/**
 * In release, all logs and debug tools are disabled
 */
object AppDebugToolsConfig {

    /**
     * Init debug tools
     */
    fun initDebugTools(app: Application) = Unit

    fun logFailure(failure: Failure?) = Unit
}

fun OkHttpClient.Builder.addDebugInterceptors(context: Context) = this