package dev.kamenivska.myapplication.main

import android.app.Application
import dev.kamenivska.myapplication.main.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GidraApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@GidraApp)
            modules(
                listOf(
                    appModule,
                ),
            )
        }
    }
}