package com.example.appguests.constants

class DataBaseConstants private constructor() {

    object GUEST {

        const val ID = "guestid"
        const val TABLE_NAME = "GUEST"

        object COLUMNS {
            const val ID = "id"
            const val NAME = "name"
            const val PRESENCE = "presence"
        }
    }

}