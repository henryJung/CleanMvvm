package com.devpub.cleanmvvm.di

import com.devpub.data.remote.BookApi
import com.devpub.data.remote.PhotoApi
import com.devpub.data.repository.MainRepositoryImpl
import com.devpub.domain.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @ViewModelScoped
    @Provides
    fun providesMainRepository(
        photoApi: PhotoApi,
        bookApi: BookApi,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): MainRepository = MainRepositoryImpl(photoApi, bookApi, dispatcher)

}