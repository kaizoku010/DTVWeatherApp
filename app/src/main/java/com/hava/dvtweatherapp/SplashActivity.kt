package com.hava.dvtweatherapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.preference.PreferenceManager
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.hava.dvtweatherapp.ui.OnBoardingActivity
import dvtweatherapp.R

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acivity_splash)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            @Suppress("DEPRECATION")
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)

            val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this)
            val weatherData = sharedPrefs.getString("key",null)
            val emptyString=""

            if(weatherData != null){
                checkPreviousSession(weatherData)
            } else{
                checkPreviousSession(emptyString)
            }
        }
    }

    private fun openOnBoardingScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, OnBoardingActivity::class.java))
            finish()
        }, 1500)
    }

    private fun openHomeScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 1500)

    }

    private fun checkPreviousSession(savedData: String) {
        //here am checking to see if someone logged..so that we don't start the login all the time
        if (savedData.isEmpty()) {
            //open login screen if none or is userName in the sharedPrefs is empty
            openOnBoardingScreen()
        } else {
            //else load the main activity
            openHomeScreen()
        }


    }
}
