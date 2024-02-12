package com.example.crypto.di.module

import com.example.crypto.data.repository.CoinRepositoryImpl
import com.example.crypto.di.annotation.ApplicationScope
import com.example.crypto.domain.CoinRepository
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    @ApplicationScope
    fun bindCoinRepository(impl: CoinRepositoryImpl): CoinRepository

}