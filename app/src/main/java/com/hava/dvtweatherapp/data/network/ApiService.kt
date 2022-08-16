//package com.hava.dvtweatherapp.data.network
//
//import com.hava.dvtweatherapp.data.db.entity.CurrentWeatherResponse
//import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
//import kotlinx.coroutines.Deferred
//import okhttp3.Interceptor
//import okhttp3.OkHttpClient
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.http.GET
//import retrofit2.http.Query
//
////api key and route
////867b7175075f624befc1c2a477ca5d8e -- waetherdata--Api
////http://api.weatherstack.com/current?access_key=867b7175075f624befc1c2a477ca5d8e&query=New%20York
//
//const val API_URL = "http://api.weatherstack.com/"
//const val API_KEY = "867b7175075f624befc1c2a477ca5d8e"
//
////maybe we can make this service reusable...taking in multiple args from different sources
//
//interface ApiService {
//
//    //specify the route we want to take current or forecast
//@GET("current.json")
////this function gets the current weather from openweather or...
//fun getCurrentWeather(
//        //Q\query specifies the location/city(new york) we want to check/ as seen in the api link above
//    @Query("query")
//    location:String,
////    @Query("lang")
////    countryLanguage:String = "en"
//    ):Deferred<CurrentWeatherResponse>
////we call deferred as await or wait, asynchronously
//
////a companion is a static object
//companion object{
//    //apiService() can be the same as invoking a function
//    operator fun invoke(
//        //dependency injections here
//    connectivityIntercept: InterceptConnectivity
//     ): ApiService {
//        val requstInterceptor = Interceptor{ chain ->
//
//            val url = chain.request()
//                .url()
//                .newBuilder()
//                .addQueryParameter("key", API_KEY)
//                .build()
//
//            val reqst = chain.request()
//                .newBuilder()
//                .url(url)
//                .build()
//      //proceed with reqst
//            return@Interceptor chain.proceed(reqst)
//        }
//
//        //translate api call
//        val okHttpClient = OkHttpClient.Builder()
//            .addInterceptor(requstInterceptor)
//            .addInterceptor(connectivityIntercept)
//            .build()
//
//        return Retrofit.Builder()
//            .client(okHttpClient)
//            .baseUrl(API_URL)
//            .addCallAdapterFactory(CoroutineCallAdapterFactory())
//            .addConverterFactory(GsonConverterFactory.create())//parse gson string into a real Kotlin object
//            .build()
//            .create(ApiService::class.java)
//
//    }
//}
//
//
//}