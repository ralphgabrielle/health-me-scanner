package com.yondu.project.healthmescanner

import android.app.Application
import com.yondu.project.healthmescanner.di.appModule
import org.koin.android.ext.android.startKoin
import timber.log.Timber

/*
* Created by Ralph Gabrielle Orden on June 08 2020
*/

class HealthMeApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        plantDebugTree()
        configureDependencyInjection()
    }

    private fun configureDependencyInjection() {
        startKoin(this, appModule)
    }

    private fun plantDebugTree() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}