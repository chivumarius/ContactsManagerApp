package com.chivumarius.contactsmanagerapp.viewUI

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.chivumarius.contactsmanagerapp.R
import com.chivumarius.contactsmanagerapp.databinding.CardItemBinding
import com.chivumarius.contactsmanagerapp.room.User

// ♦ The "MyRecyclerViewAdapter" Class
//      → will Extend the "RecyclerView.Adapter" Class:
class MyRecyclerViewAdapter (
        private val usersList:List<User>,
        private val clickListener: (User)->Unit
     ) : RecyclerView.Adapter<MyViewHolder>(){

     // ♦ The "onCreateViewHolder()" Method:
     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
         // ♦  The "layoutInflater" Initialization:
         val layoutInflater = LayoutInflater.from(parent.context)

         // ♦ Displaying "layout/ card_item.xml" File:
         val binding : CardItemBinding = DataBindingUtil.
             inflate(layoutInflater, R.layout.card_item, parent, false)

         // ♦ Returning the "MyViewHolder" Class
         return MyViewHolder(binding)
     }


    // ♦ The "getItemCount()" Method:
    override fun getItemCount(): Int {
        return usersList.size
    }


    // ♦ The "onBindViewHolder()" Method:
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(usersList[position],clickListener)
    }
}


// ♦ The "MyViewHolder" Class
//      → will Extend the "RecyclerView.ViewHolder":
class MyViewHolder(val binding: CardItemBinding): RecyclerView.ViewHolder(binding.root){
    // ♦ The "bind()" Function:
    fun bind(user: User, clickListener: (User) -> Unit){
        // ♦ Setting the "text" for "nameTextView":
        binding.nameTextView.text = user.name

        // ♦ Setting the "text" for "emailTextView":
        binding.emailTextView.text = user.email

        // ♦ Setting a "Click Listener"
        //      → for the "listItemLayout" ID
        //      → from "layout/ card_item.xml" File:
        binding.listItemLayout.setOnClickListener{
            clickListener(user)
        }
    }
}