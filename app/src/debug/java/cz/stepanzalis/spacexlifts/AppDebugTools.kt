package cz.stepanzalis.spacexlifts

import android.app.Application
import android.content.Context
import cz.stepanzalis.spacexlifts.io.base.Failure
import okhttp3.OkHttpClient
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.logging.HttpLoggingInterceptor

object AppDebugToolsConfig {

    /**
     * Init debug tools
     */
    fun initDebugTools(app: Application) {
        //TODO: Bugsee or whatever
    }

    fun logFailure(failure: Failure?) {
        failure?.exception?.printStackTrace()
    }
}

fun OkHttpClient.Builder.addDebugInterceptors(context: Context): OkHttpClient.Builder {
    addNetworkInterceptor(ChuckerInterceptor.Builder(context).build())
    addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    return this
}