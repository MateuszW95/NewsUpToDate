package com.mw.news.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.mw.news.global.Shared
import com.mw.news.network.IArticleApiService
import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun providesCoroutinesResponseCallAdapterFactory(): CoroutinesResponseCallAdapterFactory =
        CoroutinesResponseCallAdapterFactory.create()

    @OptIn(ExperimentalSerializationApi::class)
    @Singleton
    @Provides
    fun provideMockApiService(
        okHttpClient: OkHttpClient,
        coroutinesResponseCallAdapterFactory: CoroutinesResponseCallAdapterFactory
    ): IArticleApiService {
        val contentType = "application/json".toMediaType()
        val json = Json() {
            prettyPrint = true
            ignoreUnknownKeys = true
        }

        return Retrofit.Builder()
            .addConverterFactory(json.asConverterFactory(contentType))
            .baseUrl(Shared.articlesApiBaseUrl)
            .addCallAdapterFactory(coroutinesResponseCallAdapterFactory)
            .client(okHttpClient)
            .build()
            .create(IArticleApiService::class.java)
    }
}