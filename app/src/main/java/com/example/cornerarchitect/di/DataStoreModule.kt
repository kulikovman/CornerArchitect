package com.example.cornerarchitect.di

import com.example.cornerarchitect.datastore.ISettingsDataStore
import com.example.cornerarchitect.datastore.SettingsDataStore
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataStoreModule {

    @Binds
    @Singleton
    abstract fun bindSettingsDataStore(settingsDataStore: SettingsDataStore): ISettingsDataStore

}