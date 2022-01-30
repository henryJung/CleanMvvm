package com.devpub.cleanmvvm.di

import android.content.Context
import com.devpub.cleanmvvm.ui.common.list.BaseListAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped

@InstallIn(ActivityComponent::class)
@Module
object ActivityModule {

    @Provides
    @ActivityScoped
    fun provideListAdapter(@ActivityContext context: Context): BaseListAdapter = BaseListAdapter(context)

}