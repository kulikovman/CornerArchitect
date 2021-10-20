package com.example.searcharchitect.repositiry

import com.example.searcharchitect.datastore.ISettingsDataStore
import javax.inject.Inject

interface IDatastoreRepository {

    suspend fun getCurrentDataVersion(): Int
    suspend fun updateDataVersion(dataVersion: Int)

}

class DatastoreRepository @Inject constructor(
    private val settings: ISettingsDataStore
) : IDatastoreRepository {

    override suspend fun getCurrentDataVersion(): Int {
        return settings.getCurrentDataVersion()
    }

    override suspend fun updateDataVersion(dataVersion: Int) {
        settings.updateDataVersion(dataVersion)
    }

}