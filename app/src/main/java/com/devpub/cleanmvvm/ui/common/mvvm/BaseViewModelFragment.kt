package com.devpub.cleanmvvm.ui.common.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseViewModelFragment<T : ViewDataBinding, V : BaseViewModel>(@LayoutRes private val layoutId: Int) :
    Fragment() {

    private var _binding: T? = null
    protected val binding get() = _binding!!
    protected abstract val viewModel: V

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        observeViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding?.unbind()
    }

    protected inline fun bind(action: T.() -> Unit) {
        binding.apply(action)
    }

    protected abstract fun setUpViews()
    protected abstract fun observeViewModel()
}