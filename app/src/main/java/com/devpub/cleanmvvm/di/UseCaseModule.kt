package com.devpub.cleanmvvm.di

import com.devpub.domain.repository.MainRepository
import com.devpub.domain.usecase.MainUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @ViewModelScoped
    @Provides
    fun providesMainUseCase(
        mainRepository: MainRepository
    ) = MainUseCase(mainRepository)

}