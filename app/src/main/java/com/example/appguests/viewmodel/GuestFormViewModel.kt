package com.example.appguests.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.appguests.model.GuestModel
import com.example.appguests.repository.GuestRepository

//ViewModel nao tem contexto, quem tem é AndroidViewModel
//ViemModel quem lida com os dados
class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository.getInstance(application)

    fun insert(guest: GuestModel){
        repository.insert(guest)

    }



}