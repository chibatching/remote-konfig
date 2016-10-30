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

    internal fun setDefault(key: String, value: Any) {
        defaultValues.put(key, value)
    }

    fun register(vararg model: KonfigModel) {
        FirebaseRemoteConfig.getInstance().setDefaults(defaultValues)
    }

    fun fetch(onComplete: (() -> Unit)? = null, onFailure: ((Exception) -> Unit)? = null) {
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
