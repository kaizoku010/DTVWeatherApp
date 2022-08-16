package com.hava.dvtweatherapp.ui.locations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LocationViewModel : ViewModel() {



    private val _text = MutableLiveData<String>().apply {
        value = "This here is a Fragment"
    }
    val text: LiveData<String> = _text
}