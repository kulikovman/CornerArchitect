package com.searcharchitect.common.di

import com.searcharchitect.common.datastore.ISettingsDataStore
import com.searcharchitect.common.datastore.SettingsDataStore
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