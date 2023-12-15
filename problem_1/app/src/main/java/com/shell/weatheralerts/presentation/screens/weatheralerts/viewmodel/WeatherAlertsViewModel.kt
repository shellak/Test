package com.shell.weatheralerts.presentation.screens.weatheralerts.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shell.weatheralerts.core.Constants.RANDOM_PICTURE_URL
import com.shell.weatheralerts.data.service.MESSAGE_TYPE_ALERT
import com.shell.weatheralerts.data.service.STATUS_ACTUAL
import com.shell.weatheralerts.domain.repository.WeatherAlertRepository
import com.shell.weatheralerts.presentation.screens.weatheralerts.mapper.WeatherAlertUiMapper
import com.shell.weatheralerts.presentation.screens.weatheralerts.model.WeatherAlertUi
import com.shell.weatheralerts.utils.cache.Cache
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherAlertsViewModel(
    private val cache: Cache,
    private val weatherAlertUiMapper: WeatherAlertUiMapper,
    private val weatherAlertRepository: WeatherAlertRepository,
) : ViewModel() {

    sealed interface Event {
        data object ShowProgress : Event
        data object HideProgress : Event
        data class ShowMessage(val message: String?) : Event
    }

    private val eventChannel = Channel<Event>(Channel.BUFFERED)
    val eventFlow = eventChannel.receiveAsFlow()

    private val _weatherAlerts = MutableStateFlow<List<WeatherAlertUi>>(emptyList())
    val weatherAlerts: Flow<List<WeatherAlertUi>>
        get() = _weatherAlerts.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        sendEvent(Event.ShowMessage(exception.message))
    }

    init {
        loadWeatherAlerts()
    }

    private fun loadWeatherAlerts() {
        viewModelScope.launch(exceptionHandler) {
            sendEvent(Event.ShowProgress)
            _weatherAlerts.value = withContext(Dispatchers.IO) {
                weatherAlertRepository.getWeatherActiveAlerts(
                    statuses = listOf(STATUS_ACTUAL),
                    messageTypes = listOf(MESSAGE_TYPE_ALERT),
                ).map { weatherAlertUiMapper.map(it, cache.get(it.id)) }
            }
        }.invokeOnCompletion {
            sendEvent(Event.HideProgress)
        }
    }

    fun loadWeatherAlertImage(id: String) {
        viewModelScope.launch(exceptionHandler) {
            withContext(Dispatchers.IO) {
                val bitmap = weatherAlertRepository.getWeatherActiveAlertImage(RANDOM_PICTURE_URL)?.also {
                    cache.put(id, it)
                }

                _weatherAlerts.emit(
                    _weatherAlerts.value.map { alert ->
                        if (alert.id == id) alert.copy(image = bitmap) else alert
                    }
                )
            }
        }
    }

    private fun sendEvent(event: Event) {
        viewModelScope.launch {
            eventChannel.send(event)
        }
    }
}
