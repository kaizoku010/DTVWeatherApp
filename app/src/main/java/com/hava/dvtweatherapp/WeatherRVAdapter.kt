package com.hava.dvtweatherapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dvtweatherapp.R

class WeatherRVAdapter : RecyclerView.Adapter<WeatherViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_holder, parent, false)

        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        //    holder.forecastTemp.text = dataSet[position]
        val url = ""
        //holder.forecastIc.setImageLevel(0)
        //       holder.forecastDay.text = dataSet[position]
    }

    override fun getItemCount(): Int {
        return 0
    }

}