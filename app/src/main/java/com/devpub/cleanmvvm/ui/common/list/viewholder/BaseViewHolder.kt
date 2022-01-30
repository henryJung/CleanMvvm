package com.devpub.cleanmvvm.ui.common.list.viewholder

import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.devpub.domain.model.ListItem
import com.devpub.cleanmvvm.ui.common.list.BaseRecyclerHandler

abstract class BaseViewHolder(
    private val binding: ViewDataBinding,
    private val handler: BaseRecyclerHandler? = null
) : RecyclerView.ViewHolder(binding.root) {

    protected var item: ListItem? = null

    open fun bind(item: ListItem) {
        this.item = item
        binding.setVariable(BR.item, this.item)
        binding.setVariable(BR.handler, handler)
    }
}

