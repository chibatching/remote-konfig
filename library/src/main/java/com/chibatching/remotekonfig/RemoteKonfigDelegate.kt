package com.chibatching.remotekonfig

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


abstract class RemoteKonfigDelegate<out T : Any>
internal constructor(private val key: String, private val default: T) : ReadOnlyProperty<KonfigModel, T> {

    operator fun provideDelegate(thisRef: KonfigModel, property: KProperty<*>): ReadOnlyProperty<KonfigModel, T> {
        RemoteKonfig.register(thisRef.javaClass, key, default)
        return this
    }
}
