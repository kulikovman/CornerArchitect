package com.example.cornerarchitect.manager

import androidx.lifecycle.MutableLiveData
import com.example.cornerarchitect.model.Contact
import com.example.cornerarchitect.repositiry.IDatabaseRepository
import com.example.cornerarchitect.repositiry.IDatastoreRepository
import com.example.cornerarchitect.repositiry.INetworkRepository
import com.example.cornerarchitect.retrofit.Failure
import com.example.cornerarchitect.utility.Either
import com.example.cornerarchitect.utility.getTestData
import com.example.cornerarchitect.utility.log
import javax.inject.Inject

interface IContactManager {

    val contacts: MutableLiveData<List<Contact>>

    var selectedCity: String?
    var selectedSpecialization: String?



    //suspend fun getDatabaseContacts()

}

class ContactManager @Inject constructor(
    private val network: INetworkRepository,
    private val database: IDatabaseRepository,
    private val datastore: IDatastoreRepository
) : IContactManager {

    override val contacts = MutableLiveData(emptyList<Contact>())

    override var selectedCity: String? = null

    override var selectedSpecialization: String? = null


}