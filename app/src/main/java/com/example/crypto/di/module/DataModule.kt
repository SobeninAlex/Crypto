package com.example.crypto.di.module

import android.app.Application
import com.example.crypto.data.database.AppDatabase
import com.example.crypto.data.database.CoinInfoDao
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideCoinInfoDao(application: Application) : CoinInfoDao =
        AppDatabase.getInstance(application).coinPriceInfoDao()

}