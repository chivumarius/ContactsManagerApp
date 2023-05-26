package com.chivumarius.contactsmanagerapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


// ♦♦ THE  "TABLE  NAME" ♦♦
// ♦ This "Class" will "Act" as an "Entity" ("Table")
//      → have the "Table Name" of "user":
@Entity(tableName = "user")
data class User(
    // ♦♦ THE "TABLE COLUMNS": ♦♦

    // (1.1) Setting the "id"
    //          → as  "Primary Key"
    //           → and  "Auto Generate It"
    @PrimaryKey(autoGenerate = true)

    // (1) The "user_id" Column Info Name:
    @ColumnInfo(name = "user_id")
    var id: Int,

    // (2) The "user_name" Column Info Name:
    @ColumnInfo(name = "user_name")
    var name: String,

    // (3) The "user_email" Column Info Name:
    @ColumnInfo(name = "user_email")
    var email: String
)
