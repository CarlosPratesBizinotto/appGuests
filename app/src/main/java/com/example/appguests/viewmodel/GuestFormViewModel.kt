package com.example.appguests.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.appguests.model.GuestModel
import com.example.appguests.repository.GuestRepository

//ViewModel nao tem contexto, quem tem é AndroidViewModel
//ViemModel quem lida com os dados

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {


    private val repository = GuestRepository(application.applicationContext)

    private val guestModel = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = guestModel

    private val _saveGuest = MutableLiveData<String>()
    val saveGueest: LiveData<String> = _saveGuest


    fun save(guest: GuestModel) {
        if (guest.id == 0) {
            if (repository.insert(guest)) {
                _saveGuest.value = "Inserção com sucesso!!!"
            } else {
                _saveGuest.value = ""
            }
        } else {
            if (repository.update(guest)) {
                _saveGuest.value = "Atualização com sucesso!!!"
            } else {
                _saveGuest.value = ""
            }
        }

    }


    fun get(id: Int) {
        guestModel.value = repository.get(id)
    }


}