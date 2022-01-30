package com.devpub.cleanmvvm.ui.common.list

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.devpub.domain.model.ListItem
import com.devpub.cleanmvvm.ui.common.list.viewholder.BaseViewHolder
import com.devpub.cleanmvvm.ui.common.list.viewholder.ViewHolderGenerator

class BaseListAdapter(
    context: Context,
    private val handler: BaseRecyclerHandler = BaseRecyclerHandler(context)
) : ListAdapter<ListItem, BaseViewHolder>(DiffCallback()) {

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return item?.viewType?.ordinal ?: -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ViewHolderGenerator.get(parent, viewType, this, handler)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    class DiffCallback<T : ListItem> : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
            oldItem.viewType == newItem.viewType && oldItem.hashCode() == newItem.hashCode()
    }
}