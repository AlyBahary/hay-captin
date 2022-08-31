package com.beetleware.hayatiDeliveryMan.ui.fragments.profile

import com.beetleware.hayatiDeliveryMan.ui.base.BaseView

interface ProfileView : BaseView {
    fun initProfile()
    fun openNested(position: Int)
}