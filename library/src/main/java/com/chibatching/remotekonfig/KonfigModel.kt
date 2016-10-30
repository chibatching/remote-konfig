package com.chibatching.remotekonfig


open class KonfigModel {

    companion object {

        fun KonfigModel.konfig(key: String, default: Long): RemoteKonfigLongDelegate {
            RemoteKonfig.setDefault(key, default)
            return RemoteKonfigLongDelegate(key)
        }

        fun KonfigModel.konfig(key: String, default: String): RemoteKonfigStringDelegate {
            RemoteKonfig.setDefault(key, default)
            return RemoteKonfigStringDelegate(key)
        }

        fun KonfigModel.konfig(key: String, default: Double): RemoteKonfigDoubleDelegate {
            RemoteKonfig.setDefault(key, default)
            return RemoteKonfigDoubleDelegate(key)
        }

        fun KonfigModel.konfig(key: String, default: Boolean): RemoteKonfigBooleanDelegate {
            RemoteKonfig.setDefault(key, default)
            return RemoteKonfigBooleanDelegate(key)
        }
    }
}
