package com.example.crypto.domain.usecases

import com.example.crypto.domain.CoinRepository

class LoadDataUseCase(
    private val repository: CoinRepository
) {
    operator fun invoke() = repository.loadData()
}