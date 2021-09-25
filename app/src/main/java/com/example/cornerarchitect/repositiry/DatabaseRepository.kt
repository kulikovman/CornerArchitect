package com.example.cornerarchitect.repositiry

import com.example.cornerarchitect.database.dao.IContactDao
import javax.inject.Inject

interface IDatabaseRepository {


}

class DatabaseRepository @Inject constructor(
    private val contactDao: IContactDao
) : IDatabaseRepository {


}