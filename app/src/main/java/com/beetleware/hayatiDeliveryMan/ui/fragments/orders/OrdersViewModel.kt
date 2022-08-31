package com.beetleware.hayatiDeliveryMan.ui.fragments.orders

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.beetleware.hayatiDeliveryMan.data.network.model.ApiResponse
import com.beetleware.hayatiDeliveryMan.data.network.model.GeneralResponse
import com.beetleware.hayatiDeliveryMan.data.network.model.OrderResponseModel
import com.beetleware.hayatiDeliveryMan.data.network.model.OrderSearchResponseModel
import com.beetleware.hayatiDeliveryMan.ui.base.BaseViewModel

class OrdersViewModel (app:Application):BaseViewModel(app) {

    fun getOrders() = appRepositoryHelper.getOrdersPagination()
    fun getSubscriptionOrders() = appRepositoryHelper.getSubscriptionOrderDataSourceFactory()

    fun getOrdersErrorResponse() = appRepositoryHelper.getOrderPaginationErrorResponse()


    fun orders(): MutableLiveData<ApiResponse<GeneralResponse<OrderResponseModel>>> {
        isLoading.value = true
        val apiResponse = appRepositoryHelper.getOrders()
        errorResponse.removeSource(apiResponse)
        errorResponse.addSource(apiResponse) {
            isLoading.value = false
            errorResponse.value = it
        }
        return apiResponse
    }

    fun searchOrders(number:String,type: String): MutableLiveData<ApiResponse<GeneralResponse<OrderSearchResponseModel>>> {
        isLoading.value = true
        val apiResponse = appRepositoryHelper.searchOrder(number,type)
        errorResponse.removeSource(apiResponse)
        errorResponse.addSource(apiResponse) {
            isLoading.value = false
            errorResponse.value = it
        }
        return apiResponse
    }

    fun filterOrders(status:String,type:String): MutableLiveData<ApiResponse<GeneralResponse<OrderSearchResponseModel>>> {
        isLoading.value = true
        val apiResponse = appRepositoryHelper.filterOrder(status,type)
        errorResponse.removeSource(apiResponse)
        errorResponse.addSource(apiResponse) {
            isLoading.value = false
            errorResponse.value = it
        }
        return apiResponse
    }




    val type = MutableLiveData<String>()
    val typeBoolean = MutableLiveData<Boolean>(false)


}