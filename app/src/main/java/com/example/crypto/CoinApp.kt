package com.example.crypto

import android.app.Application
import com.example.crypto.di.DaggerApplicationComponent

class CoinApp : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory()
            .create(this)
    }

}