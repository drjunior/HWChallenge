package com.hostelworld.challenge.di

import com.hostelworld.challenge.ui.properties.PropertiesListActivity
import dagger.Component
import javax.inject.Singleton


/**
 * AppComponent providing inject() methods for presenters.
 */

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject(propertyListActivity: PropertiesListActivity)
}