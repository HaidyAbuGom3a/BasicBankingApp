package com.android.bankingsystem.Users

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.bankingsystem.R

class UserViewHolder(itemView: View,listener:RecyclerUserInterface) :RecyclerView.ViewHolder(itemView){
    val name = itemView.findViewById<TextView>(R.id.name)
    init {
        itemView.setOnClickListener{
            listener.OnItemClicked(adapterPosition,name.text.toString())
        }
    }

}