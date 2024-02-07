package com.example.crypto.domain

import androidx.lifecycle.LiveData
import com.example.crypto.domain.entity.CoinInfoEntity

interface CoinRepository {

    fun getCoinInfoList(): LiveData<List<CoinInfoEntity>>

    fun getCoinInfo(fromSymbol: String): LiveData<CoinInfoEntity>

    fun loadData()

}