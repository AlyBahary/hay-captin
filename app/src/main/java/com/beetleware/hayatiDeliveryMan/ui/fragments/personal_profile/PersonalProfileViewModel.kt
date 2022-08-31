package com.beetleware.hayatiDeliveryMan.ui.fragments.personal_profile

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.beetleware.hayatiDeliveryMan.data.network.model.ApiResponse
import com.beetleware.hayatiDeliveryMan.data.network.model.GeneralResponse
import com.beetleware.hayatiDeliveryMan.data.network.model.ProfileResponse
import com.beetleware.hayatiDeliveryMan.ui.base.BaseViewModel
import okhttp3.MultipartBody

class PersonalProfileViewModel (app:Application): BaseViewModel(app) {

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

    fun uploadChatImage(image:  MultipartBody.Part?): MutableLiveData<ApiResponse<Any>> {
        isLoading.value = true
        val apiResponse = appRepositoryHelper.updateProfileImage(image)
        errorResponse.removeSource(apiResponse)
        errorResponse.addSource(apiResponse) {
            isLoading.value = false
            errorResponse.value = it
        }
        return apiResponse
    }

    fun updateProfile(firstName:String,lastName:String,email:String,address:String,phone:String): MutableLiveData<ApiResponse<GeneralResponse<Any>>> {
        isLoading.value = true
        val apiResponse = appRepositoryHelper.updateProfile(firstName, lastName, email, address, phone)
        errorResponse.removeSource(apiResponse)
        errorResponse.addSource(apiResponse) {
            isLoading.value = false
            errorResponse.value = it
        }
        return apiResponse
    }
}