package com.searcharchitect.common.di

import com.searcharchitect.common.repositiry.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RepositoryModule {

    @Binds
    @ActivityRetainedScoped
    abstract fun bindDatabaseRepository(databaseRepository: DatabaseRepository): IDatabaseRepository

    @Binds
    @ActivityRetainedScoped
    abstract fun bindNetworkRepository(networkRepository: NetworkRepository): INetworkRepository

    @Binds
    @ActivityRetainedScoped
    abstract fun bindDatastoreRepository(datastoreRepository: DatastoreRepository): IDatastoreRepository

}
