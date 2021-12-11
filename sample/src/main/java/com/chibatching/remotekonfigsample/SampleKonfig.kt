package com.chibatching.remotekonfigsample

import com.chibatching.remotekonfig.KonfigModel
import com.chibatching.remotekonfig.konfig


object SampleKonfig : KonfigModel {

    val foo by konfig("foo", 0L)
    val bar by konfig("bar", "Test konfig")
    val double by konfig("double", 0.833)
    val bool by konfig("boolean", false)
}
