package com.devpub.domain.usecase

import com.devpub.domain.repository.MainRepository
import javax.inject.Inject

class MainUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    fun getData() = mainRepository.getData()
}