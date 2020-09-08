package com.boring.lockscreen

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class OnLockReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when {
            intent!!.action.equals(Intent.ACTION_SCREEN_ON) -> {
                Log.e("onReceive", "SCREEN_ON")
                val startLockScreenIntent = Intent(context, LockScreenActivity::class.java)
                startLockScreenIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                val pendingIntent = PendingIntent.getActivity(context, 0, startLockScreenIntent, 0)
                pendingIntent.send()
            }
            intent.action.equals(Intent.ACTION_SCREEN_OFF) -> {
                Log.e("onReceive", "SCREEN_OFF")
            }
            intent.action.equals(Intent.ACTION_BOOT_COMPLETED) -> {
                Log.e("onReceive", "BOOT_COMPLETED")
                val onLockIntent = Intent(context, OnLockService::class.java)
                context!!.startService(onLockIntent)
            }
        }
    }
}