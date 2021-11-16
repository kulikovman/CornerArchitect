package com.corner.searcharchitect.manager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.corner.searcharchitect.model.Contact
import com.corner.searcharchitect.repositiry.IDatabaseRepository
import com.corner.searcharchitect.repositiry.IDatastoreRepository
import com.corner.searcharchitect.ui.search.ItemSearchUi
import com.corner.searcharchitect.utility.log
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
        city: String? = null,
        specialization: String? = null,
        name: String? = null
    ): List<ItemSearchUi>

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

    override suspend fun getDataVersion(): String {
        return datastore.getCurrentDataVersion().toString()
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
        log("isCorrectCredentials: $login / $password")

        log("Passwords: $passwords")

        return passwords.getOrDefault(login, null)?.let { passwordFromMap ->
            log("passwordFromMap = $passwordFromMap")
            passwordFromMap == password
        } ?: false
    }

    override suspend fun saveCredentials(login: String, password: String) {
        datastore.saveLogin(login)
        datastore.savePassword(password)
    }

}