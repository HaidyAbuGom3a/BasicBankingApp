package com.android.bankingsystem.Transactions

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.bankingsystem.R

open class TransactionsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val first_user = itemView.findViewById<TextView>(R.id.user1)
    val second_user = itemView.findViewById<TextView>(R.id.user2)
    val amount = itemView.findViewById<TextView>(R.id.to)
}