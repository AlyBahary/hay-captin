package com.beetleware.hayatiDeliveryMan.di.component

import android.content.Context
import android.content.SharedPreferences
import com.beetleware.hayatiDeliveryMan.HayatiDeliveryManApp
import com.beetleware.hayatiDeliveryMan.di.module.AppModule
import com.beetleware.hayatiDeliveryMan.di.module.NetworkModule
import com.beetleware.hayatiDeliveryMan.ui.base.BaseViewModel
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {

    fun app(): HayatiDeliveryManApp
    fun context(): Context
    fun inject(baseViewModel: BaseViewModel)
    fun getSharedPreference(): SharedPreferences

}