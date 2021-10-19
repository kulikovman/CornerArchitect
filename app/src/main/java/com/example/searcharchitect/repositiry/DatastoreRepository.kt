package com.example.searcharchitect.repositiry

import com.example.searcharchitect.datastore.ISettingsDataStore
import javax.inject.Inject

interface IDatastoreRepository {

    suspend fun getAppDataVersion(): Int
    suspend fun updateAppDataVersion(dataVersion: Int)

}

class DatastoreRepository @Inject constructor(
    private val settings: ISettingsDataStore
) : IDatastoreRepository {

    override suspend fun getAppDataVersion(): Int {
        return settings.getAppDataVersion()
    }

    override suspend fun updateAppDataVersion(dataVersion: Int) {
        settings.updateAppDataVersion(dataVersion)
    }

}