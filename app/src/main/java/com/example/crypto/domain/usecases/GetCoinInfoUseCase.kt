package com.example.crypto.domain.usecases

import com.example.crypto.domain.CoinRepository

class GetCoinInfoUseCase(
    private val repository: CoinRepository
) {
    operator fun invoke(fromSymbol: String) =
        repository.getCoinInfo(fromSymbol = fromSymbol)
}