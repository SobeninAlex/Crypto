package com.example.crypto.domain.usecases

import com.example.crypto.domain.CoinRepository
import javax.inject.Inject

class GetCoinInfoUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(fromSymbol: String) =
        repository.getCoinInfo(fromSymbol = fromSymbol)
}