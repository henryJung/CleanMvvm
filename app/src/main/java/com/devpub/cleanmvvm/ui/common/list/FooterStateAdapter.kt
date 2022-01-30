package com.devpub.cleanmvvm.ui.common.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.devpub.cleanmvvm.R
import com.devpub.cleanmvvm.databinding.ItemFooterBinding
import com.devpub.cleanmvvm.ui.common.list.viewholder.FooterStateViewHolder

class FooterStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<FooterStateViewHolder>() {

    override fun onBindViewHolder(holder: FooterStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): FooterStateViewHolder {
        return FooterStateViewHolder(
            ItemFooterBinding.bind(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_footer, parent, false)
            ), retry
        )
    }
}