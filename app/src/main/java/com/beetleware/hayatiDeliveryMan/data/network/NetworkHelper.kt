package com.beetleware.hayatiDeliveryMan.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.beetleware.hayatiDeliveryMan.data.network.model.*
import okhttp3.MultipartBody
import okhttp3.ResponseBody


interface NetworkHelper {

    fun verifyOtp(
        code:String,
        phone:String
    ): MutableLiveData<ApiResponse<GeneralResponse<Any>>>

    fun login(
        data:String,
        password: String,
        deviceId:String,
        token:String
    ): MutableLiveData<ApiResponse<GeneralResponse<AuthResponse>>>

    fun logout() : MutableLiveData<ApiResponse<GeneralResponse<Any>>>

    fun forgetPassword(codeId:String,phone:String,type:String):MutableLiveData<ApiResponse<ForgetPasswordResponse>>

    fun checkCode(codeId:String,phone:String,code:String) : MutableLiveData<ApiResponse<GeneralResponse<Any>>>

    fun sendOtp(phone:String,countryCode:String,type:String): MutableLiveData<ApiResponse<GeneralResponse<Any>>>

    fun getHome():MutableLiveData<ApiResponse<GeneralResponse<HomeRespone>>>

    fun getOrders():MutableLiveData<ApiResponse<GeneralResponse<OrderResponseModel>>>

    fun searchOrder(number:String,type: String):MutableLiveData<ApiResponse<GeneralResponse<OrderSearchResponseModel>>>

    fun filterOrder(status:String,type:String):MutableLiveData<ApiResponse<GeneralResponse<OrderSearchResponseModel>>>

    fun orderDetails(order:String,type:String):MutableLiveData<ApiResponse<GeneralResponse<OrderDetailsResponse>>>

    fun updateOrderStatus(order:String,type:String,status:String):MutableLiveData<ApiResponse<GeneralResponse<OrderDetailsResponse>>>

    fun getProfile():MutableLiveData<ApiResponse<GeneralResponse<ProfileResponse>>>

    fun getAllNotifications():MutableLiveData<ApiResponse<GetNotificationsResponse>>

    fun getTermsAndConditions():MutableLiveData<ApiResponse<GeneralResponse<TermsAndConditionsResponse>>>

    fun updateProfile(firstName:String,lastName:String,email:String,address:String,phone:String):MutableLiveData<ApiResponse<GeneralResponse<Any>>>

    fun updateProfileImage(
        file: MultipartBody.Part?
    ):MutableLiveData<ApiResponse<Any>>

    fun updatePassword(oldPassword:String,newPassword:String,passwordConfirmation:String):MutableLiveData<ApiResponse<GeneralResponse<Any>>>

    fun updateNotification(notificationReceive:Boolean,notificationSound:Boolean):MutableLiveData<ApiResponse<GeneralResponse<Any>>>

    fun getAllCodes():MutableLiveData<ApiResponse<GeneralResponse<CodesResponse>>>

    fun changePassword(codeId:String,phone:String,password:String) : MutableLiveData<ApiResponse<GeneralResponse<Any>>>

    fun getOrdersPagination(): LiveData<PagedList<OrderResponseModel.Orders.Data>>

    fun getSubscriptionOrderDataSourceFactory(): LiveData<PagedList<OrderResponseModel.Subscription.Data>>

    fun getOrderPaginationErrorResponse(): MediatorLiveData<ApiResponse<ResponseBody>>

    fun updateFireBaseToken(firebaseToken:String,deviceId: String) : MutableLiveData<ApiResponse<Any>>

}