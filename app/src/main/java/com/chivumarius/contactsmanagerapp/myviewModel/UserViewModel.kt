package com.chivumarius.contactsmanagerapp.myviewModel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chivumarius.contactsmanagerapp.room.User
import com.chivumarius.contactsmanagerapp.room.UserRepository
import kotlinx.coroutines.launch

// ♦ The "UserViewModel" Class that "Extends" and "Acts" as a "ViewModel"
//      → and "Implements" the "Observable" Interface:
class UserViewModel(private val repository: UserRepository) : ViewModel(), Observable {
    // ♦ Getting "users" from "repository"
    //      → and Connecting "ViewModel" with the "Repository":
    val users = repository.users

    // ♦ Setting:
    private var isUpdateOrDelete = false

    // ♦ Declaration:
    private lateinit var userToUpdateOrDelete : User

    // ♦ The "@Bindable" Object Annotation
    //      → for "input Name"
    //      → is Applied to Any "Getter" Method
    //      → for Any "Observable" Class:
    @Bindable
    val inputName = MutableLiveData<String?>()

    // ♦ The "@Bindable" Object Annotation
    //      → for "input Email"
    @Bindable
    val inputEmail = MutableLiveData<String?>()

    // ♦ The "@Bindable" Object Annotation
    //      → for "save Or Update Button Text"
    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()

    // ♦ The "@Bindable" Object Annotation
    //      → for "clear All Or Delete Button Text"
    @Bindable
    val clearAllOrDeleteButtonText = MutableLiveData<String>()


    // ♦ "Initializer":
    init {
        // ♦ Setting "Button Values":
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }


    // ♦ The "saveOrUpdate()" Function:
    fun saveOrUpdate(){
        // ♦♦ "Detection" of "Saving Functionality" or "Upgrading Functionality" ♦♦
        if(isUpdateOrDelete){
            // ♦ The "Update Functionality" ♦
            userToUpdateOrDelete.name = inputName.value!!
            userToUpdateOrDelete.email = inputEmail.value!!
            
            // ♦ Calling the Method:
            update(userToUpdateOrDelete)
        } else {
            // ♦ The "Insert Functionality" ♦
            val name =  inputName.value!!   // ♦  "!!" = "Not Null"
            val email = inputEmail.value!!  // ♦  "!!" = "Not Null"

            // ♦ Calling the Function:
            insert(User(0,name,email))

            // ♦ Setting:
            inputName.value = null
            inputEmail.value = null
        }
    }


    // ♦ The "clearAllorDelete()" Function
    fun clearAllorDelete()= viewModelScope.launch {
        // ♦♦ "Detection" of "Update Functionality" or "Delete Functionality" ♦♦
        if (isUpdateOrDelete){
            // ♦ Specific "Delete" of a "User":
            delete(userToUpdateOrDelete)
        } else {
            // ♦ "Delete Everything" from the "Database":
            clearAll()
        }
    }


    // ♦ The "insert()" Function
    fun insert(user: User) = viewModelScope.launch {
        // ♦ Calling the "insert()" Function
        //      → from the "room/UserRepository.kt" File:
        repository.insert(user)
    }


    // ♦ The "clearAll()" Function
    fun clearAll()= viewModelScope.launch {
        // ♦ Calling the "deleteAll()" Function
        //      → from the "room/UserRepository.kt" File:
        repository.deleteAll()
    }


    // ♦ The "update()" Function
    fun update(user: User) = viewModelScope.launch {
        // ♦ Calling the "update()" Function
        //      → from the "room/UserRepository.kt" File:
        repository.update(user)

        // ♦ "Resetting" the "Fields":
        inputName.value = null
        inputEmail.value = null
        isUpdateOrDelete = false

        // ♦ "Resetting" the "Buttons":
        saveOrUpdateButtonText.value  = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }


    // ♦ The "delete()" Function:
    fun delete(user: User) = viewModelScope.launch {
        // ♦ Calling the "delete()" Function
        //      → from the "room/UserRepository.kt" File:
        repository.delete(user)

        // ♦ "Resetting" the "Fields":
        inputName.value = null
        inputEmail.value = null
        isUpdateOrDelete = false

        // ♦ "Resetting" the "Buttons":
        saveOrUpdateButtonText.value  = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    // ♦ The "initUpdateAndDelete()" Function:
    fun initUpdateAndDelete(user: User){
        // ♦ "Setting" the "Fields":
        inputName.value = user.name
        inputEmail.value = user.email
        isUpdateOrDelete = true
        userToUpdateOrDelete = user

        // ♦ "Setting" the "Buttons":
        saveOrUpdateButtonText.value  = "Update"
        clearAllOrDeleteButtonText.value = "Delete"
    }


    // ♦ The "Implementation Member" Function
    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    // ♦ The "Implementation Member" Function
    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}