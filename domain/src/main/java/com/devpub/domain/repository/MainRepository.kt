package com.devpub.domain.repository

import com.devpub.domain.model.UiState
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    fun getData() : Flow<UiState>
}