package com.devpub.cleanmvvm.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import androidx.paging.LoadState
import com.devpub.cleanmvvm.R
import com.devpub.cleanmvvm.databinding.ActivityMainBinding
import com.devpub.cleanmvvm.ui.common.binding.BindingActivity
import com.devpub.cleanmvvm.ui.common.list.BasePageListAdapter
import com.devpub.cleanmvvm.ui.common.mvvm.BaseActivity
import com.devpub.cleanmvvm.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>() {

    private val viewModel: MainViewModel by viewModels()
    private val adapter: BasePageListAdapter by lazy { BasePageListAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setUpViews()
    }

    private fun setUpViews() {
        bind {
            adapter = this@MainActivity.adapter
            viewModel = this@MainActivity.viewModel
        }
    }

}