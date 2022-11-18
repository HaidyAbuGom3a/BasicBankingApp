package com.android.bankingsystem.Users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.bankingsystem.Database.User
import com.android.bankingsystem.R

class UsersAdapter: RecyclerView.Adapter<UserViewHolder>() {

    private val list = mutableListOf<User>()
    private lateinit var mlistener:RecyclerUserInterface

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_user,parent,false)
        return UserViewHolder(view,mlistener)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = list[position]
        holder.name.text = user.name
    }

    override fun getItemCount(): Int = list.size

    fun setData(data: List<User>){
        list.apply {
            clear()
            addAll(data)
        }
    }

    fun setOnItemClickListener(listener: RecyclerUserInterface){
        mlistener = listener
    }
}