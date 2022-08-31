package com.beetleware.hayatiDeliveryMan.ui.notification

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.beetleware.hayatiDeliveryMan.R
import com.beetleware.hayatiDeliveryMan.common.Constants
import com.beetleware.hayatiDeliveryMan.common.Constants.TARGET_SCREEN
import com.beetleware.hayatiDeliveryMan.data.AppRepositoryHelper
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import javax.inject.Inject

class FCMService : FirebaseMessagingService() {

    lateinit var intent: Intent

    @Inject
    lateinit var appRepositoryHelper: AppRepositoryHelper

    override fun onMessageReceived(p0: RemoteMessage) {

        p0.data.isNotEmpty().let {
            val notificationId = when (p0.data["notification_type"]) {
                else -> 3
            }
            val channelId = p0.data["notification_type"] ?: Constants.TARGET_SCREEN

            val pendingIntent =
                PendingIntent.getActivity(
                    this,
                    notificationId,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT
                )

            val notification = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                NotificationCompat.Builder(applicationContext, channelId)
                    .setContentTitle(p0.data["title"])
                    .setContentText(p0.data["body"])
                    .setPriority(NotificationManager.IMPORTANCE_HIGH)
                    .setSmallIcon(R.drawable.hyati_logo)
                    .setLargeIcon(
                        BitmapFactory.decodeResource(
                            resources,
                            R.drawable.hyati_logo
                        )
                    )
                    .setColorized(true)
                    .setContentIntent(pendingIntent)
                    .setDefaults(Notification.DEFAULT_VIBRATE)
                    .setDefaults(Notification.DEFAULT_SOUND)
                    .build()
            } else {
                NotificationCompat.Builder(applicationContext, channelId)
                    .setContentTitle(p0.data["title"])
                    .setContentText(p0.data["body"])
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setSmallIcon(R.drawable.hyati_logo)
                    .setLargeIcon(
                        BitmapFactory.decodeResource(
                            resources,
                            R.drawable.hyati_logo
                        )
                    )
                    .setColorized(true)
                    .setContentIntent(pendingIntent)
                    .setDefaults(Notification.DEFAULT_VIBRATE)
                    .setDefaults(Notification.DEFAULT_SOUND)
                    .build()
            }
            with(NotificationManagerCompat.from(this)) {
                notify(notificationId, notification)
            }

        }

    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.d("FCM", "onNewToken: $p0")
    }
}
