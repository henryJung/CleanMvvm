package com.devpub.domain.model


data class Photo(
    val id: String,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    val downloadUrl: String,
): ListItem {

    override val viewType: ViewType
        get() = ViewType.PHOTO

}