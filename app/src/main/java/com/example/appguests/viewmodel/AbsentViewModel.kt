package com.example.appguests.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AbsentViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Fragment dos ausentes"
    }
    val text: LiveData<String> = _text
}