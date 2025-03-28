package org.bloomy.project

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.bloomy.project.di.initKoin

class BloomyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@BloomyApplication)
        }
    }
}