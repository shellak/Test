package com.shell.weatheralerts.presentation.screens.weatheralerts.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.shell.weatheralerts.R
import com.shell.weatheralerts.core.App
import com.shell.weatheralerts.databinding.FragmentWheaterAlertsBinding
import com.shell.weatheralerts.presentation.screens.weatheralerts.adapter.WeatherAlertAdapter
import com.shell.weatheralerts.presentation.screens.weatheralerts.viewmodel.WeatherAlertsViewModel
import com.shell.weatheralerts.presentation.screens.weatheralerts.viewmodel.WeatherAlertsViewModel.*
import com.shell.weatheralerts.presentation.screens.weatheralerts.viewmodel.WeatherAlertsViewModelFactory
import com.shell.weatheralerts.utils.extensions.notify
import dagger.Lazy
import dev.chrisbanes.insetter.applyInsetter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


class WeatherAlertsFragment : Fragment(R.layout.fragment_wheater_alerts) {

    companion object {
        fun newInstance(): WeatherAlertsFragment = WeatherAlertsFragment()
    }

    private lateinit var weatherAlertAdapter: WeatherAlertAdapter

    private val binding by viewBinding(FragmentWheaterAlertsBinding::bind)

    @Inject
    lateinit var factory: Lazy<WeatherAlertsViewModelFactory>

    private val viewModel by viewModels<WeatherAlertsViewModel> { factory.get() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        observeState()
        observeEvents()
    }

    private fun initViews() {
        with(binding) {
            root.applyInsetter {
                type(navigationBars = true, statusBars = true) {
                    padding()
                }
            }

            weatherAlertAdapter = WeatherAlertAdapter(loadWeatherAlertImage = viewModel::loadWeatherAlertImage)
            with(alerts) {
                itemAnimator = null
                adapter = weatherAlertAdapter
            }

            root.applyInsetter {
                type(navigationBars = true, statusBars = true) {
                    padding()
                }
            }
        }
    }

    private fun observeState() {
        viewModel.weatherAlerts
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { alerts ->
                weatherAlertAdapter.submitList(alerts)
            }
            .launchIn(lifecycleScope)
    }

    private fun observeEvents() {
        viewModel.eventFlow
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { event ->
                when (event) {
                    Event.ShowProgress -> {
                        with(binding) {
                            progress.isVisible = true
                        }
                    }

                    Event.HideProgress -> {
                        with(binding) {
                            progress.isVisible = false
                        }
                    }

                    is Event.ShowMessage -> {
                        notify(event.message)
                    }
                }
            }
            .launchIn(lifecycleScope)
    }
}