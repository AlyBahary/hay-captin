package com.beetleware.hayatiDeliveryMan.data.network.paging

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MediatorLiveData
import androidx.paging.DataSource
import com.beetleware.hayatiDeliveryMan.data.network.ApiService
import com.beetleware.hayatiDeliveryMan.data.network.model.ApiResponse
import com.beetleware.hayatiDeliveryMan.data.network.model.OrderResponseModel
import okhttp3.ResponseBody

class SubscriptionOrderDataSourceFactory(
    val apiService: ApiService
) : DataSource.Factory<Int, OrderResponseModel.Subscription.Data>(){

    var notificationPagingErrorResponse = MediatorLiveData<ApiResponse<ResponseBody>>()
    override fun create(): DataSource<Int, OrderResponseModel.Subscription.Data> {
        val notificationDataSource = SubscriptionOrderDataSource(apiService)
        Handler(Looper.getMainLooper()).post {
            notificationPagingErrorResponse.addSource(notificationDataSource.notificationPagingErrorResponse){
                notificationPagingErrorResponse.postValue(it)
            }
        }
        return notificationDataSource
    }

}