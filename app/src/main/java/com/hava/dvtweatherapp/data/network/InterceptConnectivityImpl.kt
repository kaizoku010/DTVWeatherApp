//package com.hava.dvtweatherapp.data.network
//
//import android.content.Context
//import android.net.ConnectivityManager
//import com.hava.dvtweatherapp.internal.NetworkError
//import okhttp3.Interceptor
//import okhttp3.Response
//
//class InterceptConnectivityImpl(context: Context) : InterceptConnectivity {
//
//    private val appContext = context.applicationContext
//
//    override fun intercept(chain: Interceptor.Chain): Response {
//            if (!isConnected())
//                //custom error/exceptions
//                throw NetworkError()
//        return chain.proceed(chain.request())
//    }
//
//    //helper function to check if there's a data connection
//    private fun isConnected(): Boolean {
//        val connectionManager = appContext
//            .getSystemService(Context.CONNECTIVITY_SERVICE)
//        as ConnectivityManager
//        val connectionStatus = connectionManager.activeNetworkInfo
//        return connectionStatus != null && connectionStatus.isConnected
//    }
//}