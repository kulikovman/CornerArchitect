package com.example.searcharchitect.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.searcharchitect.database.dao.IContactDao
import com.example.searcharchitect.model.Contact

@Database(
    entities = [Contact::class],
    version = 2
)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun contactDao() : IContactDao

}