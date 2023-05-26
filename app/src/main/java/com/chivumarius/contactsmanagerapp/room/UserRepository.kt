package com.chivumarius.contactsmanagerapp.room

// ♦  The "UserRepository" Class
//      → "Abstracts Access" to "Multiple Data Sources"
//      → for "Fetching Data" from the "Room Database":
class UserRepository(private val dao: UserDAO) {
    // ♦ Declaration and Initialization:
    val users = dao.getAllUsersInDB()

    // ♦ The "insert()" Suspend Function:
    suspend fun insert(user: User): Long{
        return dao.insertUser(user)
    }

    // ♦ The "delete()" Suspend Function:
    suspend fun delete(user: User) {
        return dao.deleteUser(user)
    }

    // ♦ The "update()" Suspend Function:
    suspend fun update(user: User) {
        return dao.updateUser(user)
    }

    // ♦ The "deleteAll()" Suspend Function:
    suspend fun deleteAll(){
        return dao.deleteAll()
    }
}


