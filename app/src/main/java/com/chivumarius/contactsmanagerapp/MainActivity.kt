package com.chivumarius.contactsmanagerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.chivumarius.contactsmanagerapp.databinding.ActivityMainBinding
import com.chivumarius.contactsmanagerapp.myviewModel.UserViewModel
import com.chivumarius.contactsmanagerapp.myviewModel.UserViewModelFactory
import com.chivumarius.contactsmanagerapp.room.User
import com.chivumarius.contactsmanagerapp.room.UserDatabase
import com.chivumarius.contactsmanagerapp.room.UserRepository
import com.chivumarius.contactsmanagerapp.viewUI.MyRecyclerViewAdapter

class MainActivity : AppCompatActivity() {
    // (DB LIB 3-1) "Declaring" the "binding" Object
    //      → as an "Object" Generated by the "Data Binding Library":
    private lateinit var binding : ActivityMainBinding
    // (VM LIB 3-1) CREATING AN INSTANCE FROM THE "MainActivityViewModel" CLASS:
    private lateinit var userViewModel: UserViewModel


    // ♦  The "onCreate()" Method:
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // (DB LIB 3-2) "Initialize" the "binding" Object:
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // ♦ The "Room" Database "Instances":
        val dao = UserDatabase.getInstance(application).userDAO
        val repository = UserRepository(dao)
        val factory = UserViewModelFactory(repository)

        // (VM LIB 3-2) "INITIALIZE" THE "USER VIEW MODEL" OBJECT
        //              → by Using "ViewModelProvider()":
        userViewModel = ViewModelProvider(this,
            factory).get(UserViewModel::class.java)

        // (DB LIB 3-3) Binding the "userViewModel" with "userViewModel":
        binding.userViewModel = userViewModel

        // (DB LIB 3-4) Binding the "lifecycleOwner" with "this app":
        binding.lifecycleOwner = this

        // ♦ Calling the Method:
        initRecyclerView()
    }

    // ♦ The "initRecyclerView()" Method:
    private fun initRecyclerView() {
        // (DB LIB 3-5) Binding the "recyclerView.layoutManage" with "LinearLayoutManager":
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // ♦ Calling the Method:
        DisplayUsersList()
    }


    // ♦ The "DisplayUsersList()" Method:
    private fun DisplayUsersList() {
        // (LD LIB 3) GETTING "LIVE DATA"
        //      → by Using the "observe()" Method
        //      → for "Observing" for "Any Data Change"
        userViewModel.users.observe(this, Observer {
            binding.recyclerView.adapter = MyRecyclerViewAdapter(
                it, {selectedItem: User -> listItemClicked(selectedItem) }
            )
        })
    }

    // ♦ The "listItemClicked()" Method:
    private fun listItemClicked(selectedItem: User) {
        // ♦ Toast Message:
        Toast.makeText(this,
            "Selected name is ${selectedItem.name}",
            Toast.LENGTH_LONG).show()

        userViewModel.initUpdateAndDelete(selectedItem)
    }
}