package com.example.searcharchitect.datastore

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface ISettingsDataStore {

    suspend fun getCurrentDataVersion(): Int
    suspend fun updateDataVersion(version: Int)

}

class SettingsDataStore @Inject constructor(
    private val context: Context
) : ISettingsDataStore {

    @Suppress("UNCHECKED_CAST")
    private suspend fun <T> storeValue(key: Preferences.Key<T>, value: Any) {
        context.settingsDataStore.edit { preferences ->
            preferences[key] = value as T
        }
    }

    private suspend fun <T> readValue(key: Preferences.Key<T>): T? {
        return context.settingsDataStore.data.map { preferences ->
            preferences[key]
        }.first()
    }

    private suspend fun <T> removeValue(key: Preferences.Key<T>) {
        context.settingsDataStore.edit { preferences ->
            preferences.remove(key)
        }
    }

    override suspend fun getCurrentDataVersion(): Int {
        return readValue(DATA_VERSION) ?: 0
    }

    override suspend fun updateDataVersion(version: Int) {
        storeValue(DATA_VERSION, version)
    }


    companion object {
        val DATA_VERSION = intPreferencesKey("data_version")
    }

}