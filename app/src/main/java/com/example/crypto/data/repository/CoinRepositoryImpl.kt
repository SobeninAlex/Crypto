package com.example.crypto.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.crypto.data.database.AppDatabase
import com.example.crypto.data.mapper.CoinMapper
import com.example.crypto.data.network.ApiFactory
import com.example.crypto.domain.CoinRepository
import com.example.crypto.domain.entity.CoinInfoEntity
import kotlinx.coroutines.delay

class CoinRepositoryImpl(
    private val application: Application
) : CoinRepository {

    private val mapper = CoinMapper()
    private val coinInfoDao = AppDatabase.getInstance(application).coinPriceInfoDao()
    private val apiService = ApiFactory.apiService

    override fun getCoinInfoList(): LiveData<List<CoinInfoEntity>> {
        return coinInfoDao.getPriceList().map { list ->
            list.map {
                mapper.mapCoinInfoDbModelToCoinInfoEntity(it)
            }
        }
    }

    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfoEntity> {
        return coinInfoDao.getPriceInfoAboutCoin(fSym = fromSymbol).map {
            mapper.mapCoinInfoDbModelToCoinInfoEntity(it)
        }
    }

    override suspend fun loadData() {
        while (true) {
            try {
                val topCoins = apiService.getTopCoinsInfo(limit = 50)
                val fromSymbol = mapper.mapCoinNamesListDtoToString(topCoins)
                val json = apiService.getFullPriceList(fromSymbol = fromSymbol)
                val listCoinInfoDto = mapper.mapCoinInfoJsonContainerDtoToListCoinInfoDto(json)
                val listCoinInfoDbModel = listCoinInfoDto.map {
                    mapper.mapCoinInfoDtoToCoinInfoDbModel(it)
                }
                coinInfoDao.insertPriceList(listCoinInfoDbModel)
            } catch (ex: Exception) {}
            delay(10000)
        }
    }
}