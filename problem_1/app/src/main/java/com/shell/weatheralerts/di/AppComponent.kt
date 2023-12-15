package com.shell.weatheralerts.di

import com.shell.weatheralerts.di.common.CommonModule
import com.shell.weatheralerts.di.weatheralerts.WeatherAlertsModule
import com.shell.weatheralerts.presentation.screens.weatheralerts.view.WeatherAlertsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CommonModule::class,
        WeatherAlertsModule::class,
    ],
)
interface AppComponent {
    fun inject(fragment: WeatherAlertsFragment)
}
