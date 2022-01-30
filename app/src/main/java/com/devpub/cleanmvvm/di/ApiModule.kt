package com.devpub.cleanmvvm.di

import com.devpub.data.remote.BookApi
import com.devpub.data.remote.PhotoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun providePhotoApiService(@PhotoRetrofit retrofit: Retrofit): PhotoApi = retrofit.create(PhotoApi::class.java)

    @Provides
    @Singleton
    fun provideBookApiService(@BookRetrofit retrofit: Retrofit): BookApi = retrofit.create(BookApi::class.java)
}