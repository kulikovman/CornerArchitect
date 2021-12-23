package com.searcharchitect.common.repositiry

import com.searcharchitect.common.datastore.ISettingsDataStore
import javax.inject.Inject

interface IDatastoreRepository {

    suspend fun getSavedDataVersion(): Int
    suspend fun updateDataVersion(dataVersion: Int)
    suspend fun resetDataVersion()

    suspend fun getLogin(): String?
    suspend fun getPassword(): String?

    suspend fun saveLogin(login: String)
    suspend fun savePassword(password: String)

}

class DatastoreRepository @Inject constructor(
    private val settings: ISettingsDataStore
) : IDatastoreRepository {

    override suspend fun getSavedDataVersion(): Int {
        return settings.getCurrentDataVersion()
    }

    override suspend fun updateDataVersion(dataVersion: Int) {
        settings.updateDataVersion(dataVersion)
    }

    override suspend fun resetDataVersion() {
        settings.resetDataVersion()
    }


    override suspend fun getLogin(): String? {
        return settings.getLogin()
    }

    override suspend fun getPassword(): String? {
        return settings.getPassword()
    }


    override suspend fun saveLogin(login: String) {
        settings.saveLogin(login)
    }

    override suspend fun savePassword(password: String) {
        settings.savePassword(password)
    }

}