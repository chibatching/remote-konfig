package com.chibatching.remotekonfig

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


class RemoteKonfigStringDelegate internal constructor(private val key: String, default: String)
    : ReadOnlyProperty<KonfigModel, String>, RemoteKonfigDelegate<String>(key, default) {

    override fun getValue(thisRef: KonfigModel, property: KProperty<*>): String {
        return FirebaseRemoteConfig.getInstance().getString(key)
    }
}
