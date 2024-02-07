package com.example.crypto.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.crypto.data.repository.CoinRepositoryImpl
import com.example.crypto.data.workers.RefreshDataWorker
import com.example.crypto.domain.usecases.GetCoinInfoListUseCase
import com.example.crypto.domain.usecases.GetCoinInfoUseCase
import com.example.crypto.domain.usecases.LoadDataUseCase
import kotlinx.coroutines.launch

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CoinRepositoryImpl(application)

    private val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository)
    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    val coinInfoList = getCoinInfoListUseCase()

    init {
        loadDataUseCase()
    }

    fun getDetailInfo(fromSymbol: String) =
        getCoinInfoUseCase(fromSymbol = fromSymbol)

}