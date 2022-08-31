package com.beetleware.hayatiDeliveryMan.ui.fragments.terms_coditions

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.beetleware.hayatiDeliveryMan.data.network.model.ApiResponse
import com.beetleware.hayatiDeliveryMan.data.network.model.GeneralResponse
import com.beetleware.hayatiDeliveryMan.data.network.model.TermsAndConditionsResponse
import com.beetleware.hayatiDeliveryMan.ui.base.BaseViewModel

class TermsAndConditionsViewModel (app:Application): BaseViewModel(app) {

    fun getTermsAndConditions(): MutableLiveData<ApiResponse<GeneralResponse<TermsAndConditionsResponse>>> {
        isLoading.value = true
        val apiResponse = appRepositoryHelper.getTermsAndConditions()
        errorResponse.removeSource(apiResponse)
        errorResponse.addSource(apiResponse) {
            isLoading.value = false
            errorResponse.value = it
        }
        return apiResponse
    }


}