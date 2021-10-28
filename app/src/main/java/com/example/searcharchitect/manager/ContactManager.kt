package com.example.searcharchitect.manager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.searcharchitect.model.Contact
import com.example.searcharchitect.repositiry.IDatabaseRepository
import com.example.searcharchitect.repositiry.IDatastoreRepository
import com.example.searcharchitect.ui.search.ItemSearchUi
import com.example.searcharchitect.utility.log
import javax.inject.Inject

interface IContactManager {

    var facebookToken: String?

    fun getContacts(): LiveData<List<Contact>>
    fun getSelectedContact(): LiveData<Contact>
    fun setSelectedContact(contact: Contact)

    suspend fun isNewVersion(version: Int): Boolean
    suspend fun loadContactsFromDatabase()
    suspend fun updateAppData(version: Int, contacts: List<Contact>)

    fun getFilteredContacts(
        city: String? = null,
        specialization: String? = null,
        name: String? = null
    ): List<ItemSearchUi>

}

class ContactManager @Inject constructor(
    private val database: IDatabaseRepository,
    private val datastore: IDatastoreRepository
) : IContactManager {

    override var facebookToken: String? = null

    private val allContacts = MutableLiveData(emptyList<Contact>())

    private var selectedContact = MutableLiveData<Contact>()


    override fun getContacts(): LiveData<List<Contact>> {
        return allContacts
    }

    override fun getSelectedContact(): LiveData<Contact> {
        return selectedContact
    }

    override fun setSelectedContact(contact: Contact) {
        selectedContact.value = contact
    }


    override suspend fun isNewVersion(version: Int): Boolean {
        return version > datastore.getCurrentDataVersion()
    }

    override suspend fun loadContactsFromDatabase() {
        database.getContacts().let { contacts ->
            log("Contacts in database: ${contacts.size}")
            allContacts.value = contacts
        }
    }

    override suspend fun updateAppData(version: Int, contacts: List<Contact>) {
        database.removeAllContacts()
        database.addContacts(contacts)
        datastore.updateDataVersion(version)
        allContacts.value = contacts
    }


    override fun getFilteredContacts(
        city: String?,
        specialization: String?,
        name: String?
    ): List<ItemSearchUi> {
        var result = allContacts.value?.map { contact ->
            ItemSearchUi(
                id = contact.id,
                name = "${contact.surname} ${contact.name} ${contact.patronymic.orEmpty()}".trim(),
                location = "${contact.city} / ${contact.region}",
                specialization = contact.specialization,
                previewLink = contact.previewLink
            )
        }

        if (!city.isNullOrEmpty()) {
            result = result?.filter { it.location.contains(city, true) }
        }

        if (!specialization.isNullOrEmpty()) {
            result = result?.filter { it.specialization.contains(specialization, true) }
        }

        if (!name.isNullOrEmpty()) {
            result = result?.filter { it.name.contains(name, true) }
        }

        return result?.sortedBy { it.name } ?: emptyList()
    }

}