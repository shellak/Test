package com.shell.weatheralerts.di.common

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.shell.weatheralerts.BuildConfig
import com.shell.weatheralerts.utils.cache.Cache
import com.shell.weatheralerts.utils.cache.CacheImpl
import com.shell.weatheralerts.utils.date.DateUtils
import com.shell.weatheralerts.utils.serialization.LocalDateTimeTypeAdapter
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Singleton

@Module
class CommonModule {

    @Singleton
    @Provides
    fun provideCache(): Cache = CacheImpl()

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        HttpLoggingInterceptor.Level.NONE
                    }
                )
            )
            .build()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        val dateFormat = DateTimeFormatter.ofPattern(DateUtils.DEFAULT_DATE_TIME_PATTERN)
        return GsonBuilder()
            .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeTypeAdapter(dateFormat))
            .create()
    }
}