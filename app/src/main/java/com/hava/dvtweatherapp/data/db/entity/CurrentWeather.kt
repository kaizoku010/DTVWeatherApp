//package com.hava.dvtweatherapp.data.db.entity
//
//import androidx.room.*
//import com.google.gson.annotations.SerializedName
//import com.hava.dvtweatherapp.data.db.entity.converters.Convertter
//import com.hava.dvtweatherapp.data.db.entity.converters.ListConverter
//
//
//const val WEATHERCODE_ID = 0
//
//@Entity(tableName = "current_weather")
//data class CurrentWeather(
//    @SerializedName("cloud_cover")
//    val cloudcover: Int,
//    val feelslike: Int,
//    val humidity: Int,
//    @SerializedName("is_day")
//    val isDay: String,
//    @SerializedName("observation_time")
//    val temperature: Double,
//    @SerializedName("weather_code")
//    val weatherCode: Int,
//    @SerializedName("weather_descriptions")
//    @Embedded
//    val weatherDescriptions: List<String>,
//    @SerializedName("weather_icons")
//    @Embedded
//    val weatherIcons: List<String>,
//
//    ) {
//    @PrimaryKey(autoGenerate = false)
//    var id: Int = WEATHERCODE_ID
//}