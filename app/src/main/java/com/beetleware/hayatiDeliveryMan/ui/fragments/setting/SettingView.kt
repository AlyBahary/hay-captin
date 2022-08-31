package com.beetleware.hayatiDeliveryMan.ui.fragments.setting

import com.beetleware.hayatiDeliveryMan.ui.base.BaseView


interface SettingView : BaseView {
    fun initViews()
    fun updateNotificationSetting(notificationReceive: Boolean, notificationSound: Boolean)
}