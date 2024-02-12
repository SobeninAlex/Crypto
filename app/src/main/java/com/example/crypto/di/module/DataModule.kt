package com.example.crypto.di.module

import android.app.Application
import com.example.crypto.data.database.AppDatabase
import com.example.crypto.data.database.CoinInfoDao
import com.example.crypto.data.network.ApiFactory
import com.example.crypto.data.network.ApiService
import com.example.crypto.di.annotation.ApplicationScope
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @ApplicationScope
    fun provideCoinInfoDao(application: Application): CoinInfoDao =
        AppDatabase.getInstance(application).coinPriceInfoDao()

    @Provides
    @ApplicationScope
    fun provideApiService(): ApiService =
        ApiFactory.apiService

}