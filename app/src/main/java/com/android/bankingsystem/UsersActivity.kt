package com.android.bankingsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.bankingsystem.Database.TransactionsDatabase
import com.android.bankingsystem.Database.User
import com.android.bankingsystem.Database.UsersDatabase
import com.android.bankingsystem.Users.RecyclerUserInterface
import com.android.bankingsystem.Users.UsersAdapter
import com.android.bankingsystem.databinding.ActivityUsersBinding
import kotlinx.coroutines.launch

class UsersActivity : AppCompatActivity(){

    private lateinit var binding: ActivityUsersBinding
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        lifecycleScope.launch{
            val usersList = UsersDatabase(applicationContext).getUsersDao().getUsers()
            //UsersDatabase(applicationContext).getUsersDao().clearUsers()
            if(usersList.size == 0){
                Log.i("Users","Hello let's add again")
                UsersDatabase(applicationContext).getUsersDao().insertUser(User("James Johnson",10000000))
                UsersDatabase(applicationContext).getUsersDao().insertUser(User("Edward Miller",5345))
                UsersDatabase(applicationContext).getUsersDao().insertUser(User("Melissa Davisr",100000))
                UsersDatabase(applicationContext).getUsersDao().insertUser(User("Stephanie Williams",50000000))
                UsersDatabase( applicationContext).getUsersDao().insertUser(User("Steven Jones",10000000))
                UsersDatabase(applicationContext).getUsersDao().insertUser(User("Andrew Rodriguez",52342))
                UsersDatabase(applicationContext).getUsersDao().insertUser(User("Carol Martinez",10230))
                UsersDatabase(applicationContext).getUsersDao().insertUser(User("Lisa Harris",3430))
                UsersDatabase(applicationContext).getUsersDao().insertUser(User("Mary Thompson",70000000))
                UsersDatabase(applicationContext).getUsersDao().insertUser(User("John Jackson",13454332))

            }
            //TransactionsDatabase(applicationContext).getTransDo().clearData()
            val arr =Array(10,{""})
            var index = 0
            for(user in usersList){
                Log.i("Users","User with id ${user.id } has balance ${user.balance}")
                arr[index] = user.name
                index += 1
            }
            val intent = Intent(this@UsersActivity,DetailsActivity::class.java)
            intent.putExtra(Constants.name,arr)
            Log.e("ACTIVITY_USERS", "Size is : ${usersList.size}")
            binding.recycler.apply {
               layoutManager = LinearLayoutManager(applicationContext)
                adapter = UsersAdapter().apply {
                    setData(usersList)
                    setOnItemClickListener(
                        object :RecyclerUserInterface{
                            override fun OnItemClicked(pos: Int, name: String) {
                               lifecycleScope.launch {
                                   val user = UsersDatabase(applicationContext).getUsersDao().getUser(name)
                                   intent.putExtra(Constants.user,user)
                                   startActivity(intent)
                               }
                            }

                        }
                    )

                }
            }

        }
        binding.go.setOnClickListener {
            val intent2 = Intent(this@UsersActivity,TransactionsActivity::class.java)
            startActivity(intent2)
        }

        setContentView(binding.root)
    }

}