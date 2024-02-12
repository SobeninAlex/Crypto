package com.example.crypto.di.module

import androidx.work.ListenableWorker
import com.example.crypto.data.workers.ChildWorkerFactory
import com.example.crypto.data.workers.RefreshDataWorker
import com.example.crypto.di.annotation.WorkerKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorkerModule {

    @IntoMap
    @WorkerKey(RefreshDataWorker::class)
    @Binds
    fun bindRefreshDataWorkerFactory(impl: RefreshDataWorker.Factory): ChildWorkerFactory

}