package com.android.bankingsystem.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions_table")
data class Transaction(
    val first_user:String,
    val second_user:String,
    val amount:Int
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}