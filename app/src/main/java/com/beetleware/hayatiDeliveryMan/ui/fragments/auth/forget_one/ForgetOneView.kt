package com.beetleware.hayatiDeliveryMan.ui.fragments.auth.forget_one

import com.beetleware.hayatiDeliveryMan.ui.base.BaseView

interface ForgetOneView : BaseView {
    fun initCodes()
    fun forgetPassword(codeId: String, phoneNumber: String)
}