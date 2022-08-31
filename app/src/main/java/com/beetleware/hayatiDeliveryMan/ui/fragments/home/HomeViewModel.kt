package com.beetleware.hayatiDeliveryMan.ui.fragments.home

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.beetleware.hayatiDeliveryMan.data.network.model.ApiResponse
import com.beetleware.hayatiDeliveryMan.data.network.model.GeneralResponse
import com.beetleware.hayatiDeliveryMan.data.network.model.HomeRespone
import com.beetleware.hayatiDeliveryMan.ui.base.BaseViewModel

class HomeViewModel (app:Application):BaseViewModel(app) {

    fun home(): MutableLiveData<ApiResponse<GeneralResponse<HomeRespone>>> {
        isLoading.value = true
        val apiResponse = appRepositoryHelper.getHome()
        errorResponse.removeSource(apiResponse)
        errorResponse.addSource(apiResponse) {
            isLoading.value = false
            errorResponse.value = it
        }
        return apiResponse
    }

}