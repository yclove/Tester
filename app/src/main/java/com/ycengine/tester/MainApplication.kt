package com.ycengine.tester

import androidx.multidex.MultiDexApplication
import timber.log.Timber

class MainApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        instance = this
        configLogger()
    }

    private fun configLogger() {
//        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
//        }
    }

    companion object {
        lateinit var instance: MainApplication
    }
}