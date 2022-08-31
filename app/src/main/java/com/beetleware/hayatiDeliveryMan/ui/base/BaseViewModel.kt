package com.beetleware.hayatiDeliveryMan.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.beetleware.hayatiDeliveryMan.HayatiDeliveryManApp
import com.beetleware.hayatiDeliveryMan.data.AppRepositoryHelper
import com.beetleware.hayatiDeliveryMan.data.network.model.ApiResponse
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
abstract class BaseViewModel(val app: Application) : AndroidViewModel(app) {

    @Inject
    lateinit var appRepositoryHelper: AppRepositoryHelper

    init {
        (app as? HayatiDeliveryManApp)?.component?.inject(this)
    }

    /** livData of boolean value used to handle displaying and hiding of progressbar **/
    var isLoading = MutableLiveData<Boolean>().apply { value = false }

    /**
     * MediatorLiveData, all requests will be added as source to it to handle any error or failure.
     **/
    val errorResponse = MediatorLiveData<ApiResponse<*>>()

    /** liveData of boolean value tha will close the current fragment when set to true**/
    val isUpBtnClicked = MutableLiveData<Boolean>().apply { value = false }

    /**
     * change the value of language that's stored in shared pref.
     */

    fun isLoggedin() = appRepositoryHelper.isUserLoggedIn()
    fun upBtnClicked() = isUpBtnClicked.postValue(true)

    fun getUserType() = appRepositoryHelper.getUserType()


    fun removeUser() {
        appRepositoryHelper.removeUser()
    }

    fun getUserId()= appRepositoryHelper.getUserId()



}
