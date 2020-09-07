package com.boring.lockscreen

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class RestartReceiver : BroadcastReceiver() {

    companion object {
        val ACTION_RESTART_SERVICE = "RestartReceiver.restart"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent!!.action.equals(ACTION_RESTART_SERVICE)) {
            val serviceIntent = Intent(context, OnLockService::class.java)
            context!!.startService(serviceIntent)
        }
    }
}