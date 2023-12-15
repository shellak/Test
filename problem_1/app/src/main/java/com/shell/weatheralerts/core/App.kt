package com.shell.weatheralerts.core

import android.app.Application
import com.shell.weatheralerts.di.AppComponent
import com.shell.weatheralerts.di.DaggerAppComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob


class App : Application() {

    lateinit var component: AppComponent
    lateinit var applicationScope: CoroutineScope

    override fun onCreate() {
        super.onCreate()
        initDi()
        initAppScope()
    }

    private fun initDi() {
        component = DaggerAppComponent.create()
    }

    private fun initAppScope() {
        // No need to cancel this scope as it'll be turn down with the process
        applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    }
}
