package com.example.crypto.data.workers

import android.content.Context
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.crypto.data.database.AppDatabase
import com.example.crypto.data.database.CoinInfoDao
import com.example.crypto.data.mapper.CoinMapper
import com.example.crypto.data.network.ApiFactory
import com.example.crypto.data.network.ApiService
import kotlinx.coroutines.delay

class RefreshDataWorker(
    context: Context,
    workerParams: WorkerParameters,
    private val mapper: CoinMapper,
    private val coinInfoDao: CoinInfoDao,
    private val apiService: ApiService
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
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
            } catch (ex: Exception) {
            }
            delay(10000)
        }
        //return не требуется та как жестко указываем бесконечный цикл
    }

    companion object {
        const val WORK_NAME = "RefreshDataWorker"

        fun makeRequest(): OneTimeWorkRequest =
            OneTimeWorkRequestBuilder<RefreshDataWorker>()
                .build()
    }

}