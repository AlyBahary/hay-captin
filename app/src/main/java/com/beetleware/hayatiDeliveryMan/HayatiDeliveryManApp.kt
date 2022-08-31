package com.beetleware.hayatiDeliveryMan

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.multidex.MultiDexApplication
import com.beetleware.hayatiDeliveryMan.common.Constants
import com.beetleware.hayatiDeliveryMan.di.component.AppComponent
import com.beetleware.hayatiDeliveryMan.di.component.DaggerAppComponent
import com.beetleware.hayatiDeliveryMan.di.module.AppModule
//com.beetleware.hashiDeliveryMan
class HayatiDeliveryManApp : MultiDexApplication() {

    val component: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
//
    override fun onCreate() {
        super.onCreate()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val itemsNotificationChannel = NotificationChannel(
                Constants.TARGET_SCREEN,
                getString(R.string.notification),
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = getString(R.string.be_connected_description)
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(itemsNotificationChannel)
        }
    }

}