package com.searcharchitect.common.manager

import com.searcharchitect.common.model.Contact
import com.searcharchitect.common.repositiry.IDatastoreRepository
import javax.inject.Inject

interface ISettingsManager {

    fun createPasswordMap(contacts: List<Contact>)
    suspend fun resetDataVersion()

    suspend fun getDataVersion(): Int
    suspend fun updateDataVersion(version: Int)
    suspend fun isNewDataVersion(version: Int): Boolean

    suspend fun saveCredentials(login: String, password: String)
    suspend fun isExistCorrectCredentials(): Boolean
    fun isCorrectCredentials(login: String, password: String): Boolean

}

class SettingsManager @Inject constructor(
    private val datastore: IDatastoreRepository
) : ISettingsManager {

    private val passwords = mutableMapOf<String, String>()


    override fun createPasswordMap(contacts: List<Contact>) {
        contacts.forEach { contact ->
            passwords[contact.login] = contact.password
        }
    }

    override suspend fun resetDataVersion() {
        datastore.resetDataVersion()
    }

    override suspend fun getDataVersion(): Int {
        return datastore.getSavedDataVersion()
    }

    override suspend fun updateDataVersion(version: Int) {
        datastore.updateDataVersion(version)
    }

    override suspend fun isNewDataVersion(version: Int): Boolean {
        return version > datastore.getSavedDataVersion()
    }


    override suspend fun saveCredentials(login: String, password: String) {
        datastore.saveLogin(login)
        datastore.savePassword(password)
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

}