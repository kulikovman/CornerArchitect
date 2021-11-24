package com.searcharchitect.common.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.searcharchitect.common.model.Contact

@Dao
interface IContactDao {

    @Query("DELETE FROM Contact")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContacts(contacts: List<Contact>)

    @Query("SELECT * FROM Contact")
    fun getContacts(): List<Contact>

    @Query("SELECT COUNT(*) FROM Contact")
    fun getNumberOfContacts(): Int

}