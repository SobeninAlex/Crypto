package com.example.crypto.domain.usecases

import com.example.crypto.domain.CoinRepository
import javax.inject.Inject

class LoadDataUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke() = repository.loadData()
}