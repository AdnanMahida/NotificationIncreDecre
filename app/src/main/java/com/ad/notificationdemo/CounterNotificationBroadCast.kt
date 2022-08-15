package com.ad.notificationdemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.ad.notificationdemo.CounterNotificationService.Companion.COUNTER
import com.ad.notificationdemo.CounterNotificationService.Companion.COUNTER_TYPE
import com.ad.notificationdemo.CounterNotificationService.Companion.DECREMENT_COUNTER
import com.ad.notificationdemo.CounterNotificationService.Companion.UPDATE_COUNTER_BROADCAST


class CounterNotificationBroadCast : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val service = CounterNotificationService(context = context)

        val counterType = intent?.getStringExtra(COUNTER_TYPE)

        val currentValue = if (counterType == DECREMENT_COUNTER)
            --Counter.value
        else
            ++Counter.value

        service.showNotification(currentValue)
        val i = Intent(UPDATE_COUNTER_BROADCAST)

        i.putExtra(COUNTER, currentValue.toString())

        context.sendBroadcast(i)
    }
}