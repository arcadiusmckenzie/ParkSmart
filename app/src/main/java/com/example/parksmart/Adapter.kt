package com.example.parksmart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val userList : ArrayList<User>) : RecyclerView.Adapter<Adapter.Viewholder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item2, parent, false)
        return Viewholder(itemView)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {

        val currentitem = userList[position]

        holder.Email.text = currentitem.Email
        holder.Password.text = currentitem.Password
        holder.Phonenumber.text = currentitem.Phonenumber
    }

    override fun getItemCount(): Int {
        return userList.size    }

    class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val Email : TextView = itemView.findViewById(R.id.tvemail)
        val Password : TextView = itemView.findViewById(R.id.tvpassword)
        val Phonenumber : TextView = itemView.findViewById(R.id.tvphone)

    }
}