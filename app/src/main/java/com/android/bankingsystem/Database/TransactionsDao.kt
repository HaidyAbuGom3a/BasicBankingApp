package com.android.bankingsystem.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TransactionsDao {
    @Insert
    suspend fun insertTrans(transaction: Transaction)

    @Query("select * from transactions_table ")
    suspend fun getTrans():List<Transaction>

    @Query("delete from transactions_table")
    suspend fun clearData()

}