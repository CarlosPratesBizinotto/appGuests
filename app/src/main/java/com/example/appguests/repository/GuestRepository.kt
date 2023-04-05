package com.example.appguests.repository

import android.content.ContentValues
import android.content.Context
import com.example.appguests.constants.DataBaseConstants
import com.example.appguests.model.GuestModel
import java.lang.Exception

//Essa camada quem lida com os dados do banco

class GuestRepository private constructor(context: Context){

    //Ligação entre GuestRepository e GuestDataBase -> Quando eu instanciar a GuestRepository a GuestDataBase ja vai ser instanciada
    //Como GuestDataBase tem um context, deve ser passado o contexto aqui tambem no constructor
    //Com isso no Singleton tem que passar o contexto tambem
    private val guestDataBase = GuestDataBase(context)


    //Singleton
    companion object{
        private lateinit var repository: GuestRepository

        fun getInstance(context: Context): GuestRepository {
            if(Companion::repository.isLateinit){
                repository = GuestRepository(context)

            }
            return repository
        }

    }

    fun insert(guest: GuestModel): Boolean {
        return try {

            val db = guestDataBase.writableDatabase

            val presence = if (guest.presence) 1 else 0

            val values = ContentValues()
            values.put(DataBaseConstants.GUEST.COLUMNS.NAME,guest.name)
            values.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, presence)

            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, values)

             true
        } catch (e: Exception) {
             false
        }
    }

    fun update(){

    }

}