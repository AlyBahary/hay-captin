package com.beetleware.hayatiDeliveryMan.common.utils

import com.mikepenz.crossfader.Crossfader
import com.mikepenz.materialdrawer.interfaces.ICrossfader

class CrossfadeWrapper(private val crossfader: Crossfader<*>) : ICrossfader {

    override val isCrossfaded: Boolean
        get() = crossfader.isCrossFaded()

    override fun crossfade() {
        crossfader.crossFade()
    }
}