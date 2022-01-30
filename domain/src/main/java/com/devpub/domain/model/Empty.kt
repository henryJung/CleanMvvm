package com.devpub.domain.model

data class Empty(
    val background: Int = -1
) : ListItem {

    override val viewType: ViewType
        get() = ViewType.EMPTY

}