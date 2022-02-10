package com.devpub.cleanmvvm.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.core.view.isVisible
import androidx.paging.LoadState
import com.devpub.cleanmvvm.R
import com.devpub.cleanmvvm.databinding.WidgetListBinding
import com.devpub.cleanmvvm.ui.common.binding.BindingComponent
import com.devpub.cleanmvvm.ui.common.list.BasePageListAdapter
import com.devpub.cleanmvvm.ui.common.list.FooterStateAdapter

class PagingListView @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    BindingComponent<WidgetListBinding>(context, attrs, R.layout.widget_list) {

    fun setAdapter(adapter: BasePageListAdapter) {
        binding.apply {
            recyclerView.adapter =
                adapter.withLoadStateFooter(FooterStateAdapter { adapter.retry() })
            recyclerView.setHasFixedSize(true)
            adapter.apply {
                addLoadStateListener { loadState ->
                    val isListEmpty = (loadState.refresh is LoadState.NotLoading && itemCount == 0)
                        .also { empty ->
                            if (empty) {
                                infoIconImageView.setImageResource(R.drawable.ic_component_none)
                                infoTextView.text = context.getString(R.string.no_data)
                            }
                        }
                    val errorState = loadState.refresh as? LoadState.Error
                        ?: loadState.prepend as? LoadState.Error
                    errorState?.let {
                        infoIconImageView.setImageResource(R.drawable.ic_component_error)
                        infoTextView.text = it.error.localizedMessage
                    }

                    infoLayout.isVisible = isListEmpty || errorState != null
                    progressBar.isVisible = loadState.refresh is LoadState.Loading
                            && itemCount == 0
                    recyclerView.isVisible =
                        errorState !is LoadState.Error && itemCount != 0
                    retryButton.isVisible = errorState is LoadState.Error
                }
            }
            retryButton.setOnClickListener { adapter.retry() }
            swipeRefreshLayout.setColorSchemeResources(R.color.primaryColor)
            swipeRefreshLayout.setOnRefreshListener {
                adapter.refresh()
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }
    }
}