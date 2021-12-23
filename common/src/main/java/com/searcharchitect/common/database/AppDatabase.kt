package com.searcharchitect.common.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.searcharchitect.common.database.dao.IContactDao
import com.searcharchitect.common.model.Contact

@Database(
    entities = [Contact::class],
    version = 2
)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun contactDao() : IContactDao

}