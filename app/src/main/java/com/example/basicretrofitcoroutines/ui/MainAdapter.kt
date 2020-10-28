package com.example.basicretrofitcoroutines.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.basicretrofitcoroutines.R
import com.example.basicretrofitcoroutines.data.model.User
import kotlinx.android.synthetic.main.inflate_items.view.*

class MainAdapter(var list : ArrayList<User>) : RecyclerView.Adapter<MainAdapter.MyViewHolder>() {

    lateinit var con : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.MyViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        con = parent.context
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder:  MainAdapter.MyViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount() = list.size

    fun addUsers(user : List<User>){
        list.clear()
        list.addAll(user)
    }

    inner class MyViewHolder(var v : View) : RecyclerView.ViewHolder(v){
        fun onBind(user: User){
//            val email = user.userEmail.trim()
            v.apply {
                user.apply {
                    txtName.text = userName
                    txtEmail.text = userEmail
//                    txtId.text = user.userId
                    Glide.with(con).load(image).into(v.image)
                }
//               root.setOnClickListener {  }
            }
        }
    }
}