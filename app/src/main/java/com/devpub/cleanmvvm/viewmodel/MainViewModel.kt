package com.devpub.cleanmvvm.viewmodel

import androidx.lifecycle.viewModelScope
import com.devpub.domain.model.UiState
import com.devpub.domain.usecase.MainUseCase
import com.devpub.cleanmvvm.ui.common.mvvm.BaseViewModel
import com.devpub.cleanmvvm.util.LogD
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainUseCase: MainUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    fun getData() {
        viewModelScope.launch {
            mainUseCase.getData().collect { uiState ->
                LogD(message = "uiState:$uiState")
                _uiState.value = uiState
            }
        }
    }
}