package com.example.crypto.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.crypto.data.database.AppDatabase
import com.example.crypto.data.mapper.CoinMapper
import com.example.crypto.data.workers.RefreshDataWorker
import com.example.crypto.domain.CoinRepository
import com.example.crypto.domain.entity.CoinInfoEntity

class CoinRepositoryImpl(
    private val application: Application
) : CoinRepository {

    private val mapper = CoinMapper()
    private val coinInfoDao = AppDatabase.getInstance(application).coinPriceInfoDao()

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

    override fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            RefreshDataWorker.WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.makeRequest()
        )
    }

}