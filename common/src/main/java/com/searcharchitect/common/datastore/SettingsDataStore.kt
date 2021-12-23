package com.searcharchitect.common.datastore

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface ISettingsDataStore {

    suspend fun getCurrentDataVersion(): Int
    suspend fun updateDataVersion(version: Int)
    suspend fun resetDataVersion()

    suspend fun getLogin(): String?
    suspend fun getPassword(): String?

    suspend fun saveLogin(login: String)
    suspend fun savePassword(password: String)

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

    override suspend fun resetDataVersion() {
        removeValue(DATA_VERSION)
    }


    override suspend fun getLogin(): String? {
        return readValue(LOGIN)
    }

    override suspend fun getPassword(): String? {
        return readValue(PASSWORD)
    }


    override suspend fun saveLogin(login: String) {
        storeValue(LOGIN, login)
    }

    override suspend fun savePassword(password: String) {
        storeValue(PASSWORD, password)
    }


    companion object {
        val DATA_VERSION = intPreferencesKey("data_version")
        val LOGIN = stringPreferencesKey("login")
        val PASSWORD = stringPreferencesKey("password")
    }

}