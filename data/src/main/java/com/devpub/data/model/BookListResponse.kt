package com.devpub.data.model


data class BookListResponse(
    val error: String,
    val total: Long,
    val page: Int,
    val books: List<BookResponse>? = null,
)