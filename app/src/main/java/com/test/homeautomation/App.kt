package com.test.homeautomation

import android.app.Application
import android.content.Context

class App : Application() {

    companion object {
        private lateinit var instance: App

        val context: Context
            get() = instance.applicationContext
    }

    init {
        instance = this
    }
}
