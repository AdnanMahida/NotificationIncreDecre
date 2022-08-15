package com.ad.notificationdemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ad.notificationdemo.CounterNotificationService.Companion.COUNTER
import com.ad.notificationdemo.CounterNotificationService.Companion.UPDATE_COUNTER_BROADCAST


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        register broad cast
        registerReceiver(broadcastReceiver, IntentFilter(UPDATE_COUNTER_BROADCAST))


        val notificationService = CounterNotificationService(applicationContext)

        findViewById<Button>(R.id.btnClick).setOnClickListener {
            notificationService.showNotification(Counter.value)

        }
    }

    private fun updateValue(value: String) {
        val txtValue = findViewById<TextView>(R.id.txtValue)
        txtValue.text = value
    }

    private var broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            val value = intent.extras?.getString(COUNTER)
            updateValue(value.toString())
        }
    }

}