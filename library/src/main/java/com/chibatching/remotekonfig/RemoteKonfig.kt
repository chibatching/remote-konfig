package com.chibatching.remotekonfig

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import java.util.*


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

    internal fun register(key: String, default: Any) {
        if (defaultValues.containsKey(key)) {
            throw IllegalArgumentException("Key $key is already registered.")
        } else {
            defaultValues.put(key, default)
        }
    }

    fun fetch(onComplete: (() -> Unit)? = null) {
        fetch(onComplete, null)
    }

    fun fetch(onComplete: (() -> Unit)?, onFailure: ((Exception) -> Unit)?) {
        FirebaseRemoteConfig.getInstance()
                .fetch(cacheExpirationSeconds)
                .apply {
                    addOnSuccessListener { onComplete?.invoke() }
                    addOnFailureListener { onFailure?.invoke(it) }
                }
    }

    fun activate() {
        FirebaseRemoteConfig.getInstance().activateFetched()
    }
}
