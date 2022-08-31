package com.beetleware.hayatiDeliveryMan.data.network

import com.beetleware.hayatiDeliveryMan.data.network.model.*
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    // Lookups
    @GET("otp/allCodes")
    fun getAllCodes(): Call <GeneralResponse<CodesResponse>>

    @POST("otp/verifyOtp")
    fun verifyOtp(
        @Body body: Map<String, String>
    ) : Call<GeneralResponse<Any>>

    @POST("delivery/auth/login")
    fun login(
        @Body body: Map<String, String>
    ) : Call<GeneralResponse<AuthResponse>>

    @POST("delivery/auth/logout")
    fun logout() : Call<GeneralResponse<Any>>

    @POST("delivery/auth/forget-password")
    fun forgetPassword(
        @Body body: Map<String, String>
    ) : Call<ForgetPasswordResponse>

    @POST("delivery/auth/check-code")
    fun checkCode(
        @Body body: Map<String, String>
    ) : Call<GeneralResponse<Any>>

    @POST("otp/sendOtp")
    fun sendOtp(
        @Body body: Map<String, String>
    ) : Call<GeneralResponse<Any>>

    // Home //
    @GET("delivery/home")
    fun home() : Call<GeneralResponse<HomeRespone>>


    // Order //
    @GET("delivery/orders")
    fun getOrders(
        @Query("page") page:Int
    ) : Call<GeneralResponse<OrderResponseModel>>

    //Order Search //
    @FormUrlEncoded
    @POST("delivery/orders/search")
    fun searchOrder(
        @Query("number") number:String,
        @Field("type") type:String
    ):Call<GeneralResponse<OrderSearchResponseModel>>

    // Filter Order //
    @FormUrlEncoded
    @POST("delivery/orders/filter")
    fun filter(
        @Field("status") status:String,
        @Field("type") type:String
    ):Call<GeneralResponse<OrderSearchResponseModel>>

    @FormUrlEncoded
    @POST("delivery/order-details")
    fun orderDetails(
        @Field("order") order:String,
        @Field("type") type:String
    ):Call<GeneralResponse<OrderDetailsResponse>>

    @FormUrlEncoded
    @POST("delivery/orders/update")
    fun updateOrderStatus(
        @Field("test") order:String,
        @FieldMap body: Map<String, String>
    ) : Call<GeneralResponse<OrderDetailsResponse>>

    //profile
    @GET("delivery/profile")
    fun getProfile(): Call<GeneralResponse<ProfileResponse>>

    //old
    @GET("delivery/notifications")
    fun getAllNotifications():Call<GetNotificationsResponse>

    @POST("delivery/setting/notification-setting")
    fun updateNotification(
        @Body body: Map<String, Boolean>
    ):Call<GeneralResponse<Any>>


    @GET("delivery/setting/terms-conditions")
    fun getTermsAndConditions():Call<GeneralResponse<TermsAndConditionsResponse>>


    @POST("delivery/profile/update")
    fun updateProfile(
        @Body body: Map<String, String>
    ):Call<GeneralResponse<Any>>


    @Multipart
    @POST("delivery/profile/uploadImage")
    fun updateProfileImage(
        @Part file: MultipartBody.Part?
    ):Call<Any>
    
    @POST("delivery/profile/update-password")
    fun updatePassword(
        @Body body: Map<String, String>
    ):Call<GeneralResponse<Any>>

    @POST("delivery/auth/change-password")
    fun changePassword(
        @Body body: Map<String, String>
    ) : Call<GeneralResponse<Any>>

    @POST("delivery/auth/update-firebase-token")
    fun updateFirebaseToken(
        @Body body: Map<String, String>
    ) : Call<Any>

}

