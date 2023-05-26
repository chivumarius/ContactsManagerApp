package com.chivumarius.contactsmanagerapp.myviewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chivumarius.contactsmanagerapp.room.UserRepository


// ♦ The "UserViewModelFactory" Class
//      → which Extend the "ViewModelProvider.Factory" Interface
//      → having a "Primary Constructor"
//      → with a "modelClass" Property Declaration:
class UserViewModelFactory(private val repository: UserRepository): ViewModelProvider.Factory {
    // ♦ The "create()" Method
    //      → which is a "Template":
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // ♦ Condition Check:
        if(modelClass.isAssignableFrom(UserViewModel::class.java)){
            return UserViewModel(repository) as T
        }
        // ♦ In case of an "Error":
        throw IllegalArgumentException("Unknown View Model Class")
    }
}