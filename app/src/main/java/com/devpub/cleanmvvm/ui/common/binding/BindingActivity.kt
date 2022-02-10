package com.devpub.cleanmvvm.ui.common.binding

import android.os.Bundle
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import com.devpub.cleanmvvm.ui.common.mvvm.BaseViewModel
import com.devpub.cleanmvvm.util.bindingadapter.getBinding

abstract class BindingActivity<VB : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var binding: VB
        private set

//    private val loadingView by lazy { LoadingView() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        beforeBindView()
        if (::binding.isInitialized.not()) {
            binding = getBinding()
            setContentView(binding.root)
        }
        binding.lifecycleOwner = this
    }

//    fun showLoading(cancelable: Boolean = false) {
//        loadingView.setClickCancelable(cancelable)
//        loadingView.show(supportFragmentManager, null)
//    }
//
//    fun hideLoading() {
//        if (loadingView.showsDialog) {
//            loadingView.dismiss()
//        }
//    }

    @MainThread
    protected inline fun <reified VM : BaseViewModel> viewModels(
        noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null,
    ): Lazy<VM> {
        val factoryPromise = factoryProducer ?: {
            defaultViewModelProviderFactory
        }

        return ViewModelLazy(VM::class, { viewModelStore }, factoryPromise)
            .also {
//                it.value.isLoading.observe(this) { show ->
//                    if (show) {
//                        showLoading()
//                    } else {
//                        hideLoading()
//                    }
//                }
            }
    }

    protected open fun beforeBindView() {
        // Nothing
    }

    protected inline fun bind(action: VB.() -> Unit) {
        binding.run(action)
    }
}