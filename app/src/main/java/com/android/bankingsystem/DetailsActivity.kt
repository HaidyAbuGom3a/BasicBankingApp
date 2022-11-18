package com.android.bankingsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.android.bankingsystem.Database.Transaction
import com.android.bankingsystem.Database.TransactionsDatabase
import com.android.bankingsystem.Database.User
import com.android.bankingsystem.Database.UsersDatabase
import com.android.bankingsystem.databinding.ActivityDetailsBinding
import kotlinx.coroutines.launch

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val arr = intent.getStringArrayExtra(Constants.name)
        val myAdapter = ArrayAdapter(this@DetailsActivity, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,arr!!)
        var userdata = arr[0]
        binding.spinner.apply {
            adapter = myAdapter
            onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    userdata = arr[p2]
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    //Do Nothing
                }
            }
        }
        val user = intent.getSerializableExtra(Constants.user) as User
        binding.nameData.text = user.name
        binding.balanceData.text = user.balance.toString() + " $"

        binding.trans.setOnClickListener{
            var balance = binding.balanceData.text.toString()
            balance = balance.substring(0,balance.length - 2)
            if(binding.amount.text.toString().toLong() > balance.toInt()){
                val toast = Toast.makeText(this,"Failed the amount is larger than your balance",Toast.LENGTH_LONG)
                toast.show()
            }
            else{
                lifecycleScope.launch {
                    val newBalanceOne = user.balance - binding.amount.text.toString().toInt()
                    var secondUser = UsersDatabase(applicationContext).getUsersDao().getUser(userdata)
                    val newBalanceTwo = secondUser.balance + binding.amount.text.toString().toInt()
                    binding.balanceData.text = user.balance.toString()
                    UsersDatabase(applicationContext).getUsersDao().updateUser(newBalanceOne,user.name,user.id)
                    UsersDatabase(applicationContext).getUsersDao().updateUser(newBalanceTwo,secondUser.name,secondUser.id)
                    Log.i("Users","The current user has id ${user.id} with balance ${user.balance}")
                    Log.i("Users","The second user has id ${secondUser.id} with balance ${secondUser.balance}")
                    TransactionsDatabase(applicationContext).getTransDo().insertTrans(Transaction(user.name,secondUser.name,binding.amount.text.toString().toInt()))
                }

                val toast = Toast.makeText(this,"Successful transaction !",Toast.LENGTH_LONG)
                toast.show()
                finish()
            }

        }

        setContentView(binding.root)
    }
}