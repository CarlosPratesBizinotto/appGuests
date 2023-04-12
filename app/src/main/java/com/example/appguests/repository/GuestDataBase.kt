package com.example.appguests.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.appguests.constants.DataBaseConstants
import com.example.appguests.model.GuestModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized


//Conexão antiga do banco de dados

//Unica função é conexao com o banco
//class GuestDataBase(context: Context) : SQLiteOpenHelper(context, NAME, null, VERSION) {

   /* companion object{
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

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {} */

//}

//Conexão nova usando o Room
@Database(entities = [GuestModel::class], version = 1)
abstract class GuestDataBase : RoomDatabase() {

    abstract fun guestDAO(): GuestDAO

     companion object{
         private lateinit var INSTANCE: GuestDataBase

         @OptIn(InternalCoroutinesApi::class)
         fun getDataBase(context: Context): GuestDataBase{
             if (!::INSTANCE.isInitialized) {
                 synchronized(GuestDataBase::class) {
                     INSTANCE = Room.databaseBuilder(context, GuestDataBase::class.java, "guestdb")
                         .addMigrations(MIGRATION_1_2)
                         .allowMainThreadQueries()
                         .build()
                 }
             }
             return INSTANCE
         }

         private val MIGRATION_1_2: Migration = object : Migration(1, 2){
             override fun migrate(database: SupportSQLiteDatabase) {
                 database.execSQL("DELETE FROM Guest")
             }

         }

     }
}