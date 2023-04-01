package com.example.appguests.viewmodel

import androidx.lifecycle.ViewModel
import com.example.appguests.repository.GuestRepository

class GuestFormViewModel : ViewModel() {

    private val repository = GuestRepository.getInstance()



}