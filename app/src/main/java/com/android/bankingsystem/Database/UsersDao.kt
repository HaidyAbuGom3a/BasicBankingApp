package com.android.bankingsystem.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UsersDao {

    @Insert
    suspend fun insertUser(user:User)

    @Query("select * from Users_table")
    suspend fun getUsers():List<User>

    @Query("select * from Users_table where name ==:nm ")
    suspend fun getUser(nm:String):User

    @Query("delete from Users_table")
    suspend fun clearUsers()

    @Query("update Users_table set balance=:b where name =:nm and id =:i")
    suspend fun updateUser(b:Int,nm:String,i:Int)

    @Query("update Users_table set name=:newName where name =:nm and id =:i")
    suspend fun updateName(newName:String,nm:String,i:Int)
}