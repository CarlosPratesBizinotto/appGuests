package com.example.appguests.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PresentViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Fragments dos presentes"
    }
    val text: LiveData<String> = _text
}