package com.chibatching.remotekonfig


interface KonfigModel

fun KonfigModel.konfig(key: String, default: Long): RemoteKonfigLongDelegate {
    return RemoteKonfigLongDelegate(key, default)
}

fun KonfigModel.konfig(key: String, default: Int): RemoteKonfigIntDelegate {
    return RemoteKonfigIntDelegate(key, default)
}

fun KonfigModel.konfig(key: String, default: String): RemoteKonfigStringDelegate {
    return RemoteKonfigStringDelegate(key, default)
}

fun KonfigModel.konfig(key: String, default: Double): RemoteKonfigDoubleDelegate {
    return RemoteKonfigDoubleDelegate(key, default)
}

fun KonfigModel.konfig(key: String, default: Boolean): RemoteKonfigBooleanDelegate {
    return RemoteKonfigBooleanDelegate(key, default)
}
