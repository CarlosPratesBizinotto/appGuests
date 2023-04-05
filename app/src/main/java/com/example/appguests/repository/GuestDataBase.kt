package com.example.appguests.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.appguests.constants.DataBaseConstants

//Unica função é conexao com o banco
class GuestDataBase(context: Context) : SQLiteOpenHelper(context, NAME, null, VERSION) {

    companion object{
        private const val NAME = "guestdb"
        private const val VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        //Criação do banco de dados
        db.execSQL("CREATE TABLE " + DataBaseConstants.GUEST.TABLE_NAME + " (" +
        DataBaseConstants.GUEST.COLUMNS.ID + " integer primary key autoincrement, " +
        DataBaseConstants.GUEST.COLUMNS.NAME + " text, " +
        DataBaseConstants.GUEST.COLUMNS.PRESENCE + " integer);")

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {


    }


}