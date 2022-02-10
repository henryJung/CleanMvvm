package com.devpub.cleanmvvm.ui.common.binding

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.devpub.cleanmvvm.util.bindingadapter.getBinding
import com.devpub.cleanmvvm.util.bindingadapter.inflater

abstract class BindingComponent<VB : ViewDataBinding> @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @LayoutRes resId: Int? = null
) : LinearLayout(context, attrs) {
    protected lateinit var binding: VB

    init {
        if (isInEditMode && resId != null) {
            inflate(context, resId, null)
        } else {
            initBinding()
        }
    }

    private fun initBinding() {
        if (::binding.isInitialized.not()) {
            binding = getBinding(context.inflater, this, true)
        }
    }
}