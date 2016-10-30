package com.chibatching.remotekonfig

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


class RemoteKonfigDoubleDelegate
internal constructor(private val key: String) : ReadOnlyProperty<Any, Double> {
    override fun getValue(thisRef: Any, property: KProperty<*>): Double {
        return FirebaseRemoteConfig.getInstance().getDouble(key)
    }
}
