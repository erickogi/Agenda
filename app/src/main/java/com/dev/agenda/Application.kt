package com.dev.agenda

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.location.Location
import android.os.Build
import android.os.Handler

import androidx.multidex.MultiDexApplication

class Application : MultiDexApplication() {


    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        applicationHandler = Handler(context.mainLooper)


    }

    companion object {


        val TAG = Application::class.java
                .simpleName
        private val mInstance: Application? = null
        @Volatile
        lateinit var context: Context
        @Volatile
        lateinit var applicationHandler: Handler

        @Volatile
        var isConnected: Boolean = false
        @Volatile
        var application: Application? = null
        var _completeNotificationManager: NotificationManager? = null
    }

}
