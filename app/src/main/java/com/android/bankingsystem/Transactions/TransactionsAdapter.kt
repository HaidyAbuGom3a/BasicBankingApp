package com.android.bankingsystem.Transactions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.bankingsystem.Database.Transaction
import com.android.bankingsystem.R

class TransactionsAdapter: RecyclerView.Adapter<TransactionsViewHolder>() {
    val transactions = mutableListOf<Transaction>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionsViewHolder {
        val view =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.transactions_card,parent,false)
        return TransactionsViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionsViewHolder, position: Int) {
        holder.first_user.text = transactions[position].first_user
        holder.second_user.text = transactions[position].second_user
        holder.amount.text = "sent " + transactions[position].amount.toString() + " $ to "
    }

    override fun getItemCount() = transactions.size

    fun setData(data:List<Transaction>){
        transactions.apply {
            clear()
            addAll(data)
        }
    }
}