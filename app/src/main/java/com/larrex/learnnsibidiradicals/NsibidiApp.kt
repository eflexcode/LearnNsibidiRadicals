package com.larrex.learnnsibidiradicals

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class NsibidiApp : Application() {


    var notificationChannelName: String = "Daily nsibidi reminder  notification"



    companion object{
        var notificationChannelId: String = "ReminderChannel1"

    }

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                notificationChannelId,
               notificationChannelName,
                NotificationManager.IMPORTANCE_HIGH
            )

            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

}