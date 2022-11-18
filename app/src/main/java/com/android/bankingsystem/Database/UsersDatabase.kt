package com.android.bankingsystem.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class UsersDatabase:RoomDatabase() {

    abstract fun getUsersDao():UsersDao

    companion object{
        @Volatile
        private var instance:UsersDatabase ?= null
        private var LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            UsersDatabase::class.java,
            "users_database"
        ).build()


    }
}