package com.larrex.learnnsibidiradicals.broadcastreceiver

import android.Manifest
import android.app.Activity
import android.app.Notification
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.larrex.learnnsibidiradicals.MainActivity
import com.larrex.learnnsibidiradicals.NsibidiApp
import com.larrex.learnnsibidiradicals.R

class SendDailyAlarm : BroadcastReceiver() {

    override fun onReceive(context: Context?, p1: Intent?) {



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            if(context?.let { ContextCompat.checkSelfPermission(it, Manifest.permission.POST_NOTIFICATIONS) }
                != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(context as Activity,  arrayOf(Manifest.permission.POST_NOTIFICATIONS), 7);
            }

        }

        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val bigText = "Hey this is a reminder to practise your nsibidi radicals to remember practices makes perfect "

        val intent1: Intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 45, intent1,
            PendingIntent.FLAG_IMMUTABLE)
        val builder: Notification.Builder
        if (Build.VERSION.SDK_INT >= 26) {
            builder = Notification.Builder(context, NsibidiApp.notificationChannelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Learn nsibidi today")
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setStyle(
                    Notification.BigTextStyle()
                        .bigText(bigText)
                )
                .setContentText(bigText)
                .addAction(R.mipmap.ic_launcher, "Learn", pendingIntent)
        } else {
            builder = Notification.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Learn nsibidi today")
                .setContentText(bigText)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setStyle(
                    Notification.BigTextStyle()
                        .bigText(bigText)
                )
                .setPriority(Notification.PRIORITY_HIGH)
                .setSound(uri)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder.setColor(Color.parseColor("#00BCA1"))
                builder.addAction(R.mipmap.ic_launcher, "Learn", pendingIntent)
            }
        }
        if (context?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.POST_NOTIFICATIONS
                )
            } != PackageManager.PERMISSION_GRANTED
        ) {

            return
        }
        context.let { NotificationManagerCompat.from(it) }.notify(3, builder.build())
    }
}