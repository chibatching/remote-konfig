package com.chibatching.remotekonfig

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


class RemoteKonfigBooleanDelegate internal constructor(private val key: String, default: Boolean)
    : ReadOnlyProperty<KonfigModel, Boolean>, RemoteKonfigDelegate<Boolean>(key, default) {

    override fun getValue(thisRef: KonfigModel, property: KProperty<*>): Boolean {
        return FirebaseRemoteConfig.getInstance().getBoolean(key)
    }
}
