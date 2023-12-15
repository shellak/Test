package com.shell.weatheralerts.presentation.screens.weatheralerts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shell.weatheralerts.databinding.ItemWeatherAlertBinding
import com.shell.weatheralerts.presentation.screens.weatheralerts.model.WeatherAlertUi
import com.shell.weatheralerts.utils.extensions.dpToPx
import com.shell.weatheralerts.utils.extensions.getCircularProgressDrawable
import com.shell.weatheralerts.utils.extensions.getRoundedBitmapDrawable

private const val CORNER_RADIUS = 8

class WeatherAlertAdapter(
    private val loadWeatherAlertImage: (String) -> Unit
) : ListAdapter<WeatherAlertUi, WeatherAlertAdapter.WeatherAlertViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<WeatherAlertUi>() {
        override fun areItemsTheSame(
            oldItem: WeatherAlertUi,
            newItem: WeatherAlertUi,
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: WeatherAlertUi,
            newItem: WeatherAlertUi,
        ) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherAlertViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemWeatherAlertBinding.inflate(inflater, parent, false)
        return WeatherAlertViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherAlertViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class WeatherAlertViewHolder(
        private val binding: ItemWeatherAlertBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(weatherAlertUi: WeatherAlertUi) {
            with(binding) {
                event.text = weatherAlertUi.event
                effective.text = weatherAlertUi.effective
                ends.text = weatherAlertUi.ends
                duration.text = weatherAlertUi.duration
                senderName.text = weatherAlertUi.senderName
                image.setImageBitmap(weatherAlertUi.image)

                weatherAlertUi.image?.let { bitmap ->
                    image.setImageDrawable(
                        bitmap.getRoundedBitmapDrawable(
                            resources = binding.root.context.resources,
                            radius = CORNER_RADIUS.dpToPx,
                        )
                    )
                } ?: run {
                    image.setImageDrawable(
                        binding.root.context.getCircularProgressDrawable()
                    )
                    loadWeatherAlertImage(weatherAlertUi.id)
                }
            }
        }
    }
}
