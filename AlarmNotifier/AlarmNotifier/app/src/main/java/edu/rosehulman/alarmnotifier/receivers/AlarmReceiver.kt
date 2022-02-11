package edu.rosehulman.alarmnotifier.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import edu.rosehulman.alarmnotifier.Constants
import edu.rosehulman.alarmnotifier.utils.NotificationUtils

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        Log.d(Constants.TAG, "Received message")
        NotificationUtils.createAndLaunch(context, intent?.getStringExtra(NotificationUtils.MESSAGE_KEY) ?: "")
    }
}