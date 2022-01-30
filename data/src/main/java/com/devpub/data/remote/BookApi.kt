package com.devpub.data.remote

import com.devpub.data.model.BookDetailResponse
import com.devpub.data.model.BookListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BookApi {

    @GET("search/{query}/{page}")
    suspend fun getList(@Path("query") query: String, @Path("page") page: Int): Response<BookListResponse>

    @GET("books/{isbn13}")
    suspend fun getDetail(@Path("isbn13") isbn13: String): Response<BookDetailResponse>
}