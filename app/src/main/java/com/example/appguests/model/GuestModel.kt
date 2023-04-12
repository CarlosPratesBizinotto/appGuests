package com.example.appguests.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Para informar o room para criar a tabela

@Entity(tableName = "Guest")
class GuestModel{

    //Para informar o room para criar colunas basta colocar o @ColumInfo, mesma coisa para informa quem é chave primaria

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    @ColumnInfo(name = "name")
    var name: String = ""

    @ColumnInfo(name = "presence")
    var presence: Boolean = false

}