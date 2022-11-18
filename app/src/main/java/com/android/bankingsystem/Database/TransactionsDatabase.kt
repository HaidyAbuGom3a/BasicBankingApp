package com.android.bankingsystem.Database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Transaction::class], version = 1)
abstract class TransactionsDatabase:RoomDatabase() {

    abstract fun getTransDo():TransactionsDao

    companion object{
        @Volatile
        private var instance:TransactionsDatabase ?= null
        private var LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            TransactionsDatabase::class.java,
            "transactions_database"
        ).build()


    }

}