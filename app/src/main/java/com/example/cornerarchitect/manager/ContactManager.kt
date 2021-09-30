package com.example.cornerarchitect.manager

import androidx.lifecycle.MutableLiveData
import com.example.cornerarchitect.model.Contact
import com.example.cornerarchitect.repositiry.IDatabaseRepository
import com.example.cornerarchitect.utility.getTestData
import javax.inject.Inject

interface IContactManager {

    val contacts: MutableLiveData<List<Contact>>

}

class ContactManager @Inject constructor(
    private val database: IDatabaseRepository
) : IContactManager {

    override val contacts = MutableLiveData(emptyList<Contact>())


    init {
        //contacts.value = getTestData()
    }

}