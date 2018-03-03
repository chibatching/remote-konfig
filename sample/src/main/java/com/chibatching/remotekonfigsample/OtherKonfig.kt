package com.chibatching.remotekonfigsample

import com.chibatching.remotekonfig.KonfigModel
import com.chibatching.remotekonfig.konfig


object OtherKonfig : KonfigModel {

    val secondModel by konfig("second", 23)
    val secondHoge by konfig("second_hoge", "ddd")
}
