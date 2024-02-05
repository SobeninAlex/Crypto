package com.example.crypto.domain.usecases

import com.example.crypto.domain.CoinRepository

class LoadDataUseCase(
    private val repository: CoinRepository
) {
    suspend operator fun invoke() =
        repository.loadData()
}