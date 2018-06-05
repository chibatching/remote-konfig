package com.chibatching.remotekonfig

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


class RemoteKonfigIntDelegate internal constructor(private val key: String, default: Int)
    : ReadOnlyProperty<KonfigModel, Int>, RemoteKonfigDelegate<Int>(key, default) {

    override fun getValue(thisRef: KonfigModel, property: KProperty<*>): Int {
        return FirebaseRemoteConfig.getInstance().getLong(key).toInt()
    }
}
