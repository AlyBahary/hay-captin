package com.beetleware.hayatiDeliveryMan.ui.fragments.setting

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.beetleware.hayatiDeliveryMan.common.Constants
import com.beetleware.hayatiDeliveryMan.data.network.model.ApiResponse
import com.beetleware.hayatiDeliveryMan.data.network.model.GeneralResponse
import com.beetleware.hayatiDeliveryMan.ui.base.BaseViewModel

class SettingViewModel (app:Application): BaseViewModel(app) {


    fun getLanguage() = appRepositoryHelper.getUserLanguage()

    fun changeLang(lang:Int) {
        if (lang == Constants.ARABIC)
            appRepositoryHelper.setUserLanguage(Constants.ARABIC)
        else
            appRepositoryHelper.setUserLanguage(Constants.ENGLISH)
    }

    fun updateNotification(notificationReceive:Boolean,notificationSound:Boolean): MutableLiveData<ApiResponse<GeneralResponse<Any>>> {
        isLoading.value = true
        val apiResponse = appRepositoryHelper.updateNotification(notificationReceive, notificationSound)
        errorResponse.removeSource(apiResponse)
        errorResponse.addSource(apiResponse) {
            isLoading.value = false
            errorResponse.value = it
        }
        return apiResponse
    }

    fun receiveNotification() = appRepositoryHelper.receiveNotification
    fun soundNotification() = appRepositoryHelper.soundNotification
}