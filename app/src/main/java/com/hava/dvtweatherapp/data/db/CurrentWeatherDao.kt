//package com.hava.dvtweatherapp.data.db
//
//import androidx.lifecycle.LiveData
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import com.hava.dvtweatherapp.data.db.entity.CurrentWeather
//import com.hava.dvtweatherapp.data.db.entity.WEATHERCODE_ID
//import com.hava.dvtweatherapp.data.db.localized.GetTempreature
//
//
//@Dao
//interface CurrentWeatherDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun upsert(entry: CurrentWeather)
//
//
//    @Query(value = "select * from current_weather where id = $WEATHERCODE_ID")
//    fun getTempreture():LiveData<GetTempreature>
//
//}