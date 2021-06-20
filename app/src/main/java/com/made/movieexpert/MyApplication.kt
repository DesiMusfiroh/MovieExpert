package com.made.movieexpert

import android.app.Application
import com.made.movieexpert.core.di.databaseModule
import com.made.movieexpert.core.di.networkModule
import com.made.movieexpert.core.di.repositoryModule
import com.made.movieexpert.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}