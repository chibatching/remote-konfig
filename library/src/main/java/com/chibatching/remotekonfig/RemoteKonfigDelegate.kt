package com.chibatching.remotekonfig

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


abstract class RemoteKonfigDelegate<out T : Any>
internal constructor(private val key: String, private val default: T) : ReadOnlyProperty<Any, T> {

    operator fun provideDelegate(thisRef: Any, property: KProperty<*>): ReadOnlyProperty<Any, T> {
        RemoteKonfig.register(key, default)
        return this
    }
}
