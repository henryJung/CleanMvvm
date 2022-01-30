package com.devpub.cleanmvvm.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import androidx.paging.LoadState
import com.devpub.cleanmvvm.R
import com.devpub.cleanmvvm.databinding.ActivityMainBinding
import com.devpub.cleanmvvm.ui.common.list.BaseListAdapter
import com.devpub.cleanmvvm.ui.common.list.BasePageListAdapter
import com.devpub.cleanmvvm.ui.common.mvvm.BaseActivity
import com.devpub.cleanmvvm.util.bindingadapter.ViewBindingAdapter.bindListItems
import com.devpub.cleanmvvm.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModels()
    private val adapter: BasePageListAdapter by lazy { BasePageListAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setUpViews()
        viewModel.getData()
    }

    private fun setUpViews() {
        bind {
            adapter = this@MainActivity.adapter
            viewModel = this@MainActivity.viewModel
            recyclerView.setHasFixedSize(true)
            this@MainActivity.adapter.apply {
                addLoadStateListener { loadState ->
                    val isListEmpty =
                        loadState.refresh is LoadState.NotLoading && itemCount == 0
                    emptyLayout.isVisible = isListEmpty
                    recyclerView.isVisible =
                        loadState.refresh is LoadState.NotLoading && itemCount != 0
                    progressBar.isVisible = loadState.refresh is LoadState.Loading

                    val errorState = loadState.refresh as? LoadState.Error
                        ?: loadState.append as? LoadState.Error
                        ?: loadState.prepend as? LoadState.Error
                    errorState?.let {
                        Toast.makeText(this@MainActivity, it.error.toString(), Toast.LENGTH_LONG)
                            .show()
                    }
                    binding.retryButton.isVisible = errorState is LoadState.Error
                }
            }
        }
    }

}