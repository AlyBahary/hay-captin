package com.beetleware.hayatiDeliveryMan.ui.fragments.auth.forget_one

import android.app.Application
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import com.beetleware.hayatiDeliveryMan.common.extensions.forEach
import com.beetleware.hayatiDeliveryMan.data.network.model.ApiResponse
import com.beetleware.hayatiDeliveryMan.data.network.model.CodesResponse
import com.beetleware.hayatiDeliveryMan.data.network.model.ForgetPasswordResponse
import com.beetleware.hayatiDeliveryMan.data.network.model.GeneralResponse
import com.beetleware.hayatiDeliveryMan.ui.base.BaseViewModel
import com.beetleware.hayatiDeliveryMan.ui.customs.CustomEditText
import com.beetleware.hayatiDeliveryMan.ui.customs.CustomMaterialEditText


class ForgetOneViewModel (app:Application): BaseViewModel(app) {

    var phoneNumber= ""
    var code =""
    val isDataValid by lazy {
        MutableLiveData<Boolean>()
    }

    fun validateInputData(view : View){
        var isInputDataValid = true
        (view.rootView.rootView as ViewGroup).forEach {
            if (it is CustomMaterialEditText && !it.validateContent())
                isInputDataValid = false
            else if (it is CustomEditText && !it.validateContent())
                isInputDataValid = false
        }
        isDataValid.postValue(isInputDataValid)
    }

    fun forgetPassword(code:String,phone:String,type:String): MutableLiveData<ApiResponse<ForgetPasswordResponse>> {
        isLoading.value = true
        val apiResponse = appRepositoryHelper.forgetPassword(code,phone,type)
        errorResponse.removeSource(apiResponse)
        errorResponse.addSource(apiResponse) {
            isLoading.value = false
            errorResponse.value = it
        }
        return apiResponse
    }


    fun getCodes(): MutableLiveData<ApiResponse<GeneralResponse<CodesResponse>>> {
        isLoading.value = true
        val apiResponse = appRepositoryHelper.getAllCodes()
        errorResponse.removeSource(apiResponse)
        errorResponse.addSource(apiResponse) {
            isLoading.value = false
            errorResponse.value = it
        }
        return apiResponse
    }

    fun checkCode(codeId:String,phone:String,code:String): MutableLiveData<ApiResponse<GeneralResponse<Any>>> {
        isLoading.value = true
        val apiResponse = appRepositoryHelper.checkCode(codeId, phone, code)
        errorResponse.removeSource(apiResponse)
        errorResponse.addSource(apiResponse) {
            isLoading.value = false
            errorResponse.value = it
        }
        return apiResponse
    }

    fun changePassword(codeId:String,phone:String,password:String): MutableLiveData<ApiResponse<GeneralResponse<Any>>> {
        isLoading.value = true
        val apiResponse = appRepositoryHelper.changePassword(codeId, phone, password)
        errorResponse.removeSource(apiResponse)
        errorResponse.addSource(apiResponse) {
            isLoading.value = false
            errorResponse.value = it
        }
        return apiResponse
    }

    fun sendOtp(phone:String,code:String,type:String): MutableLiveData<ApiResponse<GeneralResponse<Any>>> {
        isLoading.value = true
        val apiResponse = appRepositoryHelper.sendOtp(phone,code,"0")
        errorResponse.removeSource(apiResponse)
        errorResponse.addSource(apiResponse) {
            isLoading.value = false
            errorResponse.value = it
        }
        return apiResponse
    }

    val resendObserver = MutableLiveData<Boolean>(false)
}