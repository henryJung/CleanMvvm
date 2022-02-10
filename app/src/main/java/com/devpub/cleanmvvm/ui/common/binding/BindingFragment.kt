package com.devpub.cleanmvvm.ui.common.binding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.devpub.cleanmvvm.util.bindingadapter.getBinding

abstract class BindingFragment<VB : ViewDataBinding> : Fragment() {

    private var _binding: VB? = null
    protected val binding
        get() = _binding
            ?: throw RuntimeException("Should only use binding after onCreateView and before onDestroyView")

//    protected abstract val viewModel: V

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = getBinding(inflater, container)
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
        _binding = null
    }

    protected inline fun bind(action: VB.() -> Unit) {
        binding.apply(action)
    }

    protected abstract fun setUpViews()
    protected abstract fun observeViewModel()
}