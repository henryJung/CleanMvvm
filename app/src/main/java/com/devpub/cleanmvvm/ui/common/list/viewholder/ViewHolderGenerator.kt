package com.devpub.cleanmvvm.ui.common.list.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.devpub.domain.model.ViewType
import com.devpub.cleanmvvm.R
import com.devpub.cleanmvvm.databinding.*
import com.devpub.cleanmvvm.ui.common.list.BaseRecyclerHandler
import com.devpub.cleanmvvm.ui.list.viewholder.*
import com.devpub.cleanmvvm.ui.list.viewholder.RandomPhotoViewHolder

object ViewHolderGenerator {

    fun get(
        parent: ViewGroup,
        viewType: Int,
        adapter: RecyclerView.Adapter<BaseViewHolder>,
        handler: BaseRecyclerHandler
    ): BaseViewHolder {
        return when (viewType) {
            ViewType.PHOTO.ordinal -> {
                PhotoViewHolder(parent.inflate(R.layout.item_photo), handler)
            }
            ViewType.RANDOM_PHOTO.ordinal -> {
                RandomPhotoViewHolder(parent.inflate(R.layout.item_photo_random), handler)
            }
            ViewType.BOOK.ordinal -> {
                BookViewHolder(parent.inflate(R.layout.item_book), handler)
            }
            ViewType.EMPTY.ordinal -> {
                EmptyFooterViewHolder(parent.inflate(R.layout.item_empty_footer), handler)
            }
            else -> {
                ItemViewHolder(parent.inflate(R.layout.item_empty))
            }
        }
    }

    class ItemViewHolder(binding: ItemEmptyBinding) : BaseViewHolder(binding)

    fun <T : ViewDataBinding> ViewGroup.inflate(layout: Int): T {
        return DataBindingUtil.inflate(
            LayoutInflater.from(context),
            layout,
            this,
            false
        )
    }
}