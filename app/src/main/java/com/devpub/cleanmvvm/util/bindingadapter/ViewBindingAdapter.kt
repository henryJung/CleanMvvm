package com.devpub.cleanmvvm.util.bindingadapter

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devpub.domain.model.ListItem
import com.devpub.domain.model.UiState
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import coil.load
import com.devpub.cleanmvvm.R
import com.devpub.cleanmvvm.ui.common.list.BasePageListAdapter
import com.devpub.cleanmvvm.ui.common.list.FooterStateAdapter
import com.devpub.cleanmvvm.ui.widget.PagingListView


object ViewBindingAdapter {

    @JvmStatic
    @BindingAdapter("show")
    fun ProgressBar.bindShow(uiState: UiState) {
        isVisible = uiState is UiState.Loading
    }

    @JvmStatic
    @BindingAdapter("toast")
    fun View.bindToast(uiState: UiState) {
        if (uiState is UiState.Error) {
            uiState.error?.message?.let { errorMessage ->
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    @JvmStatic
    @BindingAdapter("adapter", "listItems")
    fun PagingListView.bindListItems(adapter: BasePageListAdapter, uiState: UiState) {
        setAdapter(adapter)
        if (uiState is UiState.Success<*>) {
            findViewTreeLifecycleOwner()?.let {
                adapter.submitData(
                    it.lifecycle,
                    uiState.data as PagingData<ListItem>
                )
            }
        }
    }
}

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    imageView.load(url) {
        crossfade(true)
        placeholder(R.color.hintTextColor)
    }
}

@BindingAdapter("visible")
fun setVisible(view: View, visible: Boolean) {
    view.isVisible = visible
}
