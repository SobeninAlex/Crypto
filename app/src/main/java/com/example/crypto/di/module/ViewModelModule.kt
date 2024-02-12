package com.example.crypto.di.module

import androidx.lifecycle.ViewModel
import com.example.crypto.di.annotation.ViewModelKey
import com.example.crypto.presentation.CoinViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(CoinViewModel::class)
    @Binds
    fun bindCoinViewModel(impl: CoinViewModel): ViewModel

}