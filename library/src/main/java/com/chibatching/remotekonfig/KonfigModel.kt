package com.chibatching.remotekonfig

import java.util.*


open class KonfigModel {

    internal var isRegistered: Boolean = false
    internal var defaultsMap: MutableMap<String, Any>? = null
        get() = if (!isRegistered) {
            field ?: run {
                field = HashMap<String, Any>()
                field
            }
        } else {
            null
        }

    companion object {

        fun KonfigModel.konfig(key: String, default: Long): RemoteKonfigLongDelegate {
            defaultsMap?.put(key, default)
            return RemoteKonfigLongDelegate(key)
        }

        fun KonfigModel.konfig(key: String, default: String): RemoteKonfigStringDelegate {
            defaultsMap?.put(key, default)
            return RemoteKonfigStringDelegate(key)
        }

        fun KonfigModel.konfig(key: String, default: Double): RemoteKonfigDoubleDelegate {
            defaultsMap?.put(key, default)
            return RemoteKonfigDoubleDelegate(key)
        }

        fun KonfigModel.konfig(key: String, default: Boolean): RemoteKonfigBooleanDelegate {
            defaultsMap?.put(key, default)
            return RemoteKonfigBooleanDelegate(key)
        }
    }
}
