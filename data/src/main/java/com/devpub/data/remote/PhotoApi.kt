package com.devpub.data.remote

import com.devpub.data.model.PhotoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApi {

    @GET("v2/list")
    suspend fun getList(
        @Query("page") page: Int,
        @Query("limit") limit: Int = 30
    ): Response<List<PhotoResponse>>
}