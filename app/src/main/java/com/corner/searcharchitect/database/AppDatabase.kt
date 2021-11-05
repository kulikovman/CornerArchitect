package com.corner.searcharchitect.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.corner.searcharchitect.database.dao.IContactDao
import com.corner.searcharchitect.model.Contact

@Database(
    entities = [Contact::class],
    version = 1
)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun contactDao() : IContactDao

}