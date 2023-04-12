package com.example.appguests.repository

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import com.example.appguests.constants.DataBaseConstants
import com.example.appguests.model.GuestModel
import java.lang.Exception

//Essa camada quem lida com os dados do banco

class GuestRepository private constructor(context: Context) {

    //Ligação entre GuestRepository e GuestDataBase -> Quando eu instanciar a GuestRepository a GuestDataBase ja vai ser instanciada
    //Como GuestDataBase tem um context, deve ser passado o contexto aqui tambem no constructor
    //Com isso no Singleton tem que passar o contexto tambem
    private val guestDataBase = GuestDataBase(context)


    //Singleton
    companion object {
        private lateinit var repository: GuestRepository

        fun getInstance(context: Context): GuestRepository {
            if (Companion::repository.isLateinit) {
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
            values.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            values.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, presence)

            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, values)

            true
        } catch (e: Exception) {
            false
        }
    }

    fun update(guest: GuestModel): Boolean {
        return try {

            val db = guestDataBase.writableDatabase
            val presence = if (guest.presence) 1 else 0

            val values = ContentValues()
            values.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            values.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, presence)

            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(guest.id.toString())


            db.update(DataBaseConstants.GUEST.TABLE_NAME, values, selection, args)

            true
        } catch (e: Exception) {
            false
        }

    }

    fun delete(id: Int): Boolean {
        return try {
            //Abertura de conexão com o banco
            val db = guestDataBase.writableDatabase


            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ? "
            val args = arrayOf(id.toString())


            db.delete(DataBaseConstants.GUEST.TABLE_NAME, selection, args)
            true
        } catch (e: Exception) {
            false
        }

    }

    @SuppressLint("Range")
    fun get(id: Int): GuestModel? {

        var guest: GuestModel? = null

        try {

            val db = guestDataBase.readableDatabase

            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )

            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ? "
            val args = arrayOf(id.toString())

            val cursor =  db.query(DataBaseConstants.GUEST.TABLE_NAME, projection, selection, args, null, null, null)

            if (cursor != null && cursor.count > 0){
                while (cursor.moveToNext()){

                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))

                    guest = (GuestModel(id, name, presence == 1))
                }
            }

            cursor.close()
        } catch (e: Exception){
            return guest
        }
        return guest


    }

    @SuppressLint("Range")
    fun getALL(): List<GuestModel> {

        val list = mutableListOf<GuestModel>()
        try {

            val db = guestDataBase.readableDatabase

            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )

            val cursor =  db.query(DataBaseConstants.GUEST.TABLE_NAME, projection, null, null, null, null, null)

            if (cursor != null && cursor.count > 0){
                while (cursor.moveToNext()){

                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))

                    list.add(GuestModel(id, name, presence == 1))
                }
            }

            cursor.close()
        } catch (e: Exception){
            return list
        }
        return list


    }

    @SuppressLint("Range")
    fun getPresent(): List<GuestModel> {

        val list = mutableListOf<GuestModel>()
        try {

            val db = guestDataBase.readableDatabase
            val cursor = db.rawQuery("SELECT id, name, presence FROM GUEST WHERE presence = 1", null)


            if (cursor != null && cursor.count > 0){
                while (cursor.moveToNext()){

                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))

                    list.add(GuestModel(id, name, presence == 1))
                }
            }

            cursor.close()
        } catch (e: Exception){
            return list
        }
        return list


    }

    @SuppressLint("Range")
    fun getAbsent(): List<GuestModel> {

        val list = mutableListOf<GuestModel>()
        try {

            val db = guestDataBase.readableDatabase
            val cursor = db.rawQuery("SELECT id, name, presence FROM GUEST WHERE presence = 0", null)


            if (cursor != null && cursor.count > 0){
                while (cursor.moveToNext()){

                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))

                    list.add(GuestModel(id, name, presence == 1))
                }
            }

            cursor.close()
        } catch (e: Exception){
            return list
        }
        return list


    }
}