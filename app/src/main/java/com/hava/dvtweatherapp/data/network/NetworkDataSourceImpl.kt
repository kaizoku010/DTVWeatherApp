//package com.hava.dvtweatherapp.data.network
//
//import android.util.Log
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import com.hava.dvtweatherapp.data.db.entity.CurrentWeatherResponse
//import com.hava.dvtweatherapp.internal.NetworkError
//
//
////downloaded current weather data
////encapsulation and inheritance
//class NetworkDataSourceImpl(
//    private val apiService: ApiService
//    ) : NetworkDataSource {
//
//    //back-up data which is not mutable
//    private  val downloadedMutableData = MutableLiveData<CurrentWeatherResponse>()
//    override val getWeatherResponse: LiveData<CurrentWeatherResponse>
//        get() = downloadedMutableData
//
//    override suspend fun fetchCurrentWeather(location: String) {
//
//            try{
//                val downloadedData = apiService
//                    .getCurrentWeather(location)
//                    .await()
//                downloadedMutableData.postValue(downloadedData)
//            }
//            catch(e: NetworkError) {
//                Log.e("connection", "No internet Connection.", e)
//            }
//    }
//
//}