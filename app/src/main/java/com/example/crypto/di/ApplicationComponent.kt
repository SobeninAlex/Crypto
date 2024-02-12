package com.example.crypto.di

import android.app.Application
import com.example.crypto.CoinApp
import com.example.crypto.di.annotation.ApplicationScope
import com.example.crypto.di.module.DataModule
import com.example.crypto.di.module.DomainModule
import com.example.crypto.di.module.ViewModelModule
import com.example.crypto.presentation.CoinDetailFragment
import com.example.crypto.presentation.CoinPriceListActivity
import dagger.BindsInstance
import dagger.Component
import dagger.Subcomponent

@ApplicationScope
@Component(modules = [DomainModule::class, ViewModelModule::class, DataModule::class])
interface ApplicationComponent {

    fun inject(activity: CoinPriceListActivity)

    fun inject(fragment: CoinDetailFragment)

    fun inject(coinApp: CoinApp)

    @Component.Factory
    interface ApplicationComponentFactory {

        fun create(
            @BindsInstance app: Application
        ): ApplicationComponent

    }

}