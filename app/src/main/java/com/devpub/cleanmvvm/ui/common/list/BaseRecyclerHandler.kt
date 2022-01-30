package com.devpub.cleanmvvm.ui.common.list

import android.content.Context
import android.view.View

import com.devpub.cleanmvvm.ui.common.list.viewholder.ClickType
import com.devpub.cleanmvvm.util.extension.toast
import com.devpub.domain.model.ListItem

open class BaseRecyclerHandler(private val context: Context) {

    open fun onClickButton(type: ClickType, item: ListItem? = null) {
        when (type) {
            ClickType.Random -> {

            }
            ClickType.Photo -> {
                context.toast("Photo Click")
            }
            ClickType.Book -> {
                context.toast("Book Click")
            }
        }
    }
}