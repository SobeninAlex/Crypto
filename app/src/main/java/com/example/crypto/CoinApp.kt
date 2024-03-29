package com.example.crypto

import android.app.Application
import androidx.work.Configuration
import com.example.crypto.data.workers.CoinWorkerFactory
import com.example.crypto.di.DaggerApplicationComponent
import javax.inject.Inject

class CoinApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: CoinWorkerFactory

    val component by lazy {
        DaggerApplicationComponent.factory()
            .create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }


    /**
     * Определяем как будет создан Worker
     */
    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    /**
     * так же надо в манифесте определить
     * <provider
     *             android:authorities="androidx.work.WorkManagerInitializer"
     *             android:name="androidx.startup.InitializationProvider"
     *             tools:replace="android:authorities"
     *             tools:node="remove"/>
     */

}