package com.hava.dvtweatherapp.ui.home

import android.app.ProgressDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.AsyncTask
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import dvtweatherapp.R
import dvtweatherapp.databinding.FragmentHomeBinding
import org.json.JSONException
import org.json.JSONObject
import java.net.URL
import java.nio.charset.Charset
import java.text.SimpleDateFormat
import java.util.*


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    var CITY: String = ""
    val API: String = "b5e9097119905248fb332226e7ee229b"

    // This property is only valid between onCreateView and
    // onDestroyView.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        //set new user location / got from the locations tab
        newLocation();
        if (CITY == null) {
            CITY = "kampala"
            Log.e("Default city",CITY.toString())

        }
        //get current weather from openWeather
        GetCurrentWeather().execute()
        return root
    }
    //task to do job
    //might leak
    inner class GetCurrentWeather() : AsyncTask<String, Void, String>() {
      //  var alertDialog:AlertDialog.Builder = null!!
        var background: View = binding.root
        var _string_ ="London"


        @Deprecated("Deprecated in Java")
        override fun doInBackground(vararg p0: String?): String {

            val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
            val savedUserLocation = sharedPrefs.getString("newLocation",null)
            Log.e("New user Loaction", savedUserLocation.toString())
                if(CITY == null){
                    CITY = "kampala"
                } else{

                    CITY = savedUserLocation.toString();
                    Log.e("NewCountry",CITY)

                }

            var response: String?
            try {
            //    https://api.openweathermap.org/data/2.5/weather?q=kampala&appid=b5e9097119905248fb332226e7ee229b
                response =
                    URL("https://api.openweathermap.org/data/2.5/weather?q=$CITY&appid=$API")
                        .readText(Charset.forName("ISO-8859-1"))
            } catch (e: Exception) {
                response = null
                Log.e("saver Response", e.toString())
            }
            return response.toString()
        }

        @Deprecated("Deprecated in Java")
        override fun onPreExecute() {
           super.onPreExecute()
            binding.loader.visibility = View.VISIBLE
        }

        @Deprecated("Deprecated in Java")
        override fun onPostExecute(result: String) {
            super.onPostExecute(result)
            Log.e("api Result", result)

            val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
                val savedWeatherData = sharedPrefs.getString("key",null)

            var jsObject: JSONObject? = null

            try {
                //if (result) from the Api (isEmpty) or null...then let our
                // data be pull from SharedPreferences
                jsObject = if (savedWeatherData != null) {
                    hideProgressBar()
                    JSONObject(savedWeatherData.toString())
                } else {
                    JSONObject(result)
                }
//                if (result.isEmpty() && savedWeatherData!!.isEmpty()){
//                    CITY = "London"
//                }
                val main = jsObject.getJSONObject("main")
                Log.e("main", main.toString())
                val sys = jsObject.getJSONObject("sys")
                val weather = jsObject.getJSONArray("weather").getJSONObject(0)
                val updatedLast: Long = jsObject.getLong("dt")
                val updatedAt = "updated at:" + SimpleDateFormat(
                    "dd/MM/yyyy hh:mm a",
                    Locale.ENGLISH
                ).format(Date(updatedLast * 1000))
                val temprature = main.getString("temp") + "°C"
                val currentWeather = weather?.getString("description")
                val address = jsObject.getString("name") + "," + sys.getString("country")
                val temp = main.getString("temp") + "°C"
                val tempMin = main.getString("temp_min") + "°C"
                val tempMax = main.getString("temp_max") + "°C"
                //save weather data for offline use
                saveWeatherState(jsObject.toString());
                //change background according to current weather
                when (currentWeather) {
                    "sunny" -> {
                        binding.root.background = resources.getDrawable(R.drawable.sunny_dvt);
                    }
                    "rain" -> {
                        binding.root.background = resources.getDrawable(R.drawable.rainny);
                    }
                    "cloudy" -> {
                        binding.root.background = resources.getDrawable(R.drawable.cloudy_dvt);
                    }
                    "overcast clouds" -> {
                        binding.root.background = resources.getDrawable(R.drawable.cloudy_dvt)
                    }
                    "light rain" -> {
                        binding.root.background = resources.getDrawable(R.drawable.rainny);
                    }
                    "broken clouds" -> {
                        binding.root.background = resources.getDrawable(R.drawable.cloudy_dvt);
                    }
                    "drizzle" -> {
                        binding.root.background = resources.getDrawable(R.drawable.rainny);
                    }
                    "sleet" -> {
                        binding.root.background = resources.getDrawable(R.drawable.rainny);
                    }
                    "haze" -> {
                        binding.root.background = resources.getDrawable(R.drawable.cloudy_dvt);
                    }
                    "sand/ dust whirls" -> {
                        binding.root.background = resources.getDrawable(R.drawable.cloudy_dvt);
                    }
                    "fog" -> {
                        binding.root.background = resources.getDrawable(R.drawable.cloudy_dvt);
                    }
                    "sand" -> {
                        binding.root.background = resources.getDrawable(R.drawable.sunny_dvt);
                    }
                    "mist" -> {
                        binding.root.background = resources.getDrawable(R.drawable.cloudy_dvt);
                    }

                }
                binding.dvtDegrees.text = temprature//setting temp/others to a textview
                binding.dvtCodition.text = currentWeather?.uppercase()
                binding.txtMaxTemp.text = tempMax
                binding.txtMinTemp.text = tempMin
                binding.txtCurrentTemp.text = temp
                //hide progressbar if result isNotEmpty
                hideProgressBar()
            } catch (e: JSONException) {
                //hide progressbar if there's no response at all
                hideProgressBar()
//                val alert: AlertDialog =  "hsfhds"
                Snackbar.make(background, "Error Loading Data", Snackbar.LENGTH_INDEFINITE).show()
                Log.d("Error Fetching Api Data", e.toString())
            }
        }

        private fun hideProgressBar() {
            binding.loader.visibility = View.GONE
            binding.screenLoader.visibility = View.GONE
            binding.loaderText.visibility = View.GONE
        }
    }

    private fun showErrorDialog(error: String) {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.apply {
            setTitle("oops!!")
            setMessage(error)
        }.create().show()
    }

    private fun saveWeatherState(weatherState: String) {
        val sharedPreference = PreferenceManager.getDefaultSharedPreferences(context)
        val ed: SharedPreferences.Editor = sharedPreference.edit()

        try {
            ed.putString("key", weatherState)
            ed.commit();
        } catch (e: Exception) {
            Log.d("prefs Error ", e.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun newLocation(){

        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
        val savedUserLocation = sharedPrefs.getString("newLocation",null)
        Log.e("New user Loaction", savedUserLocation.toString())
            CITY = savedUserLocation.toString();
            Log.e("NewCountry",CITY)
    }
}
