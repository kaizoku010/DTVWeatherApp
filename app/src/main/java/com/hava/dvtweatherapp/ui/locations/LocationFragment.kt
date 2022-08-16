package com.hava.dvtweatherapp.ui.locations

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBar.DISPLAY_HOME_AS_UP
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.hava.dvtweatherapp.MainActivity
import com.hava.dvtweatherapp.ui.OnBoardingActivity
import dvtweatherapp.R
import dvtweatherapp.databinding.FragmentLocationBinding
import org.json.JSONObject

class LocationFragment : Fragment() {

    private var _binding: FragmentLocationBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        val locationViewModel =
            ViewModelProvider(this)[LocationViewModel::class.java]
        _binding = FragmentLocationBinding.inflate(inflater, container, false)
            pullUpSharedPrefs()

        return binding.root
    }
    private fun pullUpSharedPrefs() {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
        val savedWeatherData = sharedPrefs.getString("key",null)
        selectToData(savedWeatherData)
    }
    private fun selectToData(jsObj: String?) {
        try {

            val jsObject = JSONObject(jsObj.toString())
            val sys = jsObject.getJSONObject("sys")
            val address = jsObject.getString("name") + "," + sys.getString("country")

            val userCurrentLocation : TextView = binding.root.findViewById(R.id.settings_txt_userLocation);
            val newUserLocationInput = binding.root.findViewById(R.id.et_userLocation_input) as TextInputEditText
            val btnSaveLocation : Button = binding.root.findViewById(R.id.btn_saveLocation);


            val newUserLocation = newUserLocationInput.text.toString()
            userCurrentLocation.text = address

            //setting location data.
            btnSaveLocation.setOnClickListener{
                if(newUserLocationInput.text.toString().isEmpty()){
                    alert(requireContext())
                } else{
                    saveNewUserLocation(newUserLocationInput.text.toString())
                    Snackbar.make(binding.root, "Location Saved", Snackbar.LENGTH_LONG).show()

                }

            }

        } catch (e: Exception){
            e.printStackTrace()
        }
    }
    fun alert(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
//        intent.putExtra("text", text)
        context.startActivity(intent)
    }

    private fun saveNewUserLocation(newLocation: String) {
        val sharedPreference = PreferenceManager.getDefaultSharedPreferences(context)
        val ed : SharedPreferences.Editor =  sharedPreference.edit()

        try {
            ed.putString("newLocation", newLocation)
            ed.commit()
        } catch (e: Exception){
            Log.d("prefs Error ", e.toString())
        }

        alert(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}