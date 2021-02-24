package com.kodingan.endemic

import android.app.Application
import com.kodingan.endemic.core.di.databaseModule
import com.kodingan.endemic.core.di.networkModule
import com.kodingan.endemic.core.di.repositoryModule
import com.kodingan.endemic.di.useCaseModule
import com.kodingan.endemic.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            androidLogger(Level.NONE)

            modules(
                listOf(
                        viewModelModule,
                        databaseModule,
                        networkModule,
                        repositoryModule,
                        useCaseModule

                )
            )
        }
    }
}