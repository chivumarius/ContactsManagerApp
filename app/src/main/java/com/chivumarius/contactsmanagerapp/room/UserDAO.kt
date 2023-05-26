/* ▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀
       ♦ The "suspend" Keyword
           → is Used to "Execute"
           → a "Long-Running Operation"

       ♦ The "Suspend Function"
           → is a Function that can be "Paused"
           → and "Resumed Later".
▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀*/
package com.chivumarius.contactsmanagerapp.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

// ♦ Specifying the "Interface" to "Act" as a "DAO"
//      → by "Annotating It" with "@Dao"
@Dao
interface UserDAO {
    // ♦♦  CRUD FUNCTIONALITY  ♦♦

    // (1) "Insert" Suspend Function
    //      → Annotated "@Insert"
    //      → to "Act" as a "Insert":
    @Insert
    suspend fun insertUser(user: User):Long


    // (2) "Update" Suspend Function
    //      → Annotated "@Update"
    //      → to "Act" as a "Update":
    @Update
    suspend fun updateUser(user: User)


    // (3) "Delete" Suspend Function
    //      → Annotated "@Delete"
    //      → to "Act" as a "Delete":
    @Delete
    suspend fun deleteUser(user: User)


    // (4) "Delete All" Suspend Function
    //      → is a Custom "Query("SQL Query")"
    //      → Annotated "@Query("DELETE FROM user")"
    //      → to "Act" as a "Query("DELETE FROM user")":
    @Query("DELETE FROM user")
    suspend fun deleteAll()


    // (5) "Get All User In DB" Function
    //      → is a Custom "Query("SQL Query")"
    //      → Annotated "@Query("SELECT * FROM user")"
    //      → to "Act" as a "Query("SELECT * FROM user")":
    @Query("SELECT * FROM user")
    fun getAllUsersInDB(): LiveData<List<User>>
}