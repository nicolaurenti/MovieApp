package com.example.di

import com.example.data.service.MoviesApi
import com.example.util.Constant.API_NAME
import com.example.util.Constant.MOVIES_API_KEY
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
       return if (BuildConfig.BUILD_TYPE == "debug") {
            OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val defaultRequest = chain.request()
                    val defaultHttpUrl = defaultRequest.url
                    val httpUrl = defaultHttpUrl.newBuilder()
                        .addQueryParameter(API_NAME, MOVIES_API_KEY)
                        .build()
                    val requestBuilder = defaultRequest.newBuilder().url(httpUrl)
                    chain.proceed(requestBuilder.build())
                }.build()
        } else {
            OkHttpClient.Builder().build() }
    }

    @Provides
    fun provideMoviesApi(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
    ): MoviesApi {
        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(converterFactory)
            .build()

        return retrofit.create(MoviesApi::class.java)
    }

    @Provides
    @ExperimentalSerializationApi
    fun provideConverterFactory(): Converter.Factory =
        GsonConverterFactory.create()
}
