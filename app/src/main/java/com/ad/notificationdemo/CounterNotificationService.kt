package com.ad.notificationdemo

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

class CounterNotificationService(private val context: Context) {
    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showNotification(counterInt: Int) {
        val activityIntent = Intent(context, MainActivity::class.java)
        val activityPendingIntent = PendingIntent.getActivity(
            context,
            1,
            activityIntent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        )
        val incrementIntent = PendingIntent.getBroadcast(
            context,
            2,
            Intent(context, CounterNotificationBroadCast::class.java).also {
                it.putExtra(COUNTER_TYPE, INCREMENT_COUNTER)
            },
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        )
        val decrementIntent = PendingIntent.getBroadcast(
            context,
            3,
            Intent(context, CounterNotificationBroadCast::class.java).also {
                it.putExtra(COUNTER_TYPE, DECREMENT_COUNTER)
            },
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        )

        val notification = NotificationCompat.Builder(context, COUNTER_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_plus_one_24)
            .setContentTitle("Increment counter")
            .setContentText("The count is $counterInt")
            .setContentIntent(activityPendingIntent)
            .addAction(
                R.drawable.ic_baseline_plus_one_24,
                "Increment",
                incrementIntent
            )
            .addAction(
                R.drawable.ic_baseline_exposure_minus_1_24,
                "Decrement",
                decrementIntent
            ).build()

        notificationManager.notify(1, notification)
    }

    companion object {
        const val COUNTER_CHANNEL_ID = "counterChannel"
        const val COUNTER = "counter"
        const val UPDATE_COUNTER_BROADCAST = "updateCounterBroadcast"
        const val COUNTER_TYPE = "counterType"
        const val INCREMENT_COUNTER = "incrementCounter"
        const val DECREMENT_COUNTER = "decrementCounter"
    }
}