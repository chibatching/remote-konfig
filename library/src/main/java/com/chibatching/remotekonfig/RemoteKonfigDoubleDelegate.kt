package com.chibatching.remotekonfig

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


class RemoteKonfigDoubleDelegate internal constructor(private val key: String, default: Double)
    : ReadOnlyProperty<KonfigModel, Double>, RemoteKonfigDelegate<Double>(key, default) {

    override fun getValue(thisRef: KonfigModel, property: KProperty<*>): Double {
        return FirebaseRemoteConfig.getInstance().getDouble(key)
    }
}
