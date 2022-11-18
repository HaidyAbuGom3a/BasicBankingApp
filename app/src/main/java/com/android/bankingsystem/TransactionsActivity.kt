package com.android.bankingsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.bankingsystem.Database.TransactionsDatabase
import com.android.bankingsystem.Transactions.TransactionsAdapter
import com.android.bankingsystem.databinding.ActivityTransactionsBinding
import kotlinx.coroutines.launch

class TransactionsActivity : AppCompatActivity() {
    private lateinit var binding:ActivityTransactionsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionsBinding.inflate(layoutInflater)
        lifecycleScope.launch{
            val trans = TransactionsDatabase(applicationContext).getTransDo().getTrans()
            binding.transactionsRec.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = TransactionsAdapter().apply {
                    setData(trans)
                }
            }
        }
        setContentView(binding.root)
    }
}