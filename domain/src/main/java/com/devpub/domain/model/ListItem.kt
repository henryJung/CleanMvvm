package com.devpub.domain.model

interface ListItem {
    val viewType: ViewType

    fun getKey() = hashCode()
}

enum class ViewType {
    EMPTY,
    PHOTO,
    RANDOM_PHOTO,
    BOOK,
}