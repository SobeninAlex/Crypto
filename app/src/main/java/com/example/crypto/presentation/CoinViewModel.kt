package com.example.crypto.presentation

import androidx.lifecycle.ViewModel
import com.example.crypto.domain.usecases.GetCoinInfoListUseCase
import com.example.crypto.domain.usecases.GetCoinInfoUseCase
import com.example.crypto.domain.usecases.LoadDataUseCase
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val getCoinInfoListUseCase: GetCoinInfoListUseCase,
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    private val loadDataUseCase: LoadDataUseCase
) : ViewModel() {

    val coinInfoList = getCoinInfoListUseCase()

    init {
        loadDataUseCase()
    }

    fun getDetailInfo(fromSymbol: String) =
        getCoinInfoUseCase(fromSymbol = fromSymbol)

}