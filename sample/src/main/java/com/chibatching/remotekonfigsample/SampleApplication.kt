package com.chibatching.remotekonfigsample

import android.app.Application
import com.chibatching.remotekonfig.RemoteKonfig

class SampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        RemoteKonfig.initialize {
            minimumFetchIntervalInSeconds = if (BuildConfig.DEBUG) 0L else 3600L
            registerModels(SampleKonfig, OtherKonfig)
        }
    }
}
