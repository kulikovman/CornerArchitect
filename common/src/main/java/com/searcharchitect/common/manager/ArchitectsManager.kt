package com.searcharchitect.common.manager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.searcharchitect.common.model.Contact
import com.searcharchitect.common.model.ItemSearchUi
import com.searcharchitect.common.repositiry.IDatabaseRepository
import com.searcharchitect.common.utility.log
import javax.inject.Inject

interface IArchitectsManager {

    fun getContacts(): LiveData<List<Contact>>
    fun getSelectedContact(): LiveData<Contact>
    fun setSelectedContact(contact: Contact)

    suspend fun loadContactsFromDatabase()
    suspend fun updateContacts(version: Int, contacts: List<Contact>)

    fun getFilteredContacts(
        location: String?,
        specialization: String?,
        name: String?
    ): List<Contact>

}

class ArchitectsManager @Inject constructor(
    private val database: IDatabaseRepository
) : IArchitectsManager {

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


    override suspend fun loadContactsFromDatabase() {
        database.getContactList().let { contacts ->
            log("Contacts in database: ${contacts.size}")
            allContacts.value = contacts
        }
    }

    override suspend fun updateContacts(version: Int, contacts: List<Contact>) {
        database.removeAllContacts()
        database.addContacts(contacts)
        allContacts.value = contacts
    }


    override fun getFilteredContacts(
        location: String?,
        specialization: String?,
        name: String?
    ): List<Contact> {
        var result = allContacts.value

        location?.let {
            result = result?.filter { contact ->
                contact.city.contains(location, true)
                        || contact.region.contains(location, true)
            }
        }

        specialization?.let {
            result = result?.filter { contact ->
                contact.specialization.contains(specialization, true)
            }
        }

        name?.let {
            result = result?.filter { contact ->
                contact.surname.contains(name, true)
                        || contact.name.contains(name, true)
            }
        }

        return result?.sortedBy { it.name } ?: emptyList()
    }

}