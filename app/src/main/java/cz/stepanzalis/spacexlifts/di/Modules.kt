package cz.stepanzalis.spacexlifts.di

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import cz.stepanzalis.spacexlifts.BuildConfig
import cz.stepanzalis.spacexlifts.addDebugInterceptors
import cz.stepanzalis.spacexlifts.io.db.SpaceXDatabase
import cz.stepanzalis.spacexlifts.io.interceptors.ConnectionInterceptor
import cz.stepanzalis.spacexlifts.io.repositories.SpaceXRepo
import cz.stepanzalis.spacexlifts.io.services.ApiConfig
import cz.stepanzalis.spacexlifts.io.services.SpaceXApiService
import cz.stepanzalis.spacexlifts.ui.feature.company.CompanyVM
import cz.stepanzalis.spacexlifts.ui.feature.launches.LaunchesVM
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val dataModule = module {
    api()
    repo()
    db()
}

val uiModule = module {
    viewModels()
}

val appModules = listOf(dataModule, uiModule)

private fun Module.viewModels() {
    viewModel { LaunchesVM(get()) }
    viewModel { CompanyVM(androidApplication(), get()) }
}

private fun Module.api() {
    single { createMoshi() }
    single { createOkHttpClient(androidApplication()) }

    single {
        createRetrofit(
            okHttpClient = get(),
            moshi = get(),
        )
    }
    single { get<Retrofit>().create(SpaceXApiService::class.java) }
}

private fun Module.repo() {
    single { SpaceXRepo(get(), get(), get()) }
}

private fun Module.db() {
    single {
        Room.databaseBuilder(
            androidApplication(),
            SpaceXDatabase::class.java,
            SpaceXDatabase.Name
        ).build()
    }

    single { get<SpaceXDatabase>().rocketLaunchDao }
    single { get<SpaceXDatabase>().rocketDao }
}

private fun createMoshi(): Moshi {
    return Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}

private fun createOkHttpClient(context: Context): OkHttpClient {
    return OkHttpClient.Builder().apply {
        connectTimeout(ApiConfig.Timeout, TimeUnit.SECONDS)
        readTimeout(ApiConfig.Timeout, TimeUnit.SECONDS)
        writeTimeout(ApiConfig.Timeout, TimeUnit.SECONDS)
        callTimeout(ApiConfig.Timeout, TimeUnit.SECONDS)
        addInterceptor(ConnectionInterceptor(context))
        addDebugInterceptors(context)
    }.build()
}

private fun createRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
    return Retrofit.Builder().apply {
        baseUrl(BuildConfig.API_BASE_URL)
        client(okHttpClient)
        addConverterFactory(MoshiConverterFactory.create(moshi))
    }.build()
}