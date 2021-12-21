package com.searcharchitect.common.manager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.searcharchitect.common.model.Contact
import com.searcharchitect.common.model.ItemSearchUi
import com.searcharchitect.common.repositiry.IDatabaseRepository
import com.searcharchitect.common.repositiry.IDatastoreRepository
import com.searcharchitect.common.utility.log
import javax.inject.Inject

interface IContactManager {

    var facebookToken: String?

    fun getContacts(): LiveData<List<Contact>>
    fun getSelectedContact(): LiveData<Contact>
    fun setSelectedContact(contact: Contact)

    suspend fun isNewVersion(version: Int): Boolean
    suspend fun loadContactsFromDatabase()
    suspend fun updateAppData(version: Int, contacts: List<Contact>)
    suspend fun getDataVersion(): String

    fun getFilteredContacts(
        location: String? = null,
        specialization: String? = null,
        name: String? = null
    ): List<ItemSearchUi>

    fun getContactList(location: String?, specialization: String?, name: String?): List<Contact>

    fun createPasswordMap(contacts: List<Contact>)
    suspend fun isExistCorrectCredentials(): Boolean
    fun isCorrectCredentials(login: String, password: String): Boolean
    suspend fun saveCredentials(login: String, password: String)

}

class ContactManager @Inject constructor(
    private val database: IDatabaseRepository,
    private val datastore: IDatastoreRepository
) : IContactManager {

    override var facebookToken: String? = null

    private val allContacts = MutableLiveData(emptyList<Contact>())

    private var selectedContact = MutableLiveData<Contact>()

    private val passwords = mutableMapOf<String, String>()


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
        database.getContactList().let { contacts ->
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

    override suspend fun getDataVersion(): String {
        return datastore.getCurrentDataVersion().toString()
    }


    override fun getFilteredContacts(
        location: String?,
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

        if (!location.isNullOrEmpty()) {
            result = result?.filter { it.location.contains(location, true) }
        }

        if (!specialization.isNullOrEmpty()) {
            result = result?.filter { it.specialization.contains(specialization, true) }
        }

        if (!name.isNullOrEmpty()) {
            result = result?.filter { it.name.contains(name, true) }
        }

        return result?.sortedBy { it.name } ?: emptyList()
    }

    override fun getContactList(
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

    override fun createPasswordMap(contacts: List<Contact>) {
        contacts.forEach { contact ->
            passwords[contact.login] = contact.password
        }

        log("Passwords map size: ${passwords.size}")
    }

    override suspend fun isExistCorrectCredentials(): Boolean {
        val login = datastore.getLogin()
        val password = datastore.getPassword()

        return login != null && password != null && isCorrectCredentials(login, password)
    }

    override fun isCorrectCredentials(login: String, password: String): Boolean {
        return passwords[login]?.let { storedPassword ->
                storedPassword == password
            } ?: false
    }

    override suspend fun saveCredentials(login: String, password: String) {
        datastore.saveLogin(login)
        datastore.savePassword(password)
    }

}