package com.example.crypto.di.module

import com.example.crypto.data.repository.CoinRepositoryImpl
import com.example.crypto.domain.CoinRepository
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    fun bindCoinRepository(impl: CoinRepositoryImpl): CoinRepository

}