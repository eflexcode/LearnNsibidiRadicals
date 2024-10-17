package com.larrex.learnnsibidiradicals.broadcastreceiver

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import java.util.Calendar


class SetAlarmOnBootComplete : BroadcastReceiver() {

    override fun onReceive(context: Context?, p1: Intent?) {

        val intent2 = Intent(context, SendDailyAlarm::class.java)

        var pendingIntent: PendingIntent? = null

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
         pendingIntent =  PendingIntent.getBroadcast(context, 34, intent2, PendingIntent.FLAG_IMMUTABLE)

        }else {
            pendingIntent =
                PendingIntent.getBroadcast(context, 34, intent2, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager


        val calendar = Calendar.getInstance()
        calendar[Calendar.HOUR_OF_DAY] = 7
        calendar[Calendar.MINUTE] = 30

        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )


    }


}