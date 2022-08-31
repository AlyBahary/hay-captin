package com.beetleware.hayatiDeliveryMan.data.network.paging

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.beetleware.hayatiDeliveryMan.data.network.ApiService
import com.beetleware.hayatiDeliveryMan.data.network.model.ApiResponse
import com.beetleware.hayatiDeliveryMan.data.network.model.GeneralResponse
import com.beetleware.hayatiDeliveryMan.data.network.model.OrderResponseModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderDataSource(
    val apiService: ApiService
) : PageKeyedDataSource<Int,OrderResponseModel.Orders.Data>(){

    val notificationPagingErrorResponse = MutableLiveData<ApiResponse<ResponseBody>>()
    val apiResponse = ApiResponse<ResponseBody>()


    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, OrderResponseModel.Orders.Data>
    )  {
        apiService.getOrders(1).enqueue(object :Callback<GeneralResponse<OrderResponseModel>>{
            override fun onResponse(
                call: Call<GeneralResponse<OrderResponseModel>>,
                response: Response<GeneralResponse<OrderResponseModel>>
            ) {
                apiResponse.responseCode = response.code()
                apiResponse.isResponseSuccessful = response.isSuccessful
                if(response.isSuccessful){
                    callback.onResult(response.body()!!.data!!.orders.data,0)
                }
                else
                    apiResponse.errorBody = response.errorBody()
                notificationPagingErrorResponse.postValue(apiResponse)
            }

            override fun onFailure(
                call: Call<GeneralResponse<OrderResponseModel>>,
                t: Throwable
            ) {
                Log.d("onFailure", "onFailure 2 : ${t.localizedMessage}")

                apiResponse.exception = t
                apiResponse.isCanceled=call.isCanceled
            }
        })
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, OrderResponseModel.Orders.Data>
    ) {
        apiService.getOrders(params.key).enqueue(object :Callback<GeneralResponse<OrderResponseModel>>{
            override fun onResponse(
                call: Call<GeneralResponse<OrderResponseModel>>,
                response: Response<GeneralResponse<OrderResponseModel>>
            ) {
                apiResponse.responseCode = response.code()
                apiResponse.isResponseSuccessful = response.isSuccessful
                if(response.isSuccessful){
                    callback.onResult(response.body()!!.data!!.orders.data,0)
                }
                else
                    apiResponse.errorBody = response.errorBody()

                notificationPagingErrorResponse.value = apiResponse
            }

            override fun onFailure(
                call: Call<GeneralResponse<OrderResponseModel>>,
                t: Throwable
            ) {
                Log.d("onFailure", "onFailure 3 : ${t.localizedMessage}")
                apiResponse.exception = t
                apiResponse.isCanceled = call.isCanceled
                notificationPagingErrorResponse.value = apiResponse

            }

        })
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, OrderResponseModel.Orders.Data>
    ) {
        apiService.getOrders(1).enqueue(object :Callback<GeneralResponse<OrderResponseModel>>{
            override fun onResponse(
                call: Call<GeneralResponse<OrderResponseModel>>,
                response: Response<GeneralResponse<OrderResponseModel>>
            ) {
                apiResponse.responseCode = response.code()
                apiResponse.isResponseSuccessful = response.isSuccessful
                if(response.isSuccessful){
                    callback.onResult(response.body()!!.data!!.orders.data,null,2)
                }
                else
                    apiResponse.errorBody = response.errorBody()
                notificationPagingErrorResponse.postValue(apiResponse)
            }

            override fun onFailure(
                call: Call<GeneralResponse<OrderResponseModel>>,
                t: Throwable
            ) {
                Log.d("onFailure", "onFailure 1 : ${t.localizedMessage}")

                apiResponse.exception = t
                apiResponse.isCanceled=call.isCanceled
            }
        })
    }

}