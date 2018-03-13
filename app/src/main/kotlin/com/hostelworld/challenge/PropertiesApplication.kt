package com.hostelworld.challenge

import android.app.Application
import com.hostelworld.challenge.di.AppComponent
import com.hostelworld.challenge.di.DaggerAppComponent

/**
 * Properties Application
 */
class PropertiesApplication : Application() {
    var appComponent: AppComponent? = null
        private set

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.create()
    }
}