package com.hava.dvtweatherapp

import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import dvtweatherapp.R
import dvtweatherapp.databinding.ActivityMainBinding
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navView: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        navView = binding.navView
          val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.locations, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
            pullUpSharedPrefs()

    }

    private fun pullUpSharedPrefs() {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this)
        val testString = sharedPrefs.getString("key",null)
          pullUpData(testString)
        Log.e("SharedPrefs", testString.toString())
    }

    private fun pullUpData(jsObj: String?) {
        try {
            val jsObject = JSONObject(jsObj.toString())
            val sys = jsObject.getJSONObject("sys")
            val updatedLast: Long = jsObject.getLong("dt")
            val updatedAt = "updated at:" + SimpleDateFormat(
                "dd/MM/yyyy hh:mm a",
                Locale.ENGLISH
            ).format(Date(updatedLast * 1000))
            val address = jsObject.getString("name") + "," + sys.getString("country")
            drawerData(address, updatedAt);
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    private fun drawerData(locationTxt: String, lastUpdatedTxt: String) {
        val header: View = navView.getHeaderView(0)
        val drawer_userLoaction:TextView = header.findViewById(R.id.drawerUserLocation)
        val drawer_userLastUpdated: TextView = header.findViewById(R.id.drawerLastUpdated);
        drawer_userLoaction.text = locationTxt
        drawer_userLastUpdated.text = lastUpdatedTxt


    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}