package com.example.cornerarchitect.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.cornerarchitect.model.Contact

@Dao
interface IContactDao {

    @Query("SELECT * FROM Contact")
    fun getContactList(): List<Contact>

}