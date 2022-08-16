//package com.hava.dvtweatherapp.data.db
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import com.hava.dvtweatherapp.data.db.entity.CurrentWeather
//
//@Database(
//    entities = [CurrentWeather::class],
//    version = 1
//)
//abstract class ForeCastData :RoomDatabase() {
//    abstract fun currentWeatherDataAccess(): CurrentWeatherDao
//
//    companion object{
//       @Volatile private var instance: ForeCastData? = null //access to all threats
//        private val LOCK = Any() //a dummy obj to make sure we have no two instances
//
//        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
//            //if instnc is not initalized
//            instance?: buildDatbase(context).also { instance = it }
//        }
//
//        private fun buildDatbase(context: Context) =
//            Room.databaseBuilder(context.applicationContext,
//            ForeCastData::class.java, "forecast.db").build()
//
//    }
//}