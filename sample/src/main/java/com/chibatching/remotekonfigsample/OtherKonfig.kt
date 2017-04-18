package com.chibatching.remotekonfigsample

import com.chibatching.remotekonfig.KonfigModel


object OtherKonfig : KonfigModel {

    val secondModel by konfig("second", 23)
    val secondHoge by konfig("second_hoge", "ddd")
}
