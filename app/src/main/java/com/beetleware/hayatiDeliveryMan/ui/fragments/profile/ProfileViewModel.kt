package com.beetleware.hayatiDeliveryMan.ui.fragments.profile

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.beetleware.hayatiDeliveryMan.data.network.model.ApiResponse
import com.beetleware.hayatiDeliveryMan.data.network.model.GeneralResponse
import com.beetleware.hayatiDeliveryMan.data.network.model.ProfileResponse
import com.beetleware.hayatiDeliveryMan.ui.base.BaseViewModel

class ProfileViewModel (app:Application):BaseViewModel(app) {

    fun getProfile(): MutableLiveData<ApiResponse<GeneralResponse<ProfileResponse>>> {
        isLoading.value = true
        val apiResponse = appRepositoryHelper.getProfile()
        errorResponse.removeSource(apiResponse)
        errorResponse.addSource(apiResponse) {
            isLoading.value = false
            errorResponse.value = it
        }
        return apiResponse
    }

    fun signout(): MutableLiveData<ApiResponse<GeneralResponse<Any>>> {
        isLoading.value = true
        val apiResponse = appRepositoryHelper.logout()
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