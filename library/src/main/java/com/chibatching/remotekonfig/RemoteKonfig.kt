package com.chibatching.remotekonfig

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


object RemoteKonfig {

    private val defaultValues: MutableMap<String, Any> = HashMap()

    /**
     * Initialize Remote Config SDK with parameters.
     * See [Firebase Docs](https://firebase.google.com/docs/reference/android/com/google/firebase/remoteconfig/FirebaseRemoteConfigSettings.Builder.html)
     *
     * @param minimumFetchIntervalInSeconds The minimum interval between successive fetch calls.
     * @param fetchTimeoutInSeconds The connection timeout for fetch requests to the Firebase Remote Config servers in seconds.
     */
    suspend fun initialize(
        minimumFetchIntervalInSeconds: Long = TimeUnit.HOURS.toSeconds(12L),
        fetchTimeoutInSeconds: Long = 5L
    ) = suspendCoroutine<Unit> { continuation ->
        FirebaseRemoteConfig.getInstance().apply {
            val config = FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(minimumFetchIntervalInSeconds)
                .setFetchTimeoutInSeconds(fetchTimeoutInSeconds)
                .build()
            setConfigSettingsAsync(config)
                .addOnSuccessListener {
                    continuation.resume(Unit)
                }
                .addOnFailureListener {
                    continuation.resumeWithException(it)
                }
        }
    }

    /**
     * Register KonfigModel to set default value to Remote Config
     *
     * @param model KonfigModel to register
     */
    @Suppress("UNUSED_PARAMETER")
    suspend fun register(vararg model: KonfigModel) = suspendCoroutine<Unit> { continuation ->
        FirebaseRemoteConfig.getInstance().setDefaultsAsync(defaultValues)
            .addOnSuccessListener {
                continuation.resume(Unit)
            }
            .addOnFailureListener {
                continuation.resumeWithException(it)
            }
    }

    internal fun register(clazz: Class<out Any>, key: String, default: Any) {
        if (defaultValues.containsKey(key)) {
            throw IllegalArgumentException("Key $key of ${clazz.simpleName} is already registered.")
        } else {
            defaultValues[key] = default
        }
    }

    /**
     * Fetch remote values from Remote Config
     */
    suspend fun fetch() = suspendCoroutine<Unit> { continuation ->
        FirebaseRemoteConfig.getInstance()
            .fetch()
            .addOnSuccessListener {
                continuation.resume(Unit)
            }
            .addOnFailureListener {
                continuation.resumeWithException(it)
            }
    }

    /**
     * Activate fetched remote values
     */
    suspend fun activate() = suspendCoroutine<Boolean> { continuation ->
        FirebaseRemoteConfig.getInstance().activate()
            .addOnSuccessListener {
                continuation.resume(it)
            }
            .addOnFailureListener {
                continuation.resumeWithException(it)
            }
    }
}
