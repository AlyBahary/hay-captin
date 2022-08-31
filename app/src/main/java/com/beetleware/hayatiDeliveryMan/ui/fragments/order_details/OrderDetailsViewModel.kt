package com.beetleware.hayatiDeliveryMan.ui.fragments.order_details

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.beetleware.hayatiDeliveryMan.data.network.model.ApiResponse
import com.beetleware.hayatiDeliveryMan.data.network.model.GeneralResponse
import com.beetleware.hayatiDeliveryMan.data.network.model.OrderDetailsResponse
import com.beetleware.hayatiDeliveryMan.ui.base.BaseViewModel

class OrderDetailsViewModel (app:Application):BaseViewModel(app) {

    fun getOrderDetails(order:String,type:String): MutableLiveData<ApiResponse<GeneralResponse<OrderDetailsResponse>>> {
        isLoading.value = true
        val apiResponse = appRepositoryHelper.orderDetails(order,type)
        errorResponse.removeSource(apiResponse)
        errorResponse.addSource(apiResponse) {
            isLoading.value = false
            errorResponse.value = it
        }
        return apiResponse
    }

    fun updateOrder(order:String,type:String,status:String): MutableLiveData<ApiResponse<GeneralResponse<OrderDetailsResponse>>> {
        isLoading.value = true
        val apiResponse = appRepositoryHelper.updateOrderStatus(order,type,status)
        errorResponse.removeSource(apiResponse)
        errorResponse.addSource(apiResponse) {
            isLoading.value = false
            errorResponse.value = it
        }
        return apiResponse
    }


    val orderId= MutableLiveData<String>()
}