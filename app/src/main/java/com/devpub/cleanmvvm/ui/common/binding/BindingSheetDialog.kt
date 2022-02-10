package com.devpub.cleanmvvm.ui.common.binding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.devpub.cleanmvvm.util.bindingadapter.getBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BindingSheetDialog<VB : ViewDataBinding> : BottomSheetDialogFragment() {

    private var _binding: VB? = null
    protected val binding: VB
        get() = _binding
            ?: throw RuntimeException("Should only use binding after onCreateView and before onDestroyView")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = getBinding(inflater, container)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}