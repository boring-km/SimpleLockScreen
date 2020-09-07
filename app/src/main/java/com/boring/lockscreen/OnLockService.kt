package com.boring.lockscreen

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import android.os.SystemClock

class OnLockService : Service() {

    private lateinit var receiver:BroadcastReceiver

    override fun onCreate() {
        super.onCreate()
        registerRestartAlarm(true)
        val filter = IntentFilter(Intent.ACTION_SCREEN_ON)
        filter.addAction(Intent.ACTION_SCREEN_OFF)
        receiver = OnLockReceiver()
        registerReceiver(receiver, filter)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent == null) {
            val filter = IntentFilter(Intent.ACTION_SCREEN_ON)
            filter.addAction(Intent.ACTION_SCREEN_OFF)
            receiver = OnLockReceiver()
            registerReceiver(receiver, filter)
            startForeground(1, Notification())
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        registerRestartAlarm(false)
        unregisterReceiver(receiver)
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    @SuppressLint("ShortAlarm")
    fun registerRestartAlarm(isOn: Boolean) {
        val intent = Intent(this@OnLockService, OnLockReceiver::class.java)
        intent.setAction(RestartReceiver.ACTION_RESTART_SERVICE)
        val sender = PendingIntent.getBroadcast(applicationContext, 0, intent, 0)
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        if (isOn)
            alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + 1000, 10000, sender)
        else
            alarmManager.cancel(sender)
    }
}