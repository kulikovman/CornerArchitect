package com.example.cornerarchitect.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cornerarchitect.database.dao.IContactDao
import com.example.cornerarchitect.model.Contact

@Database(
    entities = [Contact::class],
    version = 2
)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun contactDao() : IContactDao

}