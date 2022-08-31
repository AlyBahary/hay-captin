package com.beetleware.hayatiDeliveryMan.data

import androidx.lifecycle.MutableLiveData
import com.beetleware.hayatiDeliveryMan.data.network.AppNetworkHelper
import com.beetleware.hayatiDeliveryMan.data.network.model.*
import com.beetleware.hayatiDeliveryMan.data.preferences.AppPreferenceHelper
import okhttp3.MultipartBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepositoryHelper @Inject constructor(
    private val networkHelper: AppNetworkHelper,
    private val preferenceHelper: AppPreferenceHelper
) : RepositoryHelper {

    val isUserLoggedinObservable = MutableLiveData<Boolean>().apply {
        postValue(isUserLoggedIn())
    }

    val receiveNotification = MutableLiveData<Boolean>(false)
    val soundNotification = MutableLiveData<Boolean>(false)

    /** preferences **/
    override fun setUserLanguage(language: Int) = preferenceHelper.setUserLanguage(language)
    override fun getUserLanguage() = preferenceHelper.getUserLanguage()


    val hasNotification by lazy {
        MutableLiveData<Boolean>()
    }


    override fun setTargetScreen(screen: Int) = preferenceHelper.setTargetScreen(screen)
    override fun getTargetScreen() = preferenceHelper.getTargetScreen()

    override fun storeUser(user: AuthResponse) {
        preferenceHelper.storeUser(user)
        isUserLoggedinObservable.postValue(true)
    }


    override fun removeUser() {
        preferenceHelper.removeUser()
        isUserLoggedinObservable.postValue(false)
        hasNotification.postValue(false)
    }

    override fun isUserLoggedIn() = preferenceHelper.isUserLoggedIn()

    override fun setUserId(id: Int) = preferenceHelper.setUserId(id)

    override fun getUserId() = preferenceHelper.getUserId()
    override fun getUserType() = preferenceHelper.getUserType()
    override fun getUserAccessToken() = preferenceHelper.getUserAccessToken()
    override fun setUserAccessToken(token: String) = preferenceHelper.setUserAccessToken(token)

    override fun getUserProfileCompletedState() = preferenceHelper.getUserProfileCompletedState()
    override fun setUserProfileCompletedState(completed: Boolean) =
        preferenceHelper.setUserProfileCompletedState(completed)

    override fun getUserProfileConfirmedState() = preferenceHelper.getUserProfileConfirmedState()
    override fun setUserProfileConfirmedState(confirmed: Int) =
        preferenceHelper.setUserProfileConfirmedState(confirmed)


    /** Networking **/

    ////////////////////////////////////** Auth Part **//////////////////////////////////

    override fun verifyOtp(
        code: String,
        phone: String
    ) = networkHelper.verifyOtp(code, phone)

    override fun login(
        data: String,
        password: String,
        deviceId:String,
        token:String
    ) = networkHelper.login(data,password,deviceId,token)

    override fun logout() = networkHelper.logout()


    override fun forgetPassword(codeId: String,phone:String,type:String) = networkHelper.forgetPassword(codeId, phone, type)


    override fun checkCode(
        codeId: String,
        phone: String,
        code: String
    ) = networkHelper.checkCode(codeId, phone, code)

    override fun sendOtp(
        phone: String,
        countryCode: String,
        type: String
    ) = networkHelper.sendOtp(phone, countryCode, type)


    override fun getHome() = networkHelper.getHome()

    override fun getOrders() = networkHelper.getOrders()

    override fun searchOrder(number: String,type: String) = networkHelper.searchOrder(number,type)

    override fun filterOrder(status: String,type: String) = networkHelper.filterOrder(status,type)

    override fun orderDetails(order: String,type: String) = networkHelper.orderDetails(order,type)

    override fun updateOrderStatus(
        order: String,
        type: String,
        status: String
    ) = networkHelper.updateOrderStatus(order,type, status)

    override fun getProfile() = networkHelper.getProfile()

    override fun getAllNotifications() = networkHelper.getAllNotifications()

    override fun getTermsAndConditions() = networkHelper.getTermsAndConditions()

    override fun updateProfile(
        firstName: String,
        lastName: String,
        email: String,
        address: String,
        phone: String
    ) = networkHelper.updateProfile(firstName, lastName, email, address, phone)

    override fun updateProfileImage(file: MultipartBody.Part?)
            = networkHelper.updateProfileImage(file)

    override fun updatePassword(
        oldPassword: String,
        newPassword: String,
        passwordConfirmation: String
    ) = networkHelper.updatePassword(oldPassword, newPassword, passwordConfirmation)

    override fun updateNotification(
        notificationReceive: Boolean,
        notificationSound: Boolean
    ) = networkHelper.updateNotification(notificationReceive, notificationSound)

    override fun getAllCodes() = networkHelper.getAllCodes()

    override fun changePassword(
        codeId: String,
        phone: String,
        password: String
    ) =networkHelper.changePassword(codeId, phone, password)

    override fun getOrdersPagination() = networkHelper.getOrdersPagination()
    override fun getSubscriptionOrderDataSourceFactory() = networkHelper.getSubscriptionOrderDataSourceFactory()

    override fun getOrderPaginationErrorResponse() = networkHelper.getOrderPaginationErrorResponse()

    override fun updateFireBaseToken(firebaseToken: String,deviceId: String) = networkHelper.updateFireBaseToken(firebaseToken,deviceId)


}