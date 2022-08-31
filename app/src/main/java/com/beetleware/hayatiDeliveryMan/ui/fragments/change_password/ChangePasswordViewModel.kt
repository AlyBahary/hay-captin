package com.beetleware.hayatiDeliveryMan.ui.fragments.change_password

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.beetleware.hayatiDeliveryMan.data.network.model.ApiResponse
import com.beetleware.hayatiDeliveryMan.data.network.model.GeneralResponse
import com.beetleware.hayatiDeliveryMan.ui.base.BaseViewModel


class ChangePasswordViewModel (app:Application): BaseViewModel(app) {

    fun changePassword(oldPassword:String,newPassword:String,confirmNewPassword:String): MutableLiveData<ApiResponse<GeneralResponse<Any>>> {
        isLoading.value = true
        val apiResponse = appRepositoryHelper.updatePassword(oldPassword,newPassword,confirmNewPassword)
        errorResponse.removeSource(apiResponse)
        errorResponse.addSource(apiResponse) {
            isLoading.value = false
            errorResponse.value = it
        }
        return apiResponse
    }

}