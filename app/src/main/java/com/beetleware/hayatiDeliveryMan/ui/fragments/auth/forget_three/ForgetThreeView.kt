package com.beetleware.hayatiDeliveryMan.ui.fragments.auth.forget_three

import com.beetleware.hayatiDeliveryMan.ui.base.BaseView


interface ForgetThreeView : BaseView {
     fun changePassword(code: String, phoneNumber: String, password: String)

}