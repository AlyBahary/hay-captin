package com.beetleware.hayatiDeliveryMan.ui.fragments.change_password

import com.beetleware.hayatiDeliveryMan.ui.base.BaseView


interface ChangePasswordView : BaseView {

    fun initChangePassword(oldPassword: String, newPassword: String, confirmNewPassword: String)
}