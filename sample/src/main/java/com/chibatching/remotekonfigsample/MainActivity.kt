package com.chibatching.remotekonfigsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.chibatching.remotekonfig.RemoteKonfig

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RemoteKonfig.apply {
            developerMode = BuildConfig.DEBUG
            cacheExpirationSeconds = if (developerMode) 0L else 3600L
            register(SampleKonfig, OtherKonfig)
            fetchAsync(
                    {
                        Log.d(TAG, "fetch success")
                        activate()
                    },
                    { e ->
                        Log.d(TAG, "fetch failure")
                        e.printStackTrace()
                    }
            )
        }
    }
}
