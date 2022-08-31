package com.beetleware.hayatiDeliveryMan.ui.fragments.auth.login

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.beetleware.hayatiDeliveryMan.data.network.model.ApiResponse
import com.beetleware.hayatiDeliveryMan.data.network.model.AuthResponse
import com.beetleware.hayatiDeliveryMan.data.network.model.GeneralResponse
import com.beetleware.hayatiDeliveryMan.ui.base.BaseViewModel


class LoginViewModel (app:Application): BaseViewModel(app) {


    fun login(data:String,password:String,deviceId:String,token:String): MutableLiveData<ApiResponse<GeneralResponse<AuthResponse>>> {
        isLoading.value = true
        val apiResponse = appRepositoryHelper.login(data, password,deviceId,token)
        errorResponse.removeSource(apiResponse)
        errorResponse.addSource(apiResponse) {
            isLoading.value = false
            errorResponse.value = it
        }
        return apiResponse
    }

    fun storeUser(user: AuthResponse) = appRepositoryHelper.storeUser(user)







}