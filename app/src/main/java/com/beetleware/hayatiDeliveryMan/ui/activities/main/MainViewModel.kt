package com.beetleware.hayatiDeliveryMan.ui.activities.main

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.beetleware.hayatiDeliveryMan.data.network.model.ApiResponse
import com.beetleware.hayatiDeliveryMan.ui.base.BaseViewModel

class MainViewModel (app:Application): BaseViewModel(app) {

    fun updateFirebaseToken(firebaseToken:String,deviceId:String): MutableLiveData<ApiResponse<Any>> {
        isLoading.value = true
        val apiResponse = appRepositoryHelper.updateFireBaseToken(firebaseToken,deviceId)
        errorResponse.removeSource(apiResponse)
        errorResponse.addSource(apiResponse) {
            isLoading.value = false
            errorResponse.value = it
        }
        return apiResponse
    }
}