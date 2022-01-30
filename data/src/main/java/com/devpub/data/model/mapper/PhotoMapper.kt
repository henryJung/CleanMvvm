package com.devpub.data.model.mapper

import com.devpub.data.model.PhotoRandomResponse
import com.devpub.data.model.PhotoResponse
import com.devpub.domain.model.Photo
import com.devpub.domain.model.RandomPhoto

object PhotoMapper {
    fun PhotoResponse.mapToPhoto(): Photo {
        return Photo(
            id = this.id,
            author= this.author,
            width = this.width,
            height = this.height,
            url = this.url,
            downloadUrl = this.downloadUrl
        )
    }

    fun PhotoRandomResponse.mapToRandomPhoto(): RandomPhoto {
        return RandomPhoto(
            id = this.id,
            author = this.author,
            width = this.width,
            height = this.height,
            url = this.url,
            downloadUrl = this.downloadUrl
        )
    }
    fun mapperToPhoto(photoResponse: PhotoResponse): Photo {
        return Photo(
            id = photoResponse.id,
            author= photoResponse.author,
            width = photoResponse.width,
            height = photoResponse.height,
            url = photoResponse.url,
            downloadUrl = photoResponse.downloadUrl
        )
    }

    fun mapperToRandomPhoto(randomResponse: PhotoRandomResponse): RandomPhoto {
        return RandomPhoto(
            id = randomResponse.id,
            author = randomResponse.author,
            width = randomResponse.width,
            height = randomResponse.height,
            url = randomResponse.url,
            downloadUrl = randomResponse.downloadUrl
        )
    }
}