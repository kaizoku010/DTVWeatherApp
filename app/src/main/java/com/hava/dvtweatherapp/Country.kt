package com.hava.dvtweatherapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import dvtweatherapp.R
import dvtweatherapp.databinding.ActivitySettingsBinding.inflate
import org.json.JSONObject

class Country : AppCompatActivity(), View.OnClickListener {
    private lateinit var newUserLocationInput:TextInputEditText
    private lateinit var  userCurrentLocation : TextView
//    private lateinit var binding: Country


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = Country.inflate(layoutInflater)
//        val view = binding.root
        setContentView(R.layout.activity_setcountry)

        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this)
        val savedWeatherData = sharedPrefs.getString("key",null)
        val button: Button = findViewById(R.id.btn_saveLocation_)
        newUserLocationInput = findViewById(R.id.et_userLocation_input_)
         userCurrentLocation = findViewById(R.id.settings_txt_userLocation_)
        newUserLocationInput = findViewById(R.id.et_userLocation_input_)
        button.setOnClickListener(this)

        try {

            val jsObject = JSONObject(savedWeatherData.toString())
            val sys = jsObject.getJSONObject("sys")
            val address = jsObject.getString("name") + "," + sys.getString("country")
            userCurrentLocation.text = address

        } catch (e: Exception){
            e.printStackTrace()
        }


    }

   fun alert(context: Context) {

        val intent = Intent(context, MainActivity::class.java)
      // intent.putExtra("contry", text)
        context.startActivity(intent)
    }

    private fun saveNewUserLocation(newLocation: String) {
        val sharedPreference = PreferenceManager.getDefaultSharedPreferences(this)
        val ed : SharedPreferences.Editor =  sharedPreference.edit()
        try {
            ed.putString("newLocation", newLocation)
            ed.commit()
        } catch (e: Exception){
            Log.d("prefs Error ", e.toString())
        }
        alert(this)
    }

    override fun onClick(p0: View?) {

        when (p0?.id) {
            R.id.btn_saveLocation_ -> {
                if(newUserLocationInput.text.toString().isEmpty()){
                    Toast.makeText(applicationContext, "please Insert Location!!", Toast.LENGTH_LONG).show()
                } else{
                    saveNewUserLocation(newUserLocationInput.text.toString())
                    Toast.makeText(applicationContext, "Location Saved", Toast.LENGTH_LONG).show()
                }

              //  Toast.makeText(applicationContext, "please Insert Location!!", Toast.LENGTH_LONG).show()

            }
            else -> {
            }
        }

    }

}