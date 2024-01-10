package com.saurabh.customgallery

import android.app.Application
import android.content.Context

class MyApplication : Application() {
    companion object {
        @JvmStatic
        private var instance: MyApplication? = null

        @JvmStatic
        fun getContext(): Context? {
            return instance
        }
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
    }
}