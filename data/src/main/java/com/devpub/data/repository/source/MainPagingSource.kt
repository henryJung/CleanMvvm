package com.devpub.data.repository.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.devpub.data.model.mapper.BookMapper.mapToBook
import com.devpub.data.model.mapper.PhotoMapper.mapToPhoto
import com.devpub.data.remote.BookApi
import com.devpub.data.remote.PhotoApi
import com.devpub.domain.model.ListItem
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.lang.Exception
import java.lang.RuntimeException

class MainPagingSource constructor(
    private val photoApi: PhotoApi,
    private val bookApi: BookApi,
) : PagingSource<Int, ListItem>() {

    override fun getRefreshKey(state: PagingState<Int, ListItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ListItem> {
        try {
            val page = params.key ?: 1
            val list = mutableListOf<ListItem>()
            coroutineScope {
                val photoList = async {
                    val photoResponse = photoApi.getList(page)
                    if (photoResponse.isSuccessful) {
                        return@async photoResponse.body()?.map { it.mapToPhoto() } ?: emptyList()
                    } else {
                        throw RuntimeException("[${photoResponse.code()}] - ${photoResponse.raw()}")
                    }
                }
                val bookList = async {
                    val bookResponse = bookApi.getList("java", page)
                    if (bookResponse.isSuccessful) {
                        return@async bookResponse.body()?.books?.map { it.mapToBook() }
                            ?: emptyList()
                    } else {
                        throw RuntimeException("[${bookResponse.code()}] - ${bookResponse.raw()}")
                    }
                }
                list.addAll(photoList.await())
                list.addAll(bookList.await())
                list.shuffle()
            }

            return LoadResult.Page(
                data = list,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (list.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}