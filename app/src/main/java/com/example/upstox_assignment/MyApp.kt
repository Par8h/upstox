package com.example.upstox_assignment


import android.app.Application
import com.example.upstox_assignment.di.AppModule
import com.example.upstox_assignment.di.RepositoryModule
import com.example.upstox_assignment.di.UseCaseModule
import com.example.upstox_assignment.di.ViewModelModule
import com.example.upstox_assignment.presentation.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RepositoryModule::class, UseCaseModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(application: MyApp)
    fun inject(activity: MainActivity)
}

class MyApp : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

        appComponent.inject(this)
    }
}
