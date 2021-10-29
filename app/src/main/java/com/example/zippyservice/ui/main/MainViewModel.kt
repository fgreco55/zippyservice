package com.example.zippyservice.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zippyservice.ZipcodeApi
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _zipstring = MutableLiveData<String>()
    val thezipinfo: LiveData<String> = _zipstring

    fun getZipInfo(zip: String) {
        viewModelScope.launch {
            val results = ZipcodeApi.retrofitService.getProperties(zip)
            _zipstring.value = results
            Log.i("Frank", "Got results [" + results + "]")
        }
    }
}