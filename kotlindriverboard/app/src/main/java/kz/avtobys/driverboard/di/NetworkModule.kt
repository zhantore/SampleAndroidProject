package kz.avtobys.driverboard.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kz.avtobys.driverboard.BuildConfig
import kz.avtobys.driverboard.auth.data.network.interceptor.OAuthTokenInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val CONNECTION_TIMEOUT = 60_000L
private const val READ_TIMEOUT = 60_000L
private const val MAX_CONTENT_LENGTH = 250_000L

val networkModule = module {
    single {
        createRetrofit(
            baseUrl = BuildConfig.OAUTH_API_BASE_URL,
            okHttpClient = createOkHttpClient(
                context = androidContext(),
                authInterceptor = get(),
            ),
            gson = get(),
        )
    }
    single {
        GsonBuilder().setLenient().create()
    }
    single {
        OAuthTokenInterceptor(
            securityLocalCache = get(),
            gson = get(),
        )
    }
}

// Retrofit builder
fun createRetrofit(
    baseUrl: String,
    okHttpClient: OkHttpClient,
    gson: Gson,
): Retrofit = Retrofit.Builder()
    .baseUrl(baseUrl)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create(gson))
    .build()

// OkHttp builder
fun createOkHttpClient(
    context: Context,
    authInterceptor: OAuthTokenInterceptor? = null,
    block: OkHttpClient.Builder.() -> Unit = {},
): OkHttpClient {
    return OkHttpClient.Builder().apply {
        authInterceptor?.let { addInterceptor(it) }
        logInterceptors(context) {
            connectTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
            readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
            block()
        }
    }.build()
}

// OkHttpClient logger
private fun OkHttpClient.Builder.logInterceptors(
    context: Context,
    block: OkHttpClient.Builder.() -> Unit,
): OkHttpClient.Builder {
    val isDebug = BuildConfig.DEBUG
    if (isDebug) {
        val chuckerCollector = ChuckerCollector(
            context = context,
            showNotification = true,
            retentionPeriod = RetentionManager.Period.ONE_HOUR,
        )
        val chuckerInterceptor = ChuckerInterceptor.Builder(context)
            .collector(chuckerCollector)
            .maxContentLength(MAX_CONTENT_LENGTH)
            .alwaysReadResponseBody(true)
            .build()
        addInterceptor(chuckerInterceptor)
    }
    block()
    if (isDebug) {
        addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    }
    return this
}

