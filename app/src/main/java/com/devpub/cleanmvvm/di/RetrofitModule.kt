package com.devpub.cleanmvvm.di

import androidx.viewbinding.BuildConfig
import com.devpub.data.network.*
import com.devpub.data.network.header.HeaderInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RetrofitModule {

    private fun getLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = Level.BASIC
        }

    @Provides
    @Singleton
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient.Builder {
        return OkHttpClient.Builder().apply {
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            addInterceptor(getLoggingInterceptor())
        }
    }

    @PhotoRetrofit
    @Provides
    @Singleton
    fun providesPhotoRetrofit(
        client: OkHttpClient.Builder,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        client.addInterceptor(HeaderInterceptor())
        return Retrofit.Builder()
            .baseUrl("$PHOTO_URL")
            .addConverterFactory(gsonConverterFactory)
            .client(client.build())
            .build()
    }

    @BookRetrofit
    @Provides
    @Singleton
    fun providesBookRetrofit(
        client: OkHttpClient.Builder,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        client.addInterceptor(HeaderInterceptor())
        return Retrofit.Builder()
            .baseUrl("$BOOK_URL$VERSION/")
            .addConverterFactory(gsonConverterFactory)
            .client(client.build())
            .build()
    }
}