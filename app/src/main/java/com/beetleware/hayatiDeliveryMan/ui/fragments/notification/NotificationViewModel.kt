package com.beetleware.hayatiDeliveryMan.ui.fragments.notification

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.beetleware.hayatiDeliveryMan.data.network.model.ApiResponse
import com.beetleware.hayatiDeliveryMan.data.network.model.GetNotificationsResponse
import com.beetleware.hayatiDeliveryMan.ui.base.BaseViewModel

class NotificationViewModel (app:Application): BaseViewModel(app) {

    fun getNotifications(): MutableLiveData<ApiResponse<GetNotificationsResponse>> {
        isLoading.value = true
        val apiResponse = appRepositoryHelper.getAllNotifications()
        errorResponse.removeSource(apiResponse)
        errorResponse.addSource(apiResponse) {
            isLoading.value = false
            errorResponse.value = it
        }
        return apiResponse
    }

}