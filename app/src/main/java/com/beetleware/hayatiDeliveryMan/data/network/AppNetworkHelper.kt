package com.beetleware.hayatiDeliveryMan.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.beetleware.hayatiDeliveryMan.common.extensions.getResponse
import com.beetleware.hayatiDeliveryMan.data.network.model.*
import com.beetleware.hayatiDeliveryMan.data.network.paging.OrderDataSourceFactory
import com.beetleware.hayatiDeliveryMan.data.network.paging.SubscriptionOrderDataSourceFactory
import okhttp3.MultipartBody
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppNetworkHelper @Inject constructor(private val apiService: ApiService) : NetworkHelper {

    private lateinit var orderDataSourceFactory: OrderDataSourceFactory
    private lateinit var subscriptionOrderDataSourceFactory: SubscriptionOrderDataSourceFactory

    @Inject
    lateinit var pagedListConfig:PagedList.Config

    override fun verifyOtp(
        code: String,
        phone: String
    ): MutableLiveData<ApiResponse<GeneralResponse<Any>>> {
        val body = hashMapOf(
            "code" to code,
            "phone" to phone
        )
        return apiService.verifyOtp(body).getResponse()
    }

    override fun login(
        data: String,
        password: String,
        deviceId: String,
        token:String
    ): MutableLiveData<ApiResponse<GeneralResponse<AuthResponse>>> {
        val body = hashMapOf(
            "data" to data,
            "password" to password,
            "device_id" to deviceId,
            "firebase_token" to token
            )
        return apiService.login(body).getResponse()
    }

    override fun logout(): MutableLiveData<ApiResponse<GeneralResponse<Any>>> {
        return apiService.logout().getResponse()
    }

    override fun forgetPassword(codeId: String ,phone:String,type: String): MutableLiveData<ApiResponse<ForgetPasswordResponse>> {
        val body = hashMapOf(
            "code_id" to codeId,
            "phone" to phone,
            "type" to type
        )
        return apiService.forgetPassword(body).getResponse()
    }


    override fun checkCode(
        codeId: String,
        phone: String,
        code: String
    ): MutableLiveData<ApiResponse<GeneralResponse<Any>>> {
        val body = hashMapOf(
            "code_id" to codeId,
            "phone" to phone,
            "code" to code
        )
        return apiService.checkCode(body).getResponse()
    }


    override fun sendOtp(
        phone: String,
        countryCode: String,
        type:String
    ): MutableLiveData<ApiResponse<GeneralResponse<Any>>> {
        val body = hashMapOf(
            "phone" to phone,
            "country_code" to countryCode,
            "type" to type

        )
        return apiService.sendOtp(body).getResponse()
    }
    override fun getHome(): MutableLiveData<ApiResponse<GeneralResponse<HomeRespone>>> {
        return apiService.home().getResponse()
    }

    override fun getOrders(): MutableLiveData<ApiResponse<GeneralResponse<OrderResponseModel>>> {
        return apiService.getOrders(1).getResponse()
    }

    override fun searchOrder(number:String,type: String): MutableLiveData<ApiResponse<GeneralResponse<OrderSearchResponseModel>>> {
        return apiService.searchOrder(number,type).getResponse()
    }

    override fun filterOrder(status: String,type: String): MutableLiveData<ApiResponse<GeneralResponse<OrderSearchResponseModel>>> {
        return apiService.filter(status, type).getResponse()
    }

    override fun orderDetails(order: String,type: String): MutableLiveData<ApiResponse<GeneralResponse<OrderDetailsResponse>>> {
        return apiService.orderDetails(order,type).getResponse()
    }

    override fun updateOrderStatus(
        order: String,
        type: String,
        status: String
    ): MutableLiveData<ApiResponse<GeneralResponse<OrderDetailsResponse>>> {
        val body = hashMapOf(
            "status" to status,
            "order" to order,
            "type" to type
        )
        return apiService.updateOrderStatus(order,body).getResponse()
    }

    override fun getProfile(): MutableLiveData<ApiResponse<GeneralResponse<ProfileResponse>>> {
        return apiService.getProfile().getResponse()
    }

    override fun getAllNotifications(): MutableLiveData<ApiResponse<GetNotificationsResponse>> {
        return apiService.getAllNotifications().getResponse()
    }

    override fun getTermsAndConditions(): MutableLiveData<ApiResponse<GeneralResponse<TermsAndConditionsResponse>>> {
        return apiService.getTermsAndConditions().getResponse()
    }

    override fun updateProfile(
        firstName: String,
        lastName: String,
        email: String,
        address: String,
        phone: String
    ): MutableLiveData<ApiResponse<GeneralResponse<Any>>> {
        val body = hashMapOf(
            "first_name" to firstName,
            "last_name" to lastName,
            "email" to email,
            "address" to address,
            "phone" to phone
        )
        return apiService.updateProfile(body).getResponse()
    }

    override fun updateProfileImage(file: MultipartBody.Part?): MutableLiveData<ApiResponse<Any>> {
        return apiService.updateProfileImage(file).getResponse()
    }

    override fun updatePassword(
        oldPassword: String,
        newPassword: String,
        passwordConfirmation: String
    ): MutableLiveData<ApiResponse<GeneralResponse<Any>>> {
        val body = hashMapOf(
            "oldPassword" to oldPassword,
            "newPassword" to newPassword,
            "password_confirmation" to passwordConfirmation
        )
        return apiService.updatePassword(body).getResponse()
    }

    override fun updateNotification(
        notificationReceive: Boolean,
        notificationSound: Boolean
    ): MutableLiveData<ApiResponse<GeneralResponse<Any>>> {
        val body = hashMapOf(
            "notification_receive" to notificationReceive,
            "notification_sound" to notificationSound
        )
        return apiService.updateNotification(body).getResponse()
    }

    override fun getAllCodes(): MutableLiveData<ApiResponse<GeneralResponse<CodesResponse>>> {
        return apiService.getAllCodes().getResponse()
    }

    override fun changePassword(
        codeId: String,
        phone: String,
        password: String
    ): MutableLiveData<ApiResponse<GeneralResponse<Any>>> {
        val body = hashMapOf(
            "code_id" to codeId,
            "phone" to phone,
            "password" to password
        )
        return apiService.changePassword(body).getResponse()
    }

    override fun getOrdersPagination(): LiveData<PagedList<OrderResponseModel.Orders.Data>> {
        orderDataSourceFactory = OrderDataSourceFactory(
            apiService
        )

        return LivePagedListBuilder(orderDataSourceFactory,pagedListConfig)
            .setFetchExecutor(Executors.newFixedThreadPool(5)).build()
    }
    override fun getSubscriptionOrderDataSourceFactory(): LiveData<PagedList<OrderResponseModel.Subscription.Data>> {
        subscriptionOrderDataSourceFactory = SubscriptionOrderDataSourceFactory(
            apiService
        )

        return LivePagedListBuilder(subscriptionOrderDataSourceFactory, pagedListConfig)
            .setFetchExecutor(Executors.newFixedThreadPool(5)).build()
    }

    override fun getOrderPaginationErrorResponse() = orderDataSourceFactory.notificationPagingErrorResponse

    override fun updateFireBaseToken(firebaseToken: String,deviceId: String): MutableLiveData<ApiResponse<Any>> {
        val body = hashMapOf(
            "firebase_token" to firebaseToken,
            "device_id" to deviceId
        )
        return apiService.updateFirebaseToken(body).getResponse()
    }

}


