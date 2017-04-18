package com.chibatching.remotekonfigsample

import com.chibatching.remotekonfig.KonfigModel


object SampleKonfig : KonfigModel {

    val hoge by konfig("hoge", 0L)
    val fuga by konfig("fuga", "Test konfig")
    val dddd by konfig("dddd", 0.833)
    val bool by konfig("boolean", false)
}
