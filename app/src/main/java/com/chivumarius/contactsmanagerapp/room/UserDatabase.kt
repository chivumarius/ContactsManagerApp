package com.chivumarius.contactsmanagerapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


// ♦ This "Abstract Class" will "Act" as an "Database"
//      → have the "entities" Array
//      → withe only one "User" Class:
@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    // ♦ Creating the Instance:
    abstract val userDAO: UserDAO

    // ♦ The "Singleton" Design Pattern
    //      → for "Database Connectivity" and "Network Connections":
    companion object {
        // ♦ The "@Volatile" Annotation
        //      → makes the "Field Immediately Visible"
        //      → to "Other Threads":
        @Volatile
        private var INSTANCE: UserDatabase? = null

        // ♦ The "getInstance()" Function
        //      → will extend from "UserDatabase":
        fun getInstance(context: Context): UserDatabase {
            // ♦ Calling the "synchronized()" Functions:
            synchronized(this) {
                // ♦ Setting:
                var instance = INSTANCE

                // ♦ Checking: if there is no "Database Object" Created:
                if (instance == null) {
                    // ♦ "Creating" the "Database Object":
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "users_db"
                    )
                        .build()
                }
                INSTANCE = instance

                // ♦  If there is "Database Object" Created → "Return It":
                return instance
            }
        }
    }
}