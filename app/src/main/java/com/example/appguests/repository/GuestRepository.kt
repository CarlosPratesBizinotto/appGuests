package com.example.appguests.repository

class GuestRepository private constructor(){

    //Singleton
    companion object{
        private lateinit var repository: GuestRepository

        fun getInstance(): GuestRepository {
            if(Companion::repository.isLateinit){
                repository = GuestRepository()

            }
            return repository
        }
    }
}