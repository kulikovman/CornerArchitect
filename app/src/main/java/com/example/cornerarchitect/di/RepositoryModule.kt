package com.example.cornerarchitect.di

import com.example.cornerarchitect.repositiry.DatabaseRepository
import com.example.cornerarchitect.repositiry.IDatabaseRepository
import com.example.cornerarchitect.repositiry.INetworkRepository
import com.example.cornerarchitect.repositiry.NetworkRepository
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

}
