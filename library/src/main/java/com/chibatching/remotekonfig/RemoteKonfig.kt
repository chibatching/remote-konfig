package com.chibatching.remotekonfig

import com.google.android.gms.tasks.Tasks
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import java.util.*
import java.util.concurrent.TimeUnit


object RemoteKonfig {

    private val defaultValues: MutableMap<String, Any> = HashMap()

    var developerMode: Boolean = false
        set(value) {
            field = value
            FirebaseRemoteConfig.getInstance().apply {
                FirebaseRemoteConfigSettings.Builder()
                        .setDeveloperModeEnabled(value)
                        .build()
                        .let {
                            setConfigSettings(it)
                        }
            }
        }

    var cacheExpirationSeconds: Long = 43200L

    fun register(vararg model: KonfigModel) {
        FirebaseRemoteConfig.getInstance().setDefaults(defaultValues)
    }

    internal fun register(clazz: Class<out Any>, key: String, default: Any) {
        if (defaultValues.containsKey(key)) {
            throw IllegalArgumentException("Key $key of ${clazz.simpleName} is already registered.")
        } else {
            defaultValues[key] = default
        }
    }

    fun fetchAsync(onComplete: (() -> Unit)? = null) {
        fetchAsync(onComplete, null)
    }

    fun fetchAsync(onComplete: (() -> Unit)?, onFailure: ((Exception) -> Unit)?) {
        FirebaseRemoteConfig.getInstance()
                .fetch(cacheExpirationSeconds)
                .apply {
                    addOnSuccessListener { onComplete?.invoke() }
                    addOnFailureListener { onFailure?.invoke(it) }
                }
    }

    fun fetch(timeout: Long, timeUnit: TimeUnit) {
        Tasks.await(FirebaseRemoteConfig.getInstance().fetch(), timeout, timeUnit)
    }

    fun fetch() {
        Tasks.await(FirebaseRemoteConfig.getInstance().fetch())
    }

    fun activate() {
        FirebaseRemoteConfig.getInstance().activateFetched()
    }
}
