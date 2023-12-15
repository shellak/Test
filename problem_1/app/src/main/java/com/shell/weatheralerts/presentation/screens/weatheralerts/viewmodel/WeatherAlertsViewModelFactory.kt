package com.shell.weatheralerts.presentation.screens.weatheralerts.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shell.weatheralerts.domain.repository.WeatherAlertRepository
import com.shell.weatheralerts.presentation.screens.weatheralerts.mapper.WeatherAlertUiMapper
import com.shell.weatheralerts.utils.cache.Cache
import javax.inject.Inject


class WeatherAlertsViewModelFactory
@Inject constructor(
    private val cache: Cache,
    private val weatherAlertUiMapper: WeatherAlertUiMapper,
    private val weatherAlertRepository: WeatherAlertRepository,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WeatherAlertsViewModel(
            cache = cache,
            weatherAlertUiMapper = weatherAlertUiMapper,
            weatherAlertRepository = weatherAlertRepository,
        ) as T
    }
}
