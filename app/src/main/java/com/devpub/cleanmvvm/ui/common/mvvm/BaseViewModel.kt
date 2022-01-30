package com.devpub.cleanmvvm.ui.common.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devpub.cleanmvvm.BuildConfig
import com.devpub.cleanmvvm.util.TAG
import kotlinx.coroutines.*

open class BaseViewModel : ViewModel() {

    val isLoading: LiveData<Boolean> by lazy { _isLoading }
    val toast: LiveData<String> by lazy { _toast }
    val errorEvent: LiveData<Throwable> by lazy { _errorEvent }

    private val _isLoading by lazy { MutableLiveData(false) }
    private val _toast by lazy { MutableLiveData<String>() }
    private val _errorEvent = SingleLiveEvent<Throwable>()

    protected val errorHandler = CoroutineExceptionHandler { _, exception ->
        if(BuildConfig.DEBUG) {
            Log.e("errorHandler($TAG)", exception.toString())
        }
        _errorEvent.value = exception
    }

    private val asyncJob by lazy { SupervisorJob() }
    protected val asyncScope by lazy { CoroutineScope(asyncJob + Dispatchers.IO) }
    protected val asyncErrorHandler by lazy {
        CoroutineExceptionHandler { context, exception ->
            if(BuildConfig.DEBUG) {
                Log.e("asyncScope_okhttp($TAG)", exception.toString())
            }
            context.cancel()
        }
    }

    fun setLoading(isLoading: Boolean) {
        _isLoading.postValue(isLoading)
    }

    fun showToast(message: String) {
        _toast.postValue(message)
    }
}