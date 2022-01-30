package com.devpub.cleanmvvm.util.bindingadapter

import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devpub.domain.model.ListItem
import com.devpub.domain.model.UiState
import com.devpub.cleanmvvm.util.MoneyUtil.commaWon
import com.devpub.cleanmvvm.util.ViewUtil.dpToPx
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import coil.load
import com.devpub.cleanmvvm.R
import com.devpub.cleanmvvm.ui.common.list.BasePageListAdapter
import com.devpub.cleanmvvm.ui.common.list.FooterStateAdapter


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
    fun RecyclerView.bindListItems(adapter: PagingDataAdapter<*,*>, uiState: UiState) {
        this.adapter =
            adapter.withLoadStateFooter(FooterStateAdapter { adapter.retry() })
        if (adapter is BasePageListAdapter && uiState is UiState.Success<*>) {
            adapter.submitData(
                (context as AppCompatActivity).lifecycle,
                uiState.data as PagingData<ListItem>
            )
        }
    }
}

@BindingAdapter("html")
fun setHtml(view: View, visible: Boolean) {
    if (visible) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

@BindingAdapter("checked")
fun bindChecked(view: View, checked: Boolean) {
    view.isSelected = checked
}

@BindingAdapter("money_text")
fun setMoneyText(textView: TextView, amount: Long = 0) {
    textView.text = amount.commaWon()
}


@BindingAdapter("android:layout_marginTop")
fun setLayoutMarginTop(view: View, margin: Boolean) {
    if (view.layoutParams is ViewGroup.MarginLayoutParams) {
        (view.layoutParams as ViewGroup.MarginLayoutParams).topMargin =
            if (margin) view.dpToPx(20F) else 0
    }
}

@BindingAdapter("android:src")
fun setImageUri(view: ImageView, imageUri: String?) {
    if (imageUri == null) {
        view.setImageURI(null)
    } else {
        view.setImageURI(Uri.parse(imageUri))
    }
}

@BindingAdapter("android:src")
fun setImageUri(view: ImageView, imageUri: Uri?) {
    view.setImageURI(imageUri)
}

@BindingAdapter("android:src")
fun setImageDrawable(view: ImageView, drawable: Drawable?) {
    view.setImageDrawable(drawable)
}

@BindingAdapter("android:src")
fun setImageResource(imageView: ImageView, resource: Int) {
    imageView.setImageResource(resource)
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
