package com.devpub.data.model.mapper

import com.devpub.data.model.BookDetailResponse
import com.devpub.data.model.BookResponse
import com.devpub.domain.model.Book
import com.devpub.domain.model.BookDetail

object BookMapper {
    fun BookResponse.mapToBook(): Book {
        return Book(
            title = this.title,
            subtitle = this.subtitle,
            isbn13 = this.isbn13,
            price = this.price,
            image = this.image,
            url = this.url
        )
    }

    fun BookDetailResponse.mapToBookDetail(): BookDetail {
        return BookDetail(
            error = this.error,
            title = this.title,
            subtitle = this.subtitle,
            authors = this.authors,
            publisher = this.publisher,
            language = this.language,
            isbn10 = this.isbn10,
            isbn13 = this.isbn13,
            pages = this.pages,
            year = this.year,
            rating = this.rating,
            price = this.price,
            desc = this.desc,
            image = this.image,
            url = this.url
        )
    }
}