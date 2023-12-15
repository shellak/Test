package com.shell.weatheralerts.di.weatheralerts

import com.google.gson.Gson
import com.shell.weatheralerts.data.repository.WeatherAlertRepositoryImpl
import com.shell.weatheralerts.data.service.WeatherAlertService
import com.shell.weatheralerts.domain.repository.WeatherAlertRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val WEATHER_API_URL = "https://api.weather.gov/"

@Module
class WeatherAlertsModule {

    @Singleton
    @Provides
    fun provideWeatherRepository(impl: WeatherAlertRepositoryImpl): WeatherAlertRepository = impl

    @Singleton
    @Provides
    fun provideWeatherAlertService(gson: Gson, okHttpClient: OkHttpClient): WeatherAlertService =
        Retrofit.Builder()
            .baseUrl(WEATHER_API_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(WeatherAlertService::class.java)
}
