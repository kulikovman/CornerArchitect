package com.example.searcharchitect.manager

import androidx.lifecycle.MutableLiveData
import com.example.searcharchitect.model.Contact
import com.example.searcharchitect.repositiry.IDatabaseRepository
import com.example.searcharchitect.repositiry.IDatastoreRepository
import com.example.searcharchitect.repositiry.INetworkRepository
import com.example.searcharchitect.ui.search.ItemSearchUi
import javax.inject.Inject

interface IContactManager {

    val contacts: MutableLiveData<List<Contact>>

    var selectedCity: String?
    var selectedSpecialization: String?
    var selectedContact: MutableLiveData<Contact>

    fun getContactList(
        city: String? = null,
        specialization: String? = null,
        name: String? = null
    ): List<ItemSearchUi>

}

class ContactManager @Inject constructor(
    private val network: INetworkRepository,
    private val database: IDatabaseRepository,
    private val datastore: IDatastoreRepository
) : IContactManager {

    override val contacts = MutableLiveData(emptyList<Contact>())

    override var selectedCity: String? = null

    override var selectedSpecialization: String? = null

    override var selectedContact = MutableLiveData<Contact>()


    override fun getContactList(
        city: String?,
        specialization: String?,
        name: String?
    ): List<ItemSearchUi> {
        var result = contacts.value?.map { contact ->
            ItemSearchUi(
                id = contact.id,
                name = "${contact.surname} ${contact.name}",
                city = contact.city,
                specialization = contact.specialization
            )
        }

        if (!city.isNullOrEmpty()) {
            result = result?.filter { it.city.contains(city, true) }
        }

        if (!specialization.isNullOrEmpty()) {
            result = result?.filter { it.specialization.contains(specialization, true) }
        }

        if (!name.isNullOrEmpty()) {
            result = result?.filter { it.name.contains(name, true) }
        }

        return result ?: emptyList()
    }

}