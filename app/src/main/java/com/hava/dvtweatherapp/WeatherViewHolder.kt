package com.hava.dvtweatherapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dvtweatherapp.R

class WeatherViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val forecastDay: TextView
    val forecastTemp: TextView
    val forecastIc: ImageView

    init {
        // Define click listener for the WeatherViewHolder's View.
        forecastDay = view.findViewById(R.id.forecast_day)
        forecastIc = view.findViewById(R.id.forecast_ic)
        forecastTemp = view.findViewById(R.id.forecast_temp)
    }
}