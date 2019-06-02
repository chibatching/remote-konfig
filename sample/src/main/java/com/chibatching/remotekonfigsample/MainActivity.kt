package com.chibatching.remotekonfigsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chibatching.remotekonfig.RemoteKonfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val job = SupervisorJob()

    private val coroutineContext = Dispatchers.Main + job

    private val lifecycleScope = CoroutineScope(coroutineContext)

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            RemoteKonfig.initialize(
                minimumFetchIntervalInSeconds = if (BuildConfig.DEBUG) 0L else 3600L
            )
            RemoteKonfig.register(SampleKonfig, OtherKonfig)
            RemoteKonfig.fetch()
            RemoteKonfig.activate()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}
