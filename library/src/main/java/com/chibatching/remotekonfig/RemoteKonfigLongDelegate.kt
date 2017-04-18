package com.chibatching.remotekonfig

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


class RemoteKonfigLongDelegate internal constructor(private val key: String, default: Long)
    : ReadOnlyProperty<Any, Long>, RemoteKonfigDelegate<Long>(key, default) {

    override fun getValue(thisRef: Any, property: KProperty<*>): Long {
        return FirebaseRemoteConfig.getInstance().getLong(key)
    }
}
