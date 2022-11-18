package com.android.bankingsystem.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users_table")
data class User(
    val name:String,
    val balance:Int
):java.io.Serializable{
    @PrimaryKey(autoGenerate = true)
    var id = 0
}