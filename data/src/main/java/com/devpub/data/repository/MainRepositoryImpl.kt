package com.devpub.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.devpub.data.remote.BookApi
import com.devpub.data.remote.PhotoApi
import com.devpub.data.repository.source.MainPagingSource
import com.devpub.domain.model.*
import com.devpub.domain.repository.MainRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val photoApi: PhotoApi,
    private val bookApi: BookApi,
    private val dispatcher: CoroutineDispatcher,
) : MainRepository {

    override fun getData() = Pager(
        config = PagingConfig(
            pageSize = 1,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            MainPagingSource(photoApi, bookApi, dispatcher)
        }
    ).flow.map { UiState.Success(it) }

}