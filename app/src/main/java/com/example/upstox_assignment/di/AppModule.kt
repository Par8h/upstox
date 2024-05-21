package com.example.upstox_assignment.di

import android.content.Context
import com.example.upstox_assignment.MyApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: MyApp) {
    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application.applicationContext
}
