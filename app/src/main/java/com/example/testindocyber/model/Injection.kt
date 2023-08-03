package com.example.testindocyber.model

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.testindocyber.util.Constant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object Injection {

    @Provides
    @Singleton
    fun providesClient(@ApplicationContext context: Context): Retrofit{
        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder().apply {
            addInterceptor(
                Interceptor { chain ->
                    val builder = chain.request().newBuilder()
                    return@Interceptor chain.proceed(builder.build())
                }
            )
            addInterceptor(loggingInterceptor)
            addInterceptor(ChuckerInterceptor.Builder(context)
                .collector(ChuckerCollector(context))
                .maxContentLength(25000L)
                .redactHeaders(emptySet())
                .alwaysReadResponseBody(false)
                .build())
        }.build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun providesApiServices(retrofit: Retrofit): ApiServices =
        retrofit.create(ApiServices::class.java)
}