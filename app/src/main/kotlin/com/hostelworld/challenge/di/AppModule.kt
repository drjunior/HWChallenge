package com.hostelworld.challenge.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Module which provides some required dependencies
 */

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gson = GsonBuilder().create()
        return gson
    }

    //TODO Add more modules like retrofit, sharedprefs, resources, etc
}